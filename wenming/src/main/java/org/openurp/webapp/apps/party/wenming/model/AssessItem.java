package org.openurp.webapp.apps.party.wenming.model;

import java.util.List;

import org.beangle.commons.collection.CollectUtils;
import org.beangle.commons.entity.pojo.IntegerIdObject;

/**
 * 测评指标
 * 
 * @author chaostone
 * 
 */
public class AssessItem extends IntegerIdObject {
	/** 测评指标分类 **/
  private AssessItemGroup group;
  /** 指标内容 **/
  private String content;
  /** 指标分值 **/
  private Float score;
  /** 排列顺序 **/
  private Integer orderNumber;
  /** 是否为互评指标 **/
  private Boolean mutual = false;
  /** 是否督察组测评指标 **/
  private Boolean forSupervisor = false;
  /** 评分单位 **/
  private List<AssessItemDepartment> departs = CollectUtils.newArrayList();

  public AssessItemGroup getGroup() {
    return group;
  }

  public void setGroup(AssessItemGroup category) {
    this.group = category;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public List<AssessItemDepartment> getDeparts() {
    return departs;
  }

  public void setDeparts(List<AssessItemDepartment> departs) {
    this.departs = departs;
  }

  public Float getScore() {
    return score;
  }

  public void setScore(Float score) {
    this.score = score;
  }

  public Integer getOrderNumber() {
    return orderNumber;
  }

  public void setOrderNumber(Integer orderNumber) {
    this.orderNumber = orderNumber;
  }

  public Boolean getMutual() {
    return mutual;
  }

  public void setMutual(Boolean mutual) {
    this.mutual = mutual;
  }

  public Boolean getForSupervisor() {
    return forSupervisor;
  }

  public void setForSupervisor(Boolean forSupervisor) {
    this.forSupervisor = forSupervisor;
  }

}
