package org.openurp.webapp.apps.party.wenming.action;

import org.beangle.commons.entity.Entity;
import org.beangle.ems.web.action.SecurityActionSupport;
import org.openurp.webapp.apps.party.wenming.model.WenmingSession;
import org.openurp.webapp.apps.party.wenming.model.WenmingType;

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

  @Override
  protected String saveAndForward(Entity<?> entity) {
    WenmingSession session = (WenmingSession) entity;
    String[] typeIds = getAll("wenmingTypeId", String.class);
    session.getTypes().clear();
    for (String typeId : typeIds) {
      session.getTypes().add(WenmingType.valueOf(typeId));
    }
    return super.saveAndForward(entity);
  }

}
