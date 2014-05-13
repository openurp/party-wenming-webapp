package org.openurp.webapp.apps.party.wenming.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.beangle.commons.collection.CollectUtils;
import org.beangle.commons.dao.impl.BaseServiceImpl;
import org.beangle.commons.dao.query.builder.OqlBuilder;
import org.openurp.kernel.base.unit.model.Department;
import org.openurp.webapp.apps.party.wenming.model.AssessItem;
import org.openurp.webapp.apps.party.wenming.model.AssessSchema;
import org.openurp.webapp.apps.party.wenming.model.AssessSession;
import org.openurp.webapp.apps.party.wenming.model.AssessType;
import org.openurp.webapp.apps.party.wenming.service.QueryInvoker;
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

  @Override
  public AssessSession getAssessSessionByAssessTime() {
    OqlBuilder<AssessSession> query = OqlBuilder.from(AssessSession.class, "o");
    query.where("beginOn <= :now and endOn >= :now", new Date());
    query.where("enabled = true");
    List<AssessSession> list = entityDao.search(query);
    return list.isEmpty() ? null : list.get(0);
  }

  @Override
  public AssessSchema getSchema(AssessSession session, Department department) {
    // OqlBuilder<AssessSchema> query = OqlBuilder.from(AssessSchema.class,
    // "o");
    // query.join("o.departs", "d");
    // query.where("d = :d", department);
    // List<AssessSchema> list = entityDao.search(query);
    // return list.isEmpty() ? null : list.get(0);
    for (AssessSchema schema : session.getSchemas()) {
      if (schema.getDeparts().contains(department)) {
        return schema;
      }
    }
    return null;
  }

  @Override
  public List<AssessSession> findSessions(Department department) {
    OqlBuilder<AssessSession> query = OqlBuilder.from(AssessSession.class, "o");
    query.where("o.enabled=true");
    query.orderBy("o.beginOn desc");
    List<AssessSession> sessions = entityDao.search(query);
    List<AssessSession> matched = CollectUtils.newArrayList();
    for (AssessSession session : sessions) {
      for (AssessSchema schema : session.getSchemas()) {
        if (schema.getDeparts().contains(department)) {
          matched.add(session);
          break;
        }
      }
    }
    return matched;
  }

  @Override
  public List<AssessSession> findSessions() {
    OqlBuilder<AssessSession> query = OqlBuilder.from(AssessSession.class, "o");
    query.where("o.enabled=true");
    query.orderBy("o.beginOn desc");
    return entityDao.search(query);
  }

  @Override
  public List<AssessItem> findAssessItemByMutual(AssessSchema schema) {
    return findAssessItem(schema, new QueryInvoker() {
      @Override
      public void doth(OqlBuilder<?> query) {
        query.where("o.assessType = :assessType", AssessType.MUTUAL);
      }
    });
  }

  private List<AssessItem> findAssessItem(AssessSchema schema, QueryInvoker invoker) {
    OqlBuilder<AssessItem> query = OqlBuilder.from(AssessItem.class, "o");
    query.where("o.group.schema = :schema", schema);
    query.orderBy("o.group.indexno, o.orderNumber");
    invoker.doth(query);
    return entityDao.search(query);
  }

  @Override
  public List<AssessItem> findAssessItemBySupervisor(AssessSchema schema) {
    return findAssessItem(schema, new QueryInvoker() {
      @Override
      public void doth(OqlBuilder<?> query) {
        query.where("o.assessType = :assessType", AssessType.SUPERVISOR);
      }
    });
  }
}
