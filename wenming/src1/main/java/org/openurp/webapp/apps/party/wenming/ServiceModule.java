package org.openurp.webapp.apps.party.wenming;

import org.beangle.commons.inject.bind.AbstractBindModule;
import org.openurp.webapp.apps.party.wenming.action.AssessBatchAction;

public class ServiceModule extends AbstractBindModule{

	@Override
	protected void doBinding() {
		bind(AssessBatchAction.class);
	}

}
