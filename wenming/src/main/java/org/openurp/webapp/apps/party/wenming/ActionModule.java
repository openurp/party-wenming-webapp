package org.openurp.webapp.apps.party.wenming;

import org.beangle.commons.inject.bind.AbstractBindModule;
import org.openurp.webapp.apps.party.wenming.action.AttachmentAction;
import org.openurp.webapp.apps.party.wenming.action.AttachmentHelper;
import org.openurp.webapp.apps.party.wenming.action.GoodObjectVoteStatAction;
import org.openurp.webapp.apps.party.wenming.action.WenmingProjectVoterAction;
import org.openurp.webapp.apps.party.wenming.action.WenmingSessionAction;
import org.openurp.webapp.apps.party.wenming.model.WenmingProjectVoter;

public class ActionModule extends AbstractBindModule {
  @Override
  protected void doBinding() {
    bind(AttachmentAction.class, WenmingSessionAction.class,AttachmentHelper.class,WenmingProjectVoterAction.class);
    bind(GoodObjectVoteStatAction.class);
  }
}
