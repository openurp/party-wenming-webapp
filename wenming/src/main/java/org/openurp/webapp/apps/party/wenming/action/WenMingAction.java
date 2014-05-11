package org.openurp.webapp.apps.party.wenming.action;

import org.beangle.ems.web.action.SecurityActionSupport;
import org.openurp.kernel.base.unit.model.Department;
import org.openurp.kernel.base.unit.model.UrpUserBean;
import org.openurp.webapp.apps.party.wenming.service.WenMingService;

public class WenMingAction extends SecurityActionSupport {
	
	protected WenMingService wenMingService;
	
	public void setWenMingService(WenMingService wenMingService) {
		this.wenMingService = wenMingService;
	}
	
	protected void putSchemas(){
		put("schemas", wenMingService.findSchema());
	}

	protected Department getDepartment() {
    return getUrpUser().getDepartment();
  }

	protected UrpUserBean getUrpUser() {
    return entityDao.get(UrpUserBean.class, getUserId());
  }
}
