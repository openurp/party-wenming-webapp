package org.openurp.webapp.apps.party.wenming.person.action;

import org.openurp.webapp.apps.party.wenming.action.WenmingProjectVoteAction;
import org.openurp.webapp.apps.party.wenming.model.AbstractWenmingObject;
import org.openurp.webapp.apps.party.wenming.model.AbstractWenmingVote;
import org.openurp.webapp.apps.party.wenming.model.WenmingType;
import org.openurp.webapp.apps.party.wenming.person.model.GoodPerson;
import org.openurp.webapp.apps.party.wenming.person.model.GoodPersonVote;

public class VoteAction extends WenmingProjectVoteAction{
  

  protected WenmingType getWenmingType(){
    return WenmingType.Person;
  }

  @Override
  protected Class getWenmingObjectClass() {
    return GoodPerson.class;
  }

  @Override
  protected <T extends AbstractWenmingVote> Class<T> getWenmingObjectVoteClass() {
    return (Class<T>) GoodPersonVote.class;
  }


}
