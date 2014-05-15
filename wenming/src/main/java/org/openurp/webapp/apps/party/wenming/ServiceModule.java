package org.openurp.webapp.apps.party.wenming;

import org.beangle.commons.inject.bind.AbstractBindModule;
import org.openurp.webapp.apps.party.wenming.service.impl.DepartmentServiceImpl;

public class ServiceModule extends AbstractBindModule {
  @Override
  protected void doBinding() {
    bind(DepartmentServiceImpl.class);
  }

}
