package org.openurp.webapp.apps.party.wenming.action;

import org.openurp.webapp.apps.party.wenming.model.AbstractAssessInfo;
import org.openurp.webapp.apps.party.wenming.model.AbstractAssessItemInfo;
import org.openurp.webapp.apps.party.wenming.model.FuncDepartAssess;
import org.openurp.webapp.apps.party.wenming.model.FuncDepartAssessItem;


/**
 * 职能部门评院系
 * 
 * @author chaostone
 * 
 */
public class FuncDepartAssessAction extends AssessAction {

  @Override
  protected String getEntityName() {
    return FuncDepartAssess.class.getName();
  }

  @Override
  protected Class<? extends AbstractAssessInfo> getAssessClass() {
    return FuncDepartAssess.class;
  }

  @Override
  protected Class<? extends AbstractAssessItemInfo> getItemClass() {
    return FuncDepartAssessItem.class;
  }
  
}
