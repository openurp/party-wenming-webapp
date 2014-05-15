package org.openurp.webapp.apps.party.wenming.project.action;

import org.openurp.webapp.apps.party.wenming.action.WenMingProjectAuditAction;
import org.openurp.webapp.apps.party.wenming.model.WenmingType;
import org.openurp.webapp.apps.party.wenming.project.model.GoodProject;

public class ManageAction extends WenMingProjectAuditAction {

  @Override
  protected String getEntityName() {
    return GoodProject.class.getName();
  }

  @Override
  protected WenmingType getWenmingType() {
    return WenmingType.Project;
  }

}
