package org.openurp.webapp.apps.party.wenming.depart.action;

import org.beangle.commons.entity.Entity;
import org.beangle.ems.web.action.SecurityActionSupport;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessBonusType;
import org.openurp.webapp.apps.party.wenming.depart.model.Supervisor;

/**
 * 督察组成员维护
 * 
 * @author chaostone
 */
public class SupervisorAction extends SecurityActionSupport {
  @Override
  protected String getEntityName() {
    return Supervisor.class.getName();
  }
}
