package org.openurp.webapp.apps.party.wenming.model;

import java.util.List;

import org.beangle.commons.collection.CollectUtils;
import org.beangle.commons.entity.pojo.LongIdObject;

/**
 * 部门测评加分项和材料
 * 
 * @author chaostone
 */
public class AssessBonus extends LongIdObject {
  private static final long serialVersionUID = 7084016781165137870L;

  private AssessApply apply;

  /** 加分分值 **/
  private float score;

  /** 加分项 */
  private AssessBonusItem item;

  private List<Attachment> attachments = CollectUtils.newArrayList();

  public AssessApply getApply() {
    return apply;
  }

  public void setApply(AssessApply apply) {
    this.apply = apply;
  }

  public float getScore() {
    return score;
  }

  public void setScore(float score) {
    this.score = score;
  }

  public AssessBonusItem getItem() {
    return item;
  }

  public void setItem(AssessBonusItem item) {
    this.item = item;
  }

  public List<Attachment> getAttachments() {
    return attachments;
  }

  public void setAttachments(List<Attachment> attachments) {
    this.attachments = attachments;
  }

}
