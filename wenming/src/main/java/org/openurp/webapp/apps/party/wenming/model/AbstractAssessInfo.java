package org.openurp.webapp.apps.party.wenming.model;

import java.util.Date;

import org.beangle.commons.entity.pojo.LongIdObject;
import org.beangle.security.blueprint.User;
import org.openurp.kernel.base.unit.model.Department;

public abstract class AbstractAssessInfo extends LongIdObject {
  private static final long serialVersionUID = -3652781759719412956L;

  private AssessSession session;

  private AssessSchema schema;

  /** 测评针对的单位 **/
  private Department department;

  /** 测评提交时间 **/
  private Date assessAt;

  /** 测评总分值 **/
  private float totalScore;
  
  /** 状态 **/
  private AssessState state=AssessState.Draft;

  /** 测评人员 **/
  private User assessBy;
  
  public AssessSession getSession() {
    return session;
  }

  public void setSession(AssessSession session) {
    this.session = session;
  }

  public AssessSchema getSchema() {
    return schema;
  }

  public void setSchema(AssessSchema schema) {
    this.schema = schema;
  }

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

  public Date getAssessAt() {
    return assessAt;
  }

  public void setAssessAt(Date assessAt) {
    this.assessAt = assessAt;
  }

  public float getTotalScore() {
    return totalScore;
  }

  public void setTotalScore(float totalScore) {
    this.totalScore = totalScore;
  }

  public AssessState getState() {
    return state;
  }

  public void setState(AssessState state) {
    this.state = state;
  }

  public User getAssessBy() {
    return assessBy;
  }

  public void setAssessBy(User assessBy) {
    this.assessBy = assessBy;
  }

}
