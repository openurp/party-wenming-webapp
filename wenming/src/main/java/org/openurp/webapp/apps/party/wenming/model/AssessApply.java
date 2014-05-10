package org.openurp.webapp.apps.party.wenming.model;

import java.util.List;

import org.beangle.commons.collection.CollectUtils;
import org.beangle.commons.entity.pojo.LongIdObject;
import org.beangle.security.blueprint.User;
import org.openurp.kernel.base.unit.model.Department;

/**
 * 文明单位申报
 * 
 * @author chaostone
 */
public class AssessApply extends LongIdObject {

  private static final long serialVersionUID = -203639712300833579L;

  /** 部门 */
  private Department department;

  /** 测评批次 */
  private AssessSession session;

  /** 状态 */
  private AssessState state;
  /** 申报人员 **/
  private User submitBy;

  /** 创建活动及其效果概要 **/
  private String activitySummary;

  /** 文明创建特色工作概要 **/
  private String wenmingSummary;

  /** 详实材料文档 **/
  private String detail;

  /** 加分项申报加分分值 **/
  private float bonus;

  /** 最终加分分值 **/
  private float confirmBonus;

  /** 加分项 */
  private List<AssessBonus> bonuses = CollectUtils.newArrayList();

  /** 相关支撑材料 */
  private Attachment attachment;

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

  public AssessSession getSession() {
    return session;
  }

  public void setSession(AssessSession session) {
    this.session = session;
  }

  public AssessState getState() {
    return state;
  }

  public void setState(AssessState state) {
    this.state = state;
  }

  public User getSubmitBy() {
    return submitBy;
  }

  public void setSubmitBy(User submitBy) {
    this.submitBy = submitBy;
  }

  public String getActivitySummary() {
    return activitySummary;
  }

  public void setActivitySummary(String activitySummary) {
    this.activitySummary = activitySummary;
  }

  public String getWenmingSummary() {
    return wenmingSummary;
  }

  public void setWenmingSummary(String wenmingSummary) {
    this.wenmingSummary = wenmingSummary;
  }

  public String getDetail() {
    return detail;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }

  public float getBonus() {
    return bonus;
  }

  public void setBonus(float bonus) {
    this.bonus = bonus;
  }

  public float getConfirmBonus() {
    return confirmBonus;
  }

  public void setConfirmBonus(float confirmBonus) {
    this.confirmBonus = confirmBonus;
  }

  public List<AssessBonus> getBonuses() {
    return bonuses;
  }

  public void setBonuses(List<AssessBonus> bonuses) {
    this.bonuses = bonuses;
  }

  public Attachment getAttachment() {
    return attachment;
  }

  public void setAttachment(Attachment attachment) {
    this.attachment = attachment;
  }

}
