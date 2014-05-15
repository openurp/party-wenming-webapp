package org.openurp.webapp.apps.party.wenming.project;

import org.beangle.commons.inject.bind.AbstractBindModule;
import org.openurp.webapp.apps.party.wenming.project.action.ApplyAction;
import org.openurp.webapp.apps.party.wenming.project.action.AuditAction;
import org.openurp.webapp.apps.party.wenming.project.action.ManageAction;

public class DefaultModule extends AbstractBindModule {

  @Override
  protected void doBinding() {
    bind(ApplyAction.class,AuditAction.class,ManageAction.class);
  }
}
