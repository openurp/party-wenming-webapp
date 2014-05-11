package org.openurp.webapp.apps.party.wenming.action;

import org.beangle.commons.entity.Entity;
import org.openurp.webapp.apps.party.wenming.model.AssessItem;
import org.openurp.webapp.apps.party.wenming.model.AssessSchema;
import org.openurp.webapp.apps.party.wenming.service.AssessItemGroupService;

/**
 * 评价指标管理
 * @author chii
 *
 */
public class AssessItemAction extends WenMingAction {
	
	private AssessItemGroupService assessItemGroupService;
	
	public void setAssessItemGroupService(AssessItemGroupService assessItemGroupService) {
		this.assessItemGroupService = assessItemGroupService;
	}
	
	@Override
	protected String getEntityName() {
		return AssessItem.class.getName();
	}
	
	@Override
	protected void indexSetting() {
		putSchemas();
		super.indexSetting();
	}
	
	@Override
	protected void editSetting(Entity<?> entity) {
		AssessSchema schema = entityDao.get(AssessSchema.class.getName(), getInt("schema.id"));
		put("schema", schema);
		put("groups", assessItemGroupService.findBySchema(schema.getId()));
		put("departments", wenMingService.findDepartment());
		super.editSetting(entity);
	}
	
	@Override
	protected String saveAndForward(Entity<?> entity) {
		return super.saveAndForward(entity);
	}

}
