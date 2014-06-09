package org.openurp.webapp.apps.party.wenming.person.model;

import org.openurp.webapp.apps.party.wenming.model.AbstractWenmingObject;
import org.openurp.webapp.apps.party.wenming.model.AbstractWenmingVote;

public class GoodPersonVote extends AbstractWenmingVote{
  
  private GoodPerson goodPerson;

  public GoodPerson getGoodPerson() {
    return goodPerson;
  }

  public void setGoodPerson(GoodPerson goodPerson) {
    this.goodPerson = goodPerson;
  }

  @Override
  public void setGoodObject(AbstractWenmingObject obj) {
    goodPerson = (GoodPerson) obj;
    
  }

}
