package org.openurp.webapp.apps.party.wenming;

import org.beangle.commons.entity.orm.AbstractPersistModule;
import org.openurp.webapp.apps.party.wenming.model.AssessBatch;

public class PersistModule extends AbstractPersistModule {

	@SuppressWarnings("unchecked")
	@Override
	protected void doConfig() {
		add(AssessBatch.class);
	}

}
