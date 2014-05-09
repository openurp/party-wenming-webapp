package org.openurp.webapp.apps.party.wenming.action;

import java.util.Date;

import org.beangle.commons.entity.Entity;
import org.openurp.webapp.apps.party.wenming.model.AssessSession;


/**
 * 评估批次控制器
 * @author chii
 *
 */
public class AssessSessionAction extends WenMingAction {
	
	@Override
	protected String getEntityName() {
		return AssessSession.class.getName();
	}
	
	@Override
	protected String saveAndForward(Entity<?> entity) {
		AssessSession session = (AssessSession) entity;
		session.setUpdatedAt(new Date());
		if(session.getCreatedAt() == null){
			session.setCreatedAt(new Date());
		}
		return super.saveAndForward(entity);
	}

}
