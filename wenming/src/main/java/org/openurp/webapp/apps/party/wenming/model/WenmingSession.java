package org.openurp.webapp.apps.party.wenming.model;

import java.util.Date;
import java.util.List;

import org.beangle.commons.collection.CollectUtils;
import org.beangle.commons.entity.pojo.NumberIdTimeObject;

/**
 * 文明实体申报批次
 * 
 * @author chaostone
 */
public class WenmingSession extends NumberIdTimeObject<Integer> {
  private static final long serialVersionUID = -4231206102431453473L;
  /** 名称 */
  private String name;
  /** 测评开始时间 **/
  private Date beginOn;
  /** 测评结束时间 **/
  private Date endOn;
  /** 投票开始时间 **/
  private Date voteBeginOn;
  /** 投票结束时间 **/
  private Date voteEndOn;

  private List<WenmingType> types = CollectUtils.newArrayList();

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

  public Date getVoteBeginOn() {
    return voteBeginOn;
  }

  public void setVoteBeginOn(Date voteBeginOn) {
    this.voteBeginOn = voteBeginOn;
  }

  public Date getVoteEndOn() {
    return voteEndOn;
  }

  public void setVoteEndOn(Date voteEndOn) {
    this.voteEndOn = voteEndOn;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<WenmingType> getTypes() {
    return types;
  }

  public void setTypes(List<WenmingType> types) {
    this.types = types;
  }

}
