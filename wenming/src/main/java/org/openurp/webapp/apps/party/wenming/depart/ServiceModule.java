package org.openurp.webapp.apps.party.wenming.depart;

import org.beangle.commons.inject.bind.AbstractBindModule;
import org.openurp.webapp.apps.party.wenming.depart.service.impl.AssessItemGroupServiceImpl;
import org.openurp.webapp.apps.party.wenming.depart.service.impl.MutualAssessServiceImpl;
import org.openurp.webapp.apps.party.wenming.depart.service.impl.WenMingServiceImpl;

public class ServiceModule extends AbstractBindModule {

  @Override
  protected void doBinding() {
    bind(WenMingServiceImpl.class, AssessItemGroupServiceImpl.class);
    bind(MutualAssessServiceImpl.class);
  }

}
