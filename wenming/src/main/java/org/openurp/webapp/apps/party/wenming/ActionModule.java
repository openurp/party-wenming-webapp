package org.openurp.webapp.apps.party.wenming;

import org.beangle.commons.inject.bind.AbstractBindModule;
import org.openurp.webapp.apps.party.wenming.action.AssessSchemaAction;
import org.openurp.webapp.apps.party.wenming.action.AssessSessionAction;

public class ActionModule extends AbstractBindModule {

	@Override
	protected void doBinding() {
		bind(AssessSessionAction.class);
		bind(AssessSchemaAction.class);
	}

}
