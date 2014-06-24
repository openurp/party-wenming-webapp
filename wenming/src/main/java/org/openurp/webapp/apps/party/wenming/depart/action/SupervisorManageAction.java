package org.openurp.webapp.apps.party.wenming.depart.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.beangle.commons.dao.query.builder.OqlBuilder;
import org.openurp.kernel.base.unit.model.Department;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessSession;
import org.openurp.webapp.apps.party.wenming.depart.model.SupervisorAssess;

public class SupervisorManageAction extends WenMingAction{
  
  @Override
  public String index() throws Exception {
    List<AssessSession> assessSessions = wenMingService.findAssessSessions();
    Integer sessionId = getInt("session.id");
    AssessSession assessSession = null;
    if (sessionId != null) {
      assessSession = entityDao.get(AssessSession.class, sessionId);
    } else if (!assessSessions.isEmpty()) {
      assessSession = assessSessions.get(0);
    }
    put("assessSessions", assessSessions);
    put("assessSession", assessSession);
    return super.index();
  }
  @Override
  public String info() throws Exception {
    Integer sessionId = getInt("session.id");
    List<SupervisorAssess> supervisorAssesses = findSupervisorAssess(sessionId);
    Map<Long, Object[]> map = new HashMap<Long, Object[]>();
    for(SupervisorAssess supervisorAssess : supervisorAssesses){
      Object[] oo = map.get(supervisorAssess.getAssessBy().getId());
      if(oo == null){
        oo = new Object[]{supervisorAssess.getAssessBy(), new ArrayList<Department>(),supervisorAssess.getState(),supervisorAssess.getAssessAt()};
        map.put(supervisorAssess.getAssessBy().getId(), oo);
      }
      ((ArrayList<Department>)oo[1]).add(supervisorAssess.getDepartment());
    }
    put("datas", map.values());
    return forward();
  }
  private List<SupervisorAssess> findSupervisorAssess(Integer sessionId) {
    OqlBuilder<SupervisorAssess> builder = OqlBuilder.from(SupervisorAssess.class, "sa");
    builder.where("sa.session.id=:sessionId",sessionId);
    List<SupervisorAssess> supervisorAssesses = entityDao.search(builder);
    return supervisorAssesses;
  }

}
