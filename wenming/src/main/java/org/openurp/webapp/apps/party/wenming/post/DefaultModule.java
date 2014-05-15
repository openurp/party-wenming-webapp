package org.openurp.webapp.apps.party.wenming.post;

import org.beangle.commons.inject.bind.AbstractBindModule;
import org.openurp.webapp.apps.party.wenming.post.action.ApplyAction;
import org.openurp.webapp.apps.party.wenming.post.action.AuditAction;
import org.openurp.webapp.apps.party.wenming.post.action.ManageAction;

public class DefaultModule extends AbstractBindModule {

  @Override
  protected void doBinding() {
    bind(ApplyAction.class, AuditAction.class, ManageAction.class);
  }
}
