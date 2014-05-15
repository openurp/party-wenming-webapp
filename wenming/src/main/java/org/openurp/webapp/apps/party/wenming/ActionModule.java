package org.openurp.webapp.apps.party.wenming;

import org.beangle.commons.inject.bind.AbstractBindModule;
import org.openurp.webapp.apps.party.wenming.action.AttachmentAction;
import org.openurp.webapp.apps.party.wenming.action.AttachmentHelper;
import org.openurp.webapp.apps.party.wenming.action.WenmingSessionAction;

public class ActionModule extends AbstractBindModule {
  @Override
  protected void doBinding() {
    bind(AttachmentAction.class, WenmingSessionAction.class,AttachmentHelper.class);
  }
}
