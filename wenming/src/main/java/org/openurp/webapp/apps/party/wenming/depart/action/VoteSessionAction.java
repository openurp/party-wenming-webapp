package org.openurp.webapp.apps.party.wenming.depart.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javassist.expr.NewArray;

import javax.xml.validation.Schema;

import org.beangle.commons.dao.query.builder.OqlBuilder;
import org.beangle.commons.entity.Entity;
import org.openurp.kernel.base.unit.model.Department;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessSchema;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessSession;
import org.openurp.webapp.apps.party.wenming.depart.model.Supervisor;
import org.openurp.webapp.apps.party.wenming.depart.model.VoteSession;

public class VoteSessionAction extends WenMingAction{
  
  @Override
  protected String getEntityName() {
    return VoteSession.class.getName();
  }
  
  @Override
  protected void editSetting(Entity<?> entity) {
    VoteSession voteSession = (VoteSession) entity;
    List<Supervisor> voters = findVoters();
    voters.removeAll(voteSession.getVoters());
    put("voters", voters);
    if (voteSession.getSession() == null) {
      OqlBuilder<AssessSession> builder = OqlBuilder.from(AssessSession.class,"ss");
      builder.where("ss.beginOn < :now",new Date());
      builder.orderBy("ss.beginOn desc");
      builder.limit(1, 1);
      List<AssessSession> assessSessions = entityDao.search(builder);
      if (assessSessions.isEmpty()){
        throw new RuntimeException("不存在测评批次，无法创建投票批次");
      }
      voteSession.setSession(assessSessions.get(0));
    }
    List<Department> departments = new ArrayList<Department>();
    List<AssessSchema> schemas = voteSession.getSession().getSchemas();
    for (AssessSchema schema : schemas){
      departments.addAll(schema.getDeparts());
    }
    departments.removeAll(voteSession.getDepartments());
    put("departments", departments);
    
  }

  private List<Supervisor> findVoters() {
    OqlBuilder<Supervisor> builder = OqlBuilder.from(Supervisor.class);
    builder.orderBy("fullname");
    return entityDao.search(builder);
  }

  @Override
  protected String saveAndForward(Entity<?> entity) {
    VoteSession voteSession = (VoteSession) entity;
    Integer[] voterIds = getAll("voterId", Integer.class);
    voteSession.getVoters().clear();
    voteSession.getVoters().addAll(entityDao.get(Supervisor.class, voterIds));
    Integer[] departmentIds = getAll("departId", Integer.class);
    voteSession.getDepartments().clear();
    voteSession.getDepartments().addAll(entityDao.get(Department.class, departmentIds));
    return super.saveAndForward(entity);
  }
}
