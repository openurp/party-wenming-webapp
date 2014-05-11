package org.openurp.webapp.apps.party.wenming.action;

import org.beangle.commons.dao.query.builder.OqlBuilder;
import org.beangle.commons.entity.Entity;
import org.openurp.webapp.apps.party.wenming.model.AssessItemGroup;
import org.openurp.webapp.apps.party.wenming.service.AssessItemGroupService;

/**
 * 测评指标分类管理
 * 
 * @author chii
 * 
 */
public class AssessItemGroupAction extends WenMingAction {

	private AssessItemGroupService assessItemGroupService;

	public void setAssessItemGroupService(AssessItemGroupService assessItemGroupService) {
		this.assessItemGroupService = assessItemGroupService;
	}

	@Override
	protected String getEntityName() {
		return AssessItemGroup.class.getName();
	}

	@Override
	protected void indexSetting() {
		putSchemas();
		super.indexSetting();
	}

	@Override
	protected void editSetting(Entity<?> entity) {
		putSchemas();
		OqlBuilder<AssessItemGroup> groupsquery = OqlBuilder.from(AssessItemGroup.class);
		if (entity.getId() != null) {
			groupsquery.where("id <> :id", entity.getId());
		}
		put("groups", entityDao.search(groupsquery));
		super.editSetting(entity);
	}

	@Override
	protected String saveAndForward(Entity<?> entity) {
		AssessItemGroup group = (AssessItemGroup) entity;
		int indexno = getInt("indexno");
		AssessItemGroup parent = null;
		Integer newParentId = getInt("parent.id");
		if (null != newParentId)
			parent = entityDao.get(AssessItemGroup.class, newParentId);
		assessItemGroupService.move(group, parent, indexno);
		entityDao.saveOrUpdate(group);
		return super.saveAndForward(entity);
	}
	
	/**
	 * 根据方案ID查询评价指标分类
	 * @return
	 */
	public String groups(){
		Integer schemaId = getInt("id");
		put("groups", assessItemGroupService.findBySchema(schemaId));
		return forward();
	}

}
