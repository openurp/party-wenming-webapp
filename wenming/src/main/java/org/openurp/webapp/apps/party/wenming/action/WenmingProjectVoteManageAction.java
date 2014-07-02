package org.openurp.webapp.apps.party.wenming.action;

import java.util.List;

import org.openurp.webapp.apps.party.wenming.depart.action.WenMingAction;
import org.openurp.webapp.apps.party.wenming.model.WenmingSession;

public class WenmingProjectVoteManageAction extends WenMingAction{
  

  @Override
  public String index() throws Exception {
    List<WenmingSession> wenmingSessions = wenMingService.findWenmingSessions();
    Integer sessionId = getInt("session.id");
    WenmingSession wenmingSession = null;
    if (sessionId != null) {
      wenmingSession = entityDao.get(WenmingSession.class, sessionId);
    } else if (!wenmingSessions.isEmpty()) {
      wenmingSession = wenmingSessions.get(0);
    }
    put("wenmingSessions", wenmingSessions);
    put("wenmingSession", wenmingSession);
    return super.index();
  }


}
