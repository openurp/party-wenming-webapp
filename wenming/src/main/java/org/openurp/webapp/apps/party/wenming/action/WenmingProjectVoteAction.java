package org.openurp.webapp.apps.party.wenming.action;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.beangle.commons.collection.CollectUtils;
import org.beangle.commons.dao.query.builder.OqlBuilder;
import org.beangle.commons.entity.Entity;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessState;
import org.openurp.webapp.apps.party.wenming.model.AbstractWenmingObject;
import org.openurp.webapp.apps.party.wenming.model.AbstractWenmingVote;
import org.openurp.webapp.apps.party.wenming.model.WenmingProjectVoter;
import org.openurp.webapp.apps.party.wenming.model.WenmingSession;

public abstract class WenmingProjectVoteAction extends WenmingProjectVoterCommAction {

  @Override
  protected String getEntityName() {
    return getWenmingObjectClass().getName();
  }

  public String login() {
    String name = get("username");
    String password = get("password");
    if (StringUtils.isEmpty(name) || StringUtils.isEmpty(password)) { return forward("login"); }
    OqlBuilder<WenmingProjectVoter> query = OqlBuilder.from(WenmingProjectVoter.class);
    query.where("name = :name", name);
    query.where("password = :password", password);
    List<WenmingProjectVoter> voters = search(query);
    if (voters.isEmpty()) {
      put("message", "用户名或密码错误！");
      put("name", name);
      return forward("login");
    } else {
      WenmingProjectVoter voter = voters.get(0);
      getSession().put(WENMINGPROJECTVOTER_ID, voter.getId());
      return redirect("index");
    }
  }

  @Override
  public String index() throws Exception {
    if (getWenmingProjectVoterId() == null) { return redirect("login"); }
    List<WenmingSession> wenmingSessions = wenMingService.findWenmingSessions();
    Integer sessionId = getInt("session.id");
    WenmingSession wenmingSession = null;
    if (sessionId != null) {
      wenmingSession = entityDao.get(WenmingSession.class, sessionId);
    } else if (!wenmingSessions.isEmpty()) {
      wenmingSession = wenmingSessions.get(0);
    }
    put("wenmingSessions", wenmingSessions);
    put("wenmingSession", wenmingSession);
    return super.index();
  }

  @Override
  public String info() throws Exception {
    if (getWenmingProjectVoterId() == null) { return redirect("login"); }
    Integer sessionId = getInt("session.id");
    List<AbstractWenmingVote> abstractWenmingVotes = findAbstractWenmingVote(sessionId);
    WenmingSession nowSession = wenMingService.getWenmingSessionByVoteTime();
    WenmingSession session = entityDao.get(WenmingSession.class, getInt("session.id"));
    if (abstractWenmingVotes.isEmpty() && nowSession != null && nowSession.equals(session)) { return redirect(
        "edit", null, "session.id=" + sessionId); }
    if (nowSession!=null && modifyable(abstractWenmingVotes)) {
      put("modifyable", true);
    }
    if (!abstractWenmingVotes.isEmpty() && abstractWenmingVotes.get(0).isSubmit()){
      put("isSubmit", true);
    }
    put("wenmingSession", session);
    put("abstractWenmingVotes", abstractWenmingVotes);
    return forward();
  }

  private boolean modifyable(List<AbstractWenmingVote> abstractWenmingVotes) {
    return !abstractWenmingVotes.isEmpty() && !abstractWenmingVotes.get(0).isSubmit();
  }

  @Override
  public String edit() {
    if (getWenmingProjectVoterId() == null) { return redirect("login"); }
    WenmingSession nowSession = wenMingService.getWenmingSessionByVoteTime();
    WenmingSession session = entityDao.get(WenmingSession.class, getInt("session.id"));
    List<AbstractWenmingVote> abstractWenmingVotes = findAbstractWenmingVote(session.getId());
    List<AbstractWenmingObject> objects = findAbstractWenmingObject(session.getId());
    if (nowSession != null && nowSession.equals(session)) {
      if (abstractWenmingVotes.isEmpty()) {
        WenmingProjectVoter voter = getWenmingProjectVoter();
        Date now = new Date();
        for (AbstractWenmingObject obj : objects) {
          AbstractWenmingVote abstractWenmingVote;
          try {
            abstractWenmingVote = getWenmingObjectVoteClass().newInstance();
            abstractWenmingVote.setVoter(voter);
            abstractWenmingVote.setUpdatedAt(now);
            abstractWenmingVote.setGoodObject(obj);
            abstractWenmingVotes.add(abstractWenmingVote);
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      } else if (abstractWenmingVotes.get(0).isSubmit()) { return redirect("info", null, "session.id="
          + session.getId()); }
      put("abstractWenmingVotes", abstractWenmingVotes);
      return forward();
    } else {
      return redirect("info", null, "session.id=" + session.getId());
    }
  }

  private List<AbstractWenmingObject> findAbstractWenmingObject(Integer sessionId) {
    @SuppressWarnings("rawtypes")
    OqlBuilder<AbstractWenmingObject> builder = OqlBuilder.from(getWenmingObjectClass(), "awo");
    builder.where("awo.session.id=:sessionId", sessionId);
    //FIXME 这次评选仅仅过滤掉草稿和不通过的
    builder.where("awo.state not in(:states)",CollectUtils.newArrayList(AssessState.SchoolUnpassed,AssessState.DepartUnpassed,AssessState.Draft));
    List<AbstractWenmingObject> objects = entityDao.search(builder);
    return objects;
  }

  protected abstract <T extends AbstractWenmingObject> Class<T> getWenmingObjectClass();

  protected abstract <T extends AbstractWenmingVote> Class<T> getWenmingObjectVoteClass();

  private List<AbstractWenmingVote> findAbstractWenmingVote(Integer sessionId) {
    OqlBuilder<AbstractWenmingVote> builder = OqlBuilder.from(getWenmingObjectVoteClass(), "awv");
    builder.where("awv.session.id=:sessionId", sessionId);
    builder.where("awv.voter.id=:voterid", getWenmingProjectVoter().getId());
    List<AbstractWenmingVote> abstractWenmingVotes = entityDao.search(builder);
    return abstractWenmingVotes;
  }

  @Override
  public String save() throws Exception {
    if (getWenmingProjectVoterId() == null) { return redirect("login"); }
    WenmingSession session = wenMingService.getWenmingSessionByVoteTime();
    List<AbstractWenmingVote> list = (List<AbstractWenmingVote>) getAll(getWenmingObjectVoteClass(),"index");
    Date now = new Date();
    WenmingProjectVoter voter = getWenmingProjectVoter();
    boolean save = getBool("save");
    for(AbstractWenmingVote vote : list){
      if(vote.getCreatedAt() == null) {
        vote.setCreatedAt(now);
      }
      vote.setSession(session);
      vote.setUpdatedAt(now);
      vote.setVoter(voter);
      if(!save){
        vote.setSubmit(true);
      }
    }
    entityDao.saveOrUpdate(list);
    return redirect("info", null, "session.id="+session.getId());
  }
}
