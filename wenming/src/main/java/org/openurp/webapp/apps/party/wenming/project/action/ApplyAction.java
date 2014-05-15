package org.openurp.webapp.apps.party.wenming.project.action;

import org.apache.commons.lang.StringUtils;
import org.beangle.commons.entity.Entity;
import org.openurp.webapp.apps.party.wenming.action.WenMingProjectAction;
import org.openurp.webapp.apps.party.wenming.model.WenmingType;
import org.openurp.webapp.apps.party.wenming.project.model.GoodProject;

public class ApplyAction extends WenMingProjectAction {

  @Override
  protected String getEntityName() {
    return GoodProject.class.getName();
  }

  @Override
  protected WenmingType getWenmingType() {
    return WenmingType.Project;
  }
  
  @Override
  protected void editSetting(Entity<?> entity) {
    GoodProject obj = (GoodProject) entity;
    obj.setPlan(StringUtils.replace(obj.getPlan(), "<br>", "\n"));
    super.editSetting(entity);
  }
  
  @Override
  protected String saveAndForward(Entity<?> entity) {
    GoodProject obj = (GoodProject) entity;
    obj.setPlan(StringUtils.replace(obj.getPlan(), "\n", "<br>"));
    return super.saveAndForward(entity);
  }

}
