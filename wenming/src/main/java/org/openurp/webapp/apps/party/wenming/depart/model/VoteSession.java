package org.openurp.webapp.apps.party.wenming.depart.model;

import java.util.List;

import org.beangle.commons.collection.CollectUtils;
import org.openurp.kernel.base.unit.model.Department;
import org.openurp.webapp.apps.party.wenming.model.AbstractVoteSession;

/**
 * 文明委投票批次
 * 
 * @author chii
 * 
 */
public class VoteSession extends AbstractVoteSession {
  /** 相关批次 **/
  private AssessSession session;
  /** 评委 **/
  private List<Supervisor> voters = CollectUtils.newArrayList();
  /** 投票对象（部门） **/
  private List<Department> departments = CollectUtils.newArrayList();

  public AssessSession getSession() {
    return session;
  }

  public void setSession(AssessSession session) {
    this.session = session;
  }

  public List<Supervisor> getVoters() {
    return voters;
  }

  public void setVoters(List<Supervisor> voters) {
    this.voters = voters;
  }

  public List<Department> getDepartments() {
    return departments;
  }

  public void setDepartments(List<Department> departments) {
    this.departments = departments;
  }

}
