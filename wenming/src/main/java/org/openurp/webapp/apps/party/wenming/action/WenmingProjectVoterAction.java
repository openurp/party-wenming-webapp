package org.openurp.webapp.apps.party.wenming.action;

import org.beangle.ems.web.action.SecurityActionSupport;
import org.openurp.webapp.apps.party.wenming.model.WenmingProjectVoter;

public class WenmingProjectVoterAction extends SecurityActionSupport {
  @Override
  protected String getEntityName() {
    return WenmingProjectVoter.class.getName();
  }

}
