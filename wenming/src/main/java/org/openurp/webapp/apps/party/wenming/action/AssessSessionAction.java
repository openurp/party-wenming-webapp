package org.openurp.webapp.apps.party.wenming.action;

import java.util.Date;
import java.util.List;

import org.beangle.commons.dao.query.builder.OqlBuilder;
import org.beangle.commons.entity.Entity;
import org.openurp.kernel.base.unit.model.Department;
import org.openurp.webapp.apps.party.wenming.model.AssessSchema;
import org.openurp.webapp.apps.party.wenming.model.AssessSession;

/**
 * 评估批次控制器
 * 
 * @author chii
 */
public class AssessSessionAction extends WenMingAction {

  @Override
  protected String getEntityName() {
    return AssessSession.class.getName();
  }

  @Override
  protected void editSetting(Entity<?> entity) {
    putSchemas();
  }

  @Override
  protected String saveAndForward(Entity<?> entity) {
    AssessSession session = (AssessSession) entity;
    Integer[] schemaIds = getAll("schemaId", Integer.class);
    session.getSchemas().clear();
    session.getSchemas().addAll(entityDao.get(AssessSchema.class, schemaIds));
    session.setUpdatedAt(new Date());
    if (session.getCreatedAt() == null) {
      session.setCreatedAt(new Date());
    }
    return super.saveAndForward(entity);
  }

}
