package org.openurp.webapp.apps.party.wenming.post.action;

import org.openurp.webapp.apps.party.wenming.action.WenmingProjectVoteAction;
import org.openurp.webapp.apps.party.wenming.model.AbstractWenmingObject;
import org.openurp.webapp.apps.party.wenming.model.AbstractWenmingVote;
import org.openurp.webapp.apps.party.wenming.model.WenmingType;
import org.openurp.webapp.apps.party.wenming.post.model.GoodPost;
import org.openurp.webapp.apps.party.wenming.post.model.GoodPostVote;

public class VoteAction extends WenmingProjectVoteAction{
  
  
  protected WenmingType getWenmingType(){
    return WenmingType.Post;
  }

  @Override
  protected Class getWenmingObjectClass() {
    return GoodPost.class;
  }

  @Override
  protected <T extends AbstractWenmingVote> Class<T> getWenmingObjectVoteClass() {
    return (Class<T>) GoodPostVote.class;
  }

}
