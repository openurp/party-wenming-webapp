package org.openurp.webapp.apps.party.wenming.depart.model;

import org.beangle.commons.entity.pojo.IntegerIdObject;

/**
 * 评估设置
 * 
 * @author chaostone
 */
public class AssessSetting extends IntegerIdObject {
  private static final long serialVersionUID = 5935085927778636257L;

  /**投票的比例
   * 一个投票者在教学部门中最多选择多个少个  
   * */
  private float votePercent = 0.2f;

  public float getVotePercent() {
    return votePercent;
  }

  public void setVotePercent(float votePercent) {
    this.votePercent = votePercent;
  }
  
}
