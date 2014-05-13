package org.openurp.webapp.apps.party.wenming.model;

import java.util.List;

import org.beangle.commons.collection.CollectUtils;
import org.beangle.commons.entity.pojo.IntegerIdObject;

/**
 * 测评指标
 * 
 * @author chaostone
 */
public class AssessItem extends IntegerIdObject implements Comparable<AssessItem> {
  
  private static final long serialVersionUID = 981858295086278562L;
  /** 测评指标分类 **/
  private AssessItemGroup group;
  /** 指标内容 **/
  private String content;
  /** 指标分值 **/
  private Float score;
  /** 排列顺序 **/
  private Integer orderNumber;
  /** 测评类型 **/
  private AssessType assessType = AssessType.FUNC_DEPART;
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

  public AssessType getAssessType() {
    return assessType;
  }

  public void setAssessType(AssessType assessType) {
    this.assessType = assessType;
  }

  @Override
  public int compareTo(AssessItem o) {
    return orderNumber.compareTo(o.getOrderNumber());
  }

}
