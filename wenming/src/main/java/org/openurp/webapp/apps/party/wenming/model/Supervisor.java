package org.openurp.webapp.apps.party.wenming.model;

import java.sql.Date;

import org.beangle.commons.entity.pojo.IntegerIdObject;

/**
 * 督察组成员
 * 
 * @author chaostone
 */
public class Supervisor extends IntegerIdObject {

  private static final long serialVersionUID = 1L;

  /**
   * 系统登录名称
   */
  private String name;

  /**
   * 真实姓名
   */
  private String fullname;

  /** 密码 */
  private String password;
  /**
   * 过期日期
   */
  private Date expiredOn;

  private String remark;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getFullname() {
    return fullname;
  }

  public void setFullname(String fullname) {
    this.fullname = fullname;
  }

  public Date getExpiredOn() {
    return expiredOn;
  }

  public void setExpiredOn(Date expiredOn) {
    this.expiredOn = expiredOn;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}
