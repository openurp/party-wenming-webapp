package org.openurp.kernel.base.unit.model;

import java.sql.Date;

import org.beangle.commons.entity.pojo.IntegerIdObject;

public class Department extends IntegerIdObject {

  private static final long serialVersionUID = -8261982977565690446L;

  private String code;

  private String name;

  private Date beginOn;

  private Date endOn;

  /**
   * 是否为教学部门
   */
  private boolean teaching;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public boolean isTeaching() {
    return teaching;
  }

  public void setTeaching(boolean teaching) {
    this.teaching = teaching;
  }

}
