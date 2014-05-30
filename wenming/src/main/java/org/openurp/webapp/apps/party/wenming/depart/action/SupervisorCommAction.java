package org.openurp.webapp.apps.party.wenming.depart.action;

import org.openurp.webapp.apps.party.wenming.depart.model.Supervisor;


public class SupervisorCommAction extends WenMingAction{
  public static final String SUPERVISOR_ID = "SUPERVISOR_ID";

  protected Integer getSupervisorId() {
    return (Integer) getSession().get(SUPERVISOR_ID);
  }
  
  protected Supervisor getSupervisor(){
    return entityDao.get(Supervisor.class, getSupervisorId());
  }
  
  
}
