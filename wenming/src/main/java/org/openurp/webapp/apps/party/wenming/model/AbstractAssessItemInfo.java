package org.openurp.webapp.apps.party.wenming.model;

import org.beangle.commons.entity.pojo.LongIdObject;

public abstract class AbstractAssessItemInfo  extends LongIdObject{

  private static final long serialVersionUID = 45572625231404681L;

  private AssessItem item;

  private float score;

  public AssessItem getItem() {
    return item;
  }

  public void setItem(AssessItem item) {
    this.item = item;
  }

  public float getScore() {
    return score;
  }

  public void setScore(float score) {
    this.score = score;
  }
  
}
