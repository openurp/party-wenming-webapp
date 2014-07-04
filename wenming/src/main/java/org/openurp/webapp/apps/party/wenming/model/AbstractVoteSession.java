package org.openurp.webapp.apps.party.wenming.model;

import java.util.Date;

import org.beangle.commons.entity.pojo.IntegerIdObject;
import org.beangle.commons.entity.pojo.LongIdObject;

public class AbstractVoteSession extends IntegerIdObject {

  /** 名称 */
  private String name;
  /** 开始时间 **/
  private Date beginOn;
  /** 结束时间 **/
  private Date endOn;
  /** 票数限制 **/
  private Integer limit = 0;
  /** 票数限制，为０的时候不限制 **/
  private Integer limit2 = 0;

  public String getName() {
    return name;
  }

  public Integer getLimit2() {
    return limit2;
  }

  public void setLimit2(Integer limit2) {
    this.limit2 = limit2;
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

  public Integer getLimit() {
    return limit;
  }

  public void setLimit(Integer limit) {
    this.limit = limit;
  }

}
