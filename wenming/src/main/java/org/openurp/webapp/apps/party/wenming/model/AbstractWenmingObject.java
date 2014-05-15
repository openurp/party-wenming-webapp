package org.openurp.webapp.apps.party.wenming.model;

import java.util.Date;

import org.beangle.commons.entity.pojo.NumberIdTimeObject;
import org.beangle.security.blueprint.User;
import org.openurp.kernel.base.unit.model.Department;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessState;

/**
 * 优秀项目，好人好事，文明示范岗的基类
 * 
 * @author chaostone
 */
public abstract class AbstractWenmingObject extends NumberIdTimeObject<Long> {

  private static final long serialVersionUID = -8349323778250029726L;

  /** 申报批次 */
  private WenmingSession session;

  /** 名称 */
  private String name;

  /** 开始日期 **/
  private Date beginOn;

  /** 结束日期 **/
  private Date endOn;

  /** 申报部门 */
  private Department department;

  /** 理由和陈述 */
  private String statements;

  /** 特色和创新点 */
  private String features;

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

  /** 联系人 */
  private String contactPerson;

  /** 联系电话 */
  private String contactPhone;

  /** 相关支撑材料 */
  private Attachment attachment;

  public WenmingSession getSession() {
    return session;
  }

  public void setSession(WenmingSession session) {
    this.session = session;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

  public AssessState getState() {
    return state;
  }

  public void setState(AssessState state) {
    this.state = state;
  }

  public String getContactPerson() {
    return contactPerson;
  }

  public void setContactPerson(String contactPerson) {
    this.contactPerson = contactPerson;
  }

  public String getContactPhone() {
    return contactPhone;
  }

  public void setContactPhone(String contactPhone) {
    this.contactPhone = contactPhone;
  }

  public Date getBeginOn() {
    return beginOn;
  }

  public void setBeginOn(Date beginOn) {
    this.beginOn = beginOn;
  }

  public Date getEndOn() {
    return endOn;
  }

  public void setEndOn(Date endOn) {
    this.endOn = endOn;
  }

  public String getStatements() {
    return statements;
  }

  public void setStatements(String statements) {
    this.statements = statements;
  }

  public String getFeatures() {
    return features;
  }

  public void setFeatures(String features) {
    this.features = features;
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

  public Attachment getAttachment() {
    return attachment;
  }

  public void setAttachment(Attachment attachment) {
    this.attachment = attachment;
  }
}
