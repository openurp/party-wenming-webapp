package org.openurp.webapp.apps.party.wenming.depart.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.beangle.commons.dao.query.builder.OqlBuilder;
import org.beangle.commons.dao.query.builder.SqlBuilder;
import org.openurp.kernel.base.unit.model.Department;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessApply;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessSchema;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessSession;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessVote;
import org.openurp.webapp.apps.party.wenming.depart.model.FuncDepartAssess;
import org.openurp.webapp.apps.party.wenming.depart.model.MutualAssess;
import org.openurp.webapp.apps.party.wenming.depart.model.SelfAssess;
import org.openurp.webapp.apps.party.wenming.depart.model.Supervisor;
import org.openurp.webapp.apps.party.wenming.depart.model.SupervisorAssess;

import com.sun.tools.classfile.StackMapTable_attribute.verification_type_info;

public class VoteAction extends SupervisorCommAction {

  @Override
  protected String getEntityName() {
    return AssessVote.class.getName();
  }

  public String login() {
    String name = get("username");
    String password = get("password");
    if (StringUtils.isEmpty(name) || StringUtils.isEmpty(password)) { return forward("login"); }
    OqlBuilder<Supervisor> query = OqlBuilder.from(Supervisor.class);
    query.where("name = :name", name);
    query.where("password = :password", password);
    List<Supervisor> voters = search(query);
    if (voters.isEmpty()) {
      put("message", "用户名或密码错误！");
      put("name", name);
      return forward("login");
    } else {
      Supervisor voter = voters.get(0);
      getSession().put(SUPERVISOR_ID, voter.getId());
      return redirect("index");
    }
  }

  @Override
  public String index() throws Exception {
    if (getSupervisorId() == null) { return redirect("login"); }
    List<AssessSession> assessSessions = wenMingService.findSessions();
    Integer sessionId = getInt("session.id");
    AssessSession assessSession = null;
    if (sessionId != null) {
      assessSession = entityDao.get(AssessSession.class, sessionId);
    } else if (!assessSessions.isEmpty()) {
      assessSession = assessSessions.get(0);
    }
    put("schemas", assessSession.getSchemas());
    put("assessSessions", assessSessions);
    put("assessSession", assessSession);
    return super.index();
  }

  @Override
  public String info() throws Exception {
    if (getSupervisorId() == null) { return redirect("login"); }
    Integer sessionId = getInt("session.id");
    boolean isForTeaching = getBool("departmentType");
    List<AssessVote> assessVotes = findAssessVote(sessionId, isForTeaching);
    AssessSession nowSession = wenMingService.getAssessSessionByAssessTime();
    AssessSession session = entityDao.get(AssessSession.class, getInt("session.id"));
    if (assessVotes.isEmpty() && nowSession != null && nowSession.equals(session)) { return redirect("edit"); }
    if (modifyable(assessVotes)) {
      put("modifyable", true);
    }
    put("assessSession", session);
    putData(session, isForTeaching, assessVotes);
    return forward();
  }

  @Override
  public String edit() {
    if (getSupervisorId() == null) { return redirect("login"); }
    AssessSession nowSession = wenMingService.getAssessSessionByAssessTime();
    AssessSession session = entityDao.get(AssessSession.class, getInt("session.id"));

    boolean isForTeaching = getBool("departmentType");
    List<AssessVote> assessVotes = findAssessVote(session.getId(), isForTeaching);
    
    if (nowSession!=null && nowSession.equals(session)) {
      if (assessVotes.isEmpty()) {
        Supervisor voter = getSupervisor();
        Date now = new Date();
        for (AssessSchema schema : session.getSchemas()) {
          if (schema.isForTeaching() == isForTeaching) {
            for (Department department : schema.getDeparts()) {
              AssessVote assessVote = new AssessVote();
              assessVote.setDepartment(department);
              assessVote.setVoter(voter);
              assessVote.setUpdatedAt(now);
              assessVotes.add(assessVote);
            }
          }
        }
      } else if (assessVotes.get(0).isSubmit()) { return redirect("info", null,
          "session.id=" + session.getId() + "&departmentType=" + isForTeaching); }
      putData(session, isForTeaching, assessVotes);
      return forward();
    } else {
      return redirect("info", null, "session.id=" + session.getId() + "&departmentType=" + isForTeaching);
    }
  }

