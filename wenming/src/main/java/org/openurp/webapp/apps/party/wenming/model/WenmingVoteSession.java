package org.openurp.webapp.apps.party.wenming.model;

import java.util.List;

import org.beangle.commons.collection.CollectUtils;
import org.openurp.webapp.apps.party.wenming.person.model.GoodPerson;
import org.openurp.webapp.apps.party.wenming.post.model.GoodPost;
import org.openurp.webapp.apps.party.wenming.project.model.GoodProject;

/**
 * 文明项目投票批次
 * 
 * @author chii
 * 
 */
public class WenmingVoteSession extends AbstractVoteSession {
  /** 相关批次 **/
  private WenmingSession session;
  /** 评委 **/
  private List<WenmingProjectVoter> voters = CollectUtils.newArrayList();
  /** 被投票的优秀项目 **/
  private List<GoodProject> projects = CollectUtils.newArrayList();
  /** 被投票的好人好事 **/
  private List<GoodPerson> persons = CollectUtils.newArrayList();
  /** 被投票的文明示范岗 **/
  private List<GoodPost> posts = CollectUtils.newArrayList();

  public WenmingSession getSession() {
    return session;
  }

  public void setSession(WenmingSession session) {
    this.session = session;
  }

  public List<WenmingProjectVoter> getVoters() {
    return voters;
  }

  public void setVoters(List<WenmingProjectVoter> voters) {
    this.voters = voters;
  }

  public List<GoodProject> getProjects() {
    return projects;
  }

  public void setProjects(List<GoodProject> projects) {
    this.projects = projects;
  }

  public List<GoodPerson> getPersons() {
    return persons;
  }

  public void setPersons(List<GoodPerson> persons) {
    this.persons = persons;
  }

  public List<GoodPost> getPosts() {
    return posts;
  }

  public void setPosts(List<GoodPost> posts) {
    this.posts = posts;
  }

}
