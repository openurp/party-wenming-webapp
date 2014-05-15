package org.openurp.webapp.apps.party.wenming.action;

import org.beangle.ems.web.action.SecurityActionSupport;
import org.openurp.webapp.apps.party.wenming.model.WenmingSession;

/**
 * 文明项目申报批次
 * 
 * @author chaostone
 */
public class WenmingSessionAction extends SecurityActionSupport {

  @Override
  protected String getEntityName() {
    return WenmingSession.class.getName();
  }

}
