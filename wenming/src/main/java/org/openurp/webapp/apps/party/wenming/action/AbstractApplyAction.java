package org.openurp.webapp.apps.party.wenming.action;

import org.beangle.ems.web.action.SecurityActionSupport;
import org.openurp.webapp.apps.party.wenming.model.AssessApply;
import org.openurp.webapp.apps.party.wenming.service.DepartmentService;
import org.openurp.webapp.apps.party.wenming.service.WenMingService;

/**
 * 测评申请相关的抽象类
 * 
 * @author chaostone
 */
public abstract class AbstractApplyAction extends SecurityActionSupport {
  protected WenMingService wenMingService;

  protected DepartmentService departmentService;

  protected String getEntityName() {
    return AssessApply.class.getName();
  }

  public void setWenMingService(WenMingService wenMingService) {
    this.wenMingService = wenMingService;
  }

  public void setDepartmentService(DepartmentService departmentService) {
    this.departmentService = departmentService;
  }

}
