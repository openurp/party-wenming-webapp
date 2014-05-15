package org.openurp.webapp.apps.party.wenming.depart.model;

import java.util.Date;
import java.util.List;

import org.beangle.commons.collection.CollectUtils;
import org.beangle.commons.entity.pojo.IntegerIdObject;

/**
 * 评估批次
 * 
 * @author chaostone
 */
public class AssessSession extends IntegerIdObject {
  private static final long serialVersionUID = 1L;
  /** 名称 */
  private String name;
  /** 是否有效 **/
  private boolean enabled = true;
  /** 测评开始时间 **/
  private Date beginOn;
  /** 测评结束时间 **/
  private Date endOn;
  /** 投票开始时间 **/
  private Date voteBeginOn;
  /** 投票结束时间 **/
  private Date voteEndOn;
  /** 更新时间 **/
  private Date updatedAt;
  /** 创建时间 **/
  private Date createdAt;
  /** 测评方案 */
  private List<AssessSchema> schemas = CollectUtils.newArrayList();

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public boolean isOpened() {
    return null != beginOn && null != endOn && beginOn.getTime() <= System.currentTimeMillis()
        && System.currentTimeMillis() <= endOn.getTime();
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

  public List<AssessSchema> getSchemas() {
    return schemas;
  }

  public void setSchemas(List<AssessSchema> schemas) {
    this.schemas = schemas;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

}
