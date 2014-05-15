package org.openurp.webapp.apps.party.wenming.project.action;

import org.openurp.webapp.apps.party.wenming.action.WenMingProjectDepartAuditAction;
import org.openurp.webapp.apps.party.wenming.model.WenmingType;
import org.openurp.webapp.apps.party.wenming.project.model.GoodProject;

public class AuditAction  extends WenMingProjectDepartAuditAction {

  @Override
  protected String getEntityName() {
    return GoodProject.class.getName();
  }
  
  @Override
  protected WenmingType getWenmingType() {
    return WenmingType.Project;
  }

  
}
