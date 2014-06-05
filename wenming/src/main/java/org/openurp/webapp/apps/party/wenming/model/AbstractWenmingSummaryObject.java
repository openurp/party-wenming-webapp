package org.openurp.webapp.apps.party.wenming.model;

import java.util.Date;

import org.beangle.commons.entity.pojo.NumberIdTimeObject;
import org.beangle.security.blueprint.User;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessState;

abstract public class AbstractWenmingSummaryObject<T> extends NumberIdTimeObject<Long> implements AttachmentObject{

  /** 审核状态 */
  private AssessState state;


  /**附件**/
  private Attachment attachment;


  /**申报人员**/
  private User user;
  
  abstract public T getGood(); 

  abstract public void setGood(T t); 

  public AssessState getState() {
    return state;
  }

  public void setState(AssessState state) {
    this.state = state;
  }
  
  public Attachment getAttachment() {
    return attachment;
  }

  public void setAttachment(Attachment attachment) {
    this.attachment = attachment;
  }


  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

}
