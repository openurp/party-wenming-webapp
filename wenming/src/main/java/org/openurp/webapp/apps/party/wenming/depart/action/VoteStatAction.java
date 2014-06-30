package org.openurp.webapp.apps.party.wenming.depart.action;

import java.util.List;

import org.beangle.commons.dao.query.builder.SqlBuilder;
import org.beangle.ems.web.action.SecurityActionSupport;
import org.beangle.security.blueprint.User;
import org.openurp.kernel.base.unit.model.UrpUserBean;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessSession;
import org.openurp.webapp.apps.party.wenming.depart.service.WenMingService;

/**
 * 测评统计
 * 
 * @author chaostone
 */
public class VoteStatAction extends SecurityActionSupport {

  WenMingService wenMingService;

  public void setWenMingService(WenMingService wenMingService) {
    this.wenMingService = wenMingService;
  }

  @Override
  protected void indexSetting() {
    UrpUserBean user = (UrpUserBean) entityDao.get(User.class, getUserId());
    List<AssessSession> sessions = wenMingService.findSessions(user.getDepartment());
    Integer sessionId = getInt("session.id");
    if (null != sessionId) put("assessSession", entityDao.get(AssessSession.class, sessionId));
    put("user", user);
    put("sessions", sessions);
  }

  /**
   * 统计指定的批次
   */
  public String progress() {
    Integer sessionId = getInt("session.id");
    String sql = "select s.fullname, t.voter_id from wm_supervisors s left join (select voter_id from wm_assess_votes where session_id = :sessionId"
        + " group by voter_id) t on t.voter_id = s.id order by s.name";
    SqlBuilder query = SqlBuilder.sql(sql);
    query.param("sessionId", sessionId);
    List<Object[]> datas = entityDao.search(query);
    put("datas", datas);
    return forward();
  }

}
