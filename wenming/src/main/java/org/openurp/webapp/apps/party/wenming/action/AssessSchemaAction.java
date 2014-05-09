package org.openurp.webapp.apps.party.wenming.action;

import java.util.Date;
import java.util.List;

import org.beangle.commons.dao.query.builder.OqlBuilder;
import org.beangle.commons.entity.Entity;
import org.openurp.kernel.base.unit.model.Department;
import org.openurp.webapp.apps.party.wenming.model.AssessSchema;

/**
 * 评价方案控制器
 * 
 * @author xinzhou
 * **/

public class AssessSchemaAction extends WenMingAction {
	@Override
	protected String getEntityName() {
		return AssessSchema.class.getName();
	}

	private List<Department> getAllDeparts() {
		OqlBuilder<Department> builder = OqlBuilder.from(Department.class,
				"depart");
		builder.where("depart.endOn is null or depart.endOn>=:now", new Date(
				System.currentTimeMillis()));
		builder.orderBy("depart.code");
		return entityDao.search(builder);
	}

	@Override
	protected void editSetting(Entity<?> entity) {
		put("departments", getAllDeparts());
		super.editSetting(entity);
	}

	@Override
	protected String saveAndForward(Entity<?> entity) {
		AssessSchema schema = (AssessSchema) entity;
		Integer[] departIds = getAll("departId", Integer.class);
		schema.getDeparts().clear();
		schema.getDeparts().addAll(entityDao.get(Department.class, departIds));
		schema.setUpdatedAt(new Date());
		if (schema.getCreatedAt() == null) {
			schema.setCreatedAt(new Date());
		}
		return super.saveAndForward(schema);
	}
}
