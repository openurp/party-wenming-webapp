package org.openurp.webapp.apps.party.wenming.service.impl;

import java.util.List;

import org.beangle.commons.dao.impl.BaseServiceImpl;
import org.beangle.commons.dao.query.builder.OqlBuilder;
import org.openurp.kernel.base.unit.model.Department;
import org.openurp.webapp.apps.party.wenming.model.AssessSchema;
import org.openurp.webapp.apps.party.wenming.service.WenMingService;

public class WenMingServiceImpl extends BaseServiceImpl implements WenMingService {

	@Override
	public List<AssessSchema> findSchema() {
		OqlBuilder<AssessSchema> query = OqlBuilder.from(AssessSchema.class);
		query.orderBy("name");
		return entityDao.search(query);
	}

	@Override
	public List<Department> findDepartment() {
		OqlBuilder<Department> query = OqlBuilder.from(Department.class);
		query.orderBy("name");
		return entityDao.search(query);
	}

}
