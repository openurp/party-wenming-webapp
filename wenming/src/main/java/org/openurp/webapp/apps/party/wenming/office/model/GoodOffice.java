package org.openurp.webapp.apps.party.wenming.office.model;

import java.util.Date;

import org.beangle.commons.entity.pojo.NumberIdTimeObject;
import org.beangle.security.blueprint.User;
import org.openurp.kernel.base.unit.model.Department;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessState;
import org.openurp.webapp.apps.party.wenming.model.Attachment;
import org.openurp.webapp.apps.party.wenming.model.WenmingSession;

/**
 * 文明科室
 * 
 * @author chaostone
 */
public class GoodOffice extends NumberIdTimeObject<Long> {
  
  private static final long serialVersionUID = -1812694005152660808L;

  /** 申报批次 */
  private WenmingSession session;

  /** 申报部门 */
  private Department department;

  /** 文明科室 */
  private String offices;

  /** 提交人 */
  private User submitBy;

  /** 审批人 */
  private User auditBy;

  /** 提交时间 */
  private Date submitAt;

  /** 审核时间 */
  private Date auditAt;

  /** 最终状态 */
  private AssessState state;

  /** 相关支撑材料 */
  private Attachment attachment;

  public WenmingSession getSession() {
    return session;
  }

  public void setSession(WenmingSession session) {
    this.session = session;
  }

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

  public String getOffices() {
    return offices;
  }

  public void setOffices(String offices) {
    this.offices = offices;
  }

  public User getSubmitBy() {
    return submitBy;
  }

  public void setSubmitBy(User submitBy) {
    this.submitBy = submitBy;
  }

  public User getAuditBy() {
    return auditBy;
  }

  public void setAuditBy(User auditBy) {
    this.auditBy = auditBy;
  }

  public Date getSubmitAt() {
    return submitAt;
  }

  public void setSubmitAt(Date submitAt) {
    this.submitAt = submitAt;
  }

  public Date getAuditAt() {
    return auditAt;
  }

  public void setAuditAt(Date auditAt) {
    this.auditAt = auditAt;
  }

  public AssessState getState() {
    return state;
  }

  public void setState(AssessState state) {
    this.state = state;
  }

  public Attachment getAttachment() {
    return attachment;
  }

  public void setAttachment(Attachment attachment) {
    this.attachment = attachment;
  }

}
