package org.openurp.webapp.apps.party.wenming.project.action;

import org.openurp.webapp.apps.party.wenming.action.WenMingProjectApplyAction;
import org.openurp.webapp.apps.party.wenming.model.WenmingType;
import org.openurp.webapp.apps.party.wenming.project.model.GoodProject;
import org.openurp.webapp.apps.party.wenming.project.model.GoodProjectFinalSummary;
import org.openurp.webapp.apps.party.wenming.project.model.GoodProjectMiddleSummary;

public class ApplyAction extends WenMingProjectApplyAction {

  @Override
  protected String getEntityName() {
    return GoodProject.class.getName();
  }

  @Override
  protected WenmingType getWenmingType() {
    return WenmingType.Project;
  }

  @Override
  protected Class<?> getFinalSummaryClass() {
    return GoodProjectFinalSummary.class;
  }
  
  @Override
  protected Class<?> getMiddleSummaryClass() {
    return GoodProjectMiddleSummary.class;
  }

}
