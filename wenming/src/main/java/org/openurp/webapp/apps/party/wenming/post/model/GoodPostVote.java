package org.openurp.webapp.apps.party.wenming.post.model;

import org.openurp.webapp.apps.party.wenming.model.AbstractWenmingObject;
import org.openurp.webapp.apps.party.wenming.model.AbstractWenmingVote;

public class GoodPostVote extends AbstractWenmingVote{
  
  private GoodPost goodPost;

  public GoodPost getGoodPost() {
    return goodPost;
  }

  public void setGoodPost(GoodPost goodPost) {
    this.goodPost = goodPost;
  }

  @Override
  public void setGoodObject(AbstractWenmingObject obj) {
    goodPost = (GoodPost) obj;
    
  }

}
