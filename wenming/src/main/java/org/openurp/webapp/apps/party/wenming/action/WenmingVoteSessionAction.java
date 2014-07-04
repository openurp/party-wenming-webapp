package org.openurp.webapp.apps.party.wenming.action;

import java.util.Date;
import java.util.List;

import org.beangle.commons.collection.CollectUtils;
import org.beangle.commons.dao.query.builder.OqlBuilder;
import org.beangle.commons.entity.Entity;
import org.openurp.webapp.apps.party.wenming.depart.action.WenMingAction;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessState;
import org.openurp.webapp.apps.party.wenming.model.WenmingProjectVoter;
import org.openurp.webapp.apps.party.wenming.model.WenmingSession;
import org.openurp.webapp.apps.party.wenming.model.WenmingVoteSession;
import org.openurp.webapp.apps.party.wenming.person.model.GoodPerson;
import org.openurp.webapp.apps.party.wenming.post.model.GoodPost;
import org.openurp.webapp.apps.party.wenming.project.model.GoodProject;

public class WenmingVoteSessionAction extends WenMingAction {
  @Override
  protected String getEntityName() {
    return WenmingVoteSession.class.getName();
  }

  @Override
  protected void editSetting(Entity<?> entity) {
    WenmingVoteSession voteSession = (WenmingVoteSession) entity;
    List<WenmingProjectVoter> voters = findVoters();
    voters.removeAll(voteSession.getVoters());
    put("voters", voters);
    if (voteSession.getSession() == null) {
      OqlBuilder<WenmingSession> builder = OqlBuilder.from(WenmingSession.class, "ss");
      builder.where("ss.beginOn < :now", new Date());
      builder.orderBy("ss.beginOn desc");
      builder.limit(1, 1);
      List<WenmingSession> wenmingSessions = entityDao.search(builder);
      if (wenmingSessions.isEmpty()) { throw new RuntimeException("不存在测评批次，无法创建投票批次"); }
      voteSession.setSession(wenmingSessions.get(0));
    }
    List<GoodProject> projects = findGoodProjects(voteSession.getSession().getId());
    projects.removeAll(voteSession.getProjects());
    put("projects", projects);
    List<GoodPerson> people = findGoodPeople(voteSession.getSession().getId());
    people.removeAll(voteSession.getPersons());
    put("persons", people);
    List<GoodPost> posts = findGoodPosts(voteSession.getSession().getId());
    posts.removeAll(voteSession.getPosts());
    put("posts", posts);

  }

  private List<GoodProject> findGoodProjects(Integer sessionId) {
    OqlBuilder<GoodProject> builder = OqlBuilder.from(GoodProject.class,"p");
    builder.where("p.session.id=:sessionId",sessionId);
    builder.where("p.state not in(:states)",CollectUtils.newArrayList(AssessState.SchoolUnpassed,AssessState.DepartUnpassed,AssessState.Draft));
    List<GoodProject> projects = entityDao.search(builder);
    return projects;
  }

  private List<GoodPerson> findGoodPeople(Integer sessionId) {
    OqlBuilder<GoodPerson> builder = OqlBuilder.from(GoodPerson.class,"p");
    builder.where("p.session.id=:sessionId",sessionId);
    builder.where("p.state not in(:states)",CollectUtils.newArrayList(AssessState.SchoolUnpassed,AssessState.DepartUnpassed,AssessState.Draft));
    List<GoodPerson> people = entityDao.search(builder);
    return people;
  }

  private List<GoodPost> findGoodPosts(Integer sessionId) {
    OqlBuilder<GoodPost> builder = OqlBuilder.from(GoodPost.class,"p");
    builder.where("p.session.id=:sessionId",sessionId);
    builder.where("p.state not in(:states)",CollectUtils.newArrayList(AssessState.SchoolUnpassed,AssessState.DepartUnpassed,AssessState.Draft));
    List<GoodPost> posts = entityDao.search(builder);
    return posts;
  }

  private List<WenmingProjectVoter> findVoters() {
    OqlBuilder<WenmingProjectVoter> builder = OqlBuilder.from(WenmingProjectVoter.class);
    builder.orderBy("fullname");
    return entityDao.search(builder);
  }

}
