package org.openurp.webapp.apps.party.wenming.depart.model;

import org.beangle.commons.entity.pojo.LongIdObject;

public abstract class AbstractAssessItemInfo  extends LongIdObject{

  private static final long serialVersionUID = 45572625231404681L;

  private AssessItem item;

  private Float score;

  public AssessItem getItem() {
    return item;
  }

  public void setItem(AssessItem item) {
    this.item = item;
  }

  public Float getScore() {
    return score;
  }

  public void setScore(Float score) {
    this.score = score;
  }
  
  abstract public void setAssess(AbstractAssessInfo assess);

}
