package org.openurp.webapp.apps.party.wenming.person.action;

import org.openurp.webapp.apps.party.wenming.action.WenMingProjectAction;
import org.openurp.webapp.apps.party.wenming.model.WenmingType;
import org.openurp.webapp.apps.party.wenming.person.model.GoodPerson;

public class ApplyAction  extends WenMingProjectAction {
  
  @Override
  protected String getEntityName() {
    return GoodPerson.class.getName();
  }

  @Override
  protected WenmingType getWenmingType() {
    return WenmingType.Person;
  }

}
