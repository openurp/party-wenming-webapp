package org.openurp.webapp.apps.party.wenming.depart.action;

import java.util.List;

import org.beangle.security.blueprint.User;
import org.openurp.webapp.apps.party.wenming.depart.model.Supervisor;


public class SupervisorCommAction extends WenMingAction{
  public static final String SUPERVISOR_ID = "SUPERVISOR_ID";

  protected Integer getSupervisorId() {
    Integer supervisorId = (Integer) getSession().get(SUPERVISOR_ID);
    try {
      if(supervisorId == null && getUserId() != null){
        User user = getUrpUser();
        List<Supervisor> list = entityDao.get(Supervisor.class, "name", user.getName());
        if(!list.isEmpty()){
          supervisorId = list.get(0).getId();
          getSession().put(SUPERVISOR_ID, supervisorId);
        }
      }
    } catch (Exception e) {
    }
    return supervisorId;
  }
  
  protected Supervisor getSupervisor(){
    return entityDao.get(Supervisor.class, getSupervisorId());
  }
  
  
}
