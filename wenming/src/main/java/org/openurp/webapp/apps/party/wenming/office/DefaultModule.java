package org.openurp.webapp.apps.party.wenming.office;

import org.beangle.commons.inject.bind.AbstractBindModule;
import org.openurp.webapp.apps.party.wenming.office.action.ApplyAction;
import org.openurp.webapp.apps.party.wenming.office.action.AuditAction;
import org.openurp.webapp.apps.party.wenming.office.action.ManageAction;

public class DefaultModule extends AbstractBindModule {

  @Override
  protected void doBinding() {
    bind(ApplyAction.class, AuditAction.class, ManageAction.class);
  }
}
