package org.openurp.webapp.apps.party.wenming.depart.model;

import org.beangle.commons.entity.pojo.NumberIdTimeObject;
import org.beangle.security.blueprint.User;
import org.openurp.kernel.base.unit.model.Department;

/**
 * 测评投票
 * 
 * @author chaostone
 */
public class AssessVote extends NumberIdTimeObject<Long> {

  private static final long serialVersionUID = 4303109503469167899L;

  /** 投票批次 */
  private VoteSession session;

  /** 投票人 */
  private Supervisor voter;

  /** 部门 */
  private Department department;

  /** 赞成票 */
  private boolean ayes;

  private boolean submit;

  public VoteSession getSession() {
    return session;
  }

  public void setSession(VoteSession session) {
    this.session = session;
  }

  public Supervisor getVoter() {
    return voter;
  }

  public void setVoter(Supervisor voter) {
    this.voter = voter;
  }

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

  public boolean isAyes() {
    return ayes;
  }

  public void setAyes(boolean ayes) {
    this.ayes = ayes;
  }

  public boolean isSubmit() {
    return submit;
  }

  public void setSubmit(boolean submit) {
    this.submit = submit;
  }

}
