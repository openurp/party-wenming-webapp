package org.openurp.webapp.apps.party.wenming.depart.action;

import java.util.List;

import org.beangle.commons.dao.query.builder.SqlBuilder;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessSession;
import org.openurp.webapp.apps.party.wenming.depart.model.VoteSession;

public class VoteManageAction extends WenMingAction{
  
  @Override
  public String index() throws Exception {
    List<VoteSession> assessSessions = wenMingService.findVoteSession(null);
    Integer sessionId = getInt("session.id");
    VoteSession assessSession = null;
    if (sessionId != null) {
      assessSession = entityDao.get(VoteSession.class, sessionId);
    } else if (!assessSessions.isEmpty()) {
      assessSession = assessSessions.get(0);
    }
    put("assessSessions", assessSessions);
    put("assessSession", assessSession);
    return super.index();
  }
  
  public String info() {
    Integer sessionId = getInt("session.id");
    String sql = "select d.name, count(*) "
        + " from party.wm_assess_votes v"
        + " join base.departments d on d.id = v.department_id"
        + " where ayes = true and v.session_id = :sessionId and d.teaching=true and v.submit=true"
        + " group by d.id, d.name order by count(*) desc";
    String sql2 = "select d.name, count(*) "
        + " from party.wm_assess_votes v"
        + " join base.departments d on d.id = v.department_id"
        + " where ayes = true and v.session_id = :sessionId and d.teaching=false and v.submit=true"
        + " group by d.id, d.name order by count(*) desc";
    SqlBuilder query = SqlBuilder.sql(sql);
    SqlBuilder query2 = SqlBuilder.sql(sql2);
    query.param("sessionId", sessionId);
    query2.param("sessionId", sessionId);
    List<Object[]> datas = entityDao.search(query);
    List<Object[]> datas2 = entityDao.search(query2);
    put("datas", datas);
    put("datas2", datas2);
    return forward();
  }

}
