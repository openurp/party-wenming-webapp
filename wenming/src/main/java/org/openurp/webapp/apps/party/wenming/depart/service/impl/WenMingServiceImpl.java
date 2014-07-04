package org.openurp.webapp.apps.party.wenming.depart.service.impl;

import java.util.Date;
import java.util.List;

import org.beangle.commons.dao.impl.BaseServiceImpl;
import org.beangle.commons.dao.query.builder.OqlBuilder;
import org.openurp.kernel.base.unit.model.Department;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessItem;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessSchema;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessSession;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessType;
import org.openurp.webapp.apps.party.wenming.depart.model.VoteSession;
import org.openurp.webapp.apps.party.wenming.depart.service.QueryInvoker;
import org.openurp.webapp.apps.party.wenming.depart.service.WenMingService;
import org.openurp.webapp.apps.party.wenming.model.WenmingSession;
import org.openurp.webapp.apps.party.wenming.model.WenmingVoteSession;

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
    if(session != null){
      for (AssessSchema schema : session.getSchemas()) {
        if (schema.getDeparts().contains(department)) {
          return schema;
        }
      }
    }
    return null;
  }

  @Override
  public List<AssessSession> findSessions(Department department) {
    OqlBuilder<AssessSession> query = OqlBuilder.from(AssessSession.class, "o");
//    query.where("o.enabled=true");
    query.orderBy("o.beginOn desc");
    query.join("o.schemas", "s");
    query.join("s.departs", "d");
    query.where("d = :d", department);
    query.select("distinct o");
    return entityDao.search(query);
  }

  @Override
  public List<VoteSession> findVoteSession(Department department) {
    OqlBuilder<VoteSession> query = OqlBuilder.from(VoteSession.class, "o");
    query.orderBy("o.beginOn desc");
    query.join("o.session.schemas", "s");
    query.join("s.departs", "d");
    query.where("d = :d", department);
    query.select("distinct o");
    return entityDao.search(query);
  }

  @Override
  public List<AssessSession> findAssessSessions() {
    OqlBuilder<AssessSession> query = OqlBuilder.from(AssessSession.class, "o");
//    query.where("o.enabled=true");
    query.orderBy("o.beginOn desc");
    return entityDao.search(query);
  }

  public List<AssessItem> findAssessItem(AssessSchema schema, QueryInvoker invoker) {
    OqlBuilder<AssessItem> query = OqlBuilder.from(AssessItem.class, "o");
    query.where("o.group.schema = :schema", schema);
    query.orderBy("o.group.indexno, o.orderNumber");
    if(invoker != null){
      invoker.doth(query);
    }
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
  
  @Override
  public List<AssessItem> findAssessItem(AssessSchema schema) {
    return findAssessItem(schema, null);
  }

  @Override
  public List<WenmingSession> findWenmingSessions() {
    OqlBuilder<WenmingSession> query = OqlBuilder.from(WenmingSession.class, "o");
    query.orderBy("o.beginOn desc");
    return entityDao.search(query);
  }
  
  @Override
  public WenmingSession getWenmingSessionByAssessTime() {
    OqlBuilder<WenmingSession> query = OqlBuilder.from(WenmingSession.class, "o");
    query.where("beginOn <= :now and endOn >= :now", new Date());
    List<WenmingSession> list = entityDao.search(query);
    return list.isEmpty() ? null : list.get(0);
  }

  @Override
  public VoteSession getVoteSession(Integer supervisorId) {
    OqlBuilder<VoteSession> query = OqlBuilder.from(VoteSession.class, "o");
    query.where("beginOn <= :now and endOn >= :now", new Date());
    query.join("o.voters", "v");
    query.where("v.id = :supervisorId", supervisorId);
    List<VoteSession> list = entityDao.search(query);
    return list.isEmpty() ? null : list.get(0);
  }

  @Override
  public List<VoteSession> findVoteSession(Integer supervisorId) {
    OqlBuilder<VoteSession> query = OqlBuilder.from(VoteSession.class, "o");
    query.join("o.voters", "v");
    query.where("v.id = :supervisorId", supervisorId);
    query.orderBy("o.beginOn desc");
    return entityDao.search(query);
  }

  @Override
  public WenmingVoteSession getWenmingVoteSession() {
    OqlBuilder<WenmingVoteSession> query = OqlBuilder.from(WenmingVoteSession.class, "o");
    query.where("beginOn <= :now and endOn >= :now", new Date());
    List<WenmingVoteSession> list = entityDao.search(query);
    return list.isEmpty() ? null : list.get(0);
  }

  @Override
  public List<WenmingVoteSession> findWenmingVoteSession() {
    OqlBuilder<WenmingVoteSession> query = OqlBuilder.from(WenmingVoteSession.class, "o");
    query.orderBy("o.beginOn desc");
    return entityDao.search(query);
  }

}
