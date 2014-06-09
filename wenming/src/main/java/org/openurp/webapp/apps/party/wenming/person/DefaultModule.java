package org.openurp.webapp.apps.party.wenming.person;

import org.beangle.commons.inject.bind.AbstractBindModule;
import org.openurp.webapp.apps.party.wenming.person.action.ApplyAction;
import org.openurp.webapp.apps.party.wenming.person.action.AuditAction;
import org.openurp.webapp.apps.party.wenming.person.action.ManageAction;
import org.openurp.webapp.apps.party.wenming.person.action.VoteAction;

public class DefaultModule extends AbstractBindModule {

  @Override
  protected void doBinding() {
    bind(ApplyAction.class, AuditAction.class, ManageAction.class,VoteAction.class);
  }
}