  private void putData(AssessSession session, boolean isForTeaching, List<AssessVote> assessVotes) {
    List<AssessApply> assessApplies = findAssessApply(session.getId());
    put("assessVotes", assessVotes);
    Map<String, Float> selfAssessScore = findAssessAvgScore(session.getId(),"wm_self_assesses", "wm_self_assess_items");
    Map<String, Float> mutualAssessScore = findAssessAvgScore(session.getId(),"wm_mutual_assesses", "wm_mutual_assess_items");
    Map<String, Float> funcDepartAssessScore = findAssessAvgScore(session.getId(),"wm_func_depart_assesses", "wm_func_depart_assess_items");
    Map<String, Float> supervisorAssessScore = findAssessAvgScore(session.getId(),"wm_supervisor_assesses", "wm_supervisor_assess_items");
    Map<String, Float> totalScoreMap = new HashMap<String, Float>();
    for(AssessVote assessVote : assessVotes){
      try {
        Float totalScore = 0F;
        String departmentId = assessVote.getDepartment().getId().toString();
        totalScore += getZeroScore(selfAssessScore.get(departmentId))*0.1F;
        totalScore += getZeroScore(mutualAssessScore.get(departmentId))*0.9F;
        totalScore += getZeroScore(supervisorAssessScore.get(departmentId))*0.9F;
        totalScore += getZeroScore(funcDepartAssessScore.get(departmentId))*0.9F;
        totalScoreMap.put(departmentId.toString(), totalScore);
      } catch (Exception e){}
    }
    put("totalScoreMap",totalScoreMap);
    //put("selfAssessScore", selfAssessScore);
    //put("mutualAssessScore", mutualAssessScore);
    //put("funcDepartAssessScore", funcDepartAssessScore);
    //put("supervisorAssessScore", supervisorAssessScore);
    put("assessApplies", assessApplies);
    put("departmentType", isForTeaching);
  }
  
  private Map<String, Float> findAssessAvgScore(Integer sessionId, String assessTableName,
      String assessItemTableName) {
    StringBuffer sql = new StringBuffer();
    sql.append(" select departmentId, sum(score) from (");
    sql.append(" select a.department_id departmentId, avg(ai.score) score ");
    sql.append(" from ").append(assessTableName).append(" a join ").append(assessItemTableName).append(" ai on a.id = ai.assess_id");
    sql.append(" where a.session_id = 1");
    sql.append(" group by a.department_id, ai.item_id");
    sql.append(" ) t group by departmentId");
    List<Object[]> list = (List<Object[]>) entityDao.search(SqlBuilder.sql(sql.toString()));
    Map<String, Float> map = new HashMap<String, Float>();
    for (Object[] oo : list) {
      map.put(oo[0].toString(), Float.parseFloat(oo[1].toString()));
    }
    return map;
  }

  private Float getZeroScore(Float score){
    if(score == null){
      return 0F;
    }
    return score;
  }

  private List<AssessVote> findAssessVote(Integer sessionId, boolean isForTeaching) {
    OqlBuilder<AssessVote> builder = OqlBuilder.from(AssessVote.class, "v");
    builder.where("v.session.id=:sessionId", sessionId);
    builder.where("v.department.teaching=:teaching", isForTeaching);
    List<AssessVote> assessVotes = entityDao.search(builder);
    return assessVotes;
  }
  
  private List<AssessApply> findAssessApply(Integer sessionId) {
    OqlBuilder<AssessApply> builder = OqlBuilder.from(AssessApply.class, "a");
    builder.where("a.session.id=:sessionId", sessionId);
    List<AssessApply> AssessApplies = entityDao.search(builder);
    return AssessApplies;
  }

  @Override
  public String save() throws Exception {
    if (getSupervisorId() == null) { return redirect("login"); }

    boolean departmentType = getBool("departmentType");
    AssessSession session = wenMingService.getAssessSessionByAssessTime();
    List<AssessVote> list = (List<AssessVote>) getAll();
    Date now = new Date();
    Supervisor voter = getSupervisor();
    boolean save = getBool("save");
    for (AssessVote vote : list) {
      if (vote.getCreatedAt() == null) {
        vote.setCreatedAt(now);
      }
      vote.setSession(session);
      vote.setUpdatedAt(now);
      vote.setVoter(voter);
      if (!save) {
        vote.setSubmit(true);
      }
    }
    entityDao.saveOrUpdate(list);
    return redirect("info", null, "session.id=" + session.getId() + "&departmentType=" + departmentType);
  }

  private boolean modifyable(List<AssessVote> assessVotes) {
    return (!assessVotes.isEmpty() && !assessVotes.get(0).isSubmit());
  }
  
}
