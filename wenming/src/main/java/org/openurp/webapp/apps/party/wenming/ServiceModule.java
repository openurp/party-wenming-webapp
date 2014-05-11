package org.openurp.webapp.apps.party.wenming;

import org.beangle.commons.inject.bind.AbstractBindModule;
import org.openurp.webapp.apps.party.wenming.service.impl.AssessItemGroupServiceImpl;
import org.openurp.webapp.apps.party.wenming.service.impl.WenMingServiceImpl;

public class ServiceModule extends AbstractBindModule{

	@Override
	protected void doBinding() {
		bind(WenMingServiceImpl.class, AssessItemGroupServiceImpl.class);
	}

}
