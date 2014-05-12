package org.openurp.webapp.apps.party.wenming.action;

import java.util.List;

import org.beangle.ems.web.action.SecurityActionSupport;
import org.beangle.security.blueprint.User;
import org.openurp.kernel.base.unit.model.UrpUserBean;
import org.openurp.webapp.apps.party.wenming.model.AssessSession;
import org.openurp.webapp.apps.party.wenming.model.AssessStat;
import org.openurp.webapp.apps.party.wenming.service.WenMingService;

/**
 * 评测统计
 * 
 * @author chaostone
 */
public class AssessStatAction extends SecurityActionSupport {

  WenMingService wenMingService;

  @Override
  protected void indexSetting() {
    UrpUserBean user = (UrpUserBean) entityDao.get(User.class, getUserId());
    List<AssessSession> sessions = wenMingService.findSessions(user.getDepartment());
    Integer sessionId = getInt("session.id");
    if (null != sessionId) put("assessSession", entityDao.get(AssessSession.class, sessionId));
    put("user", user);
    put("sessions", sessions);
  }

  @Override
  protected String getEntityName() {
    return AssessStat.class.getName();
  }

  /**
   * 统计指定的批次
   */
  public String stat() {
    Integer sessionId = getInt("session.id");
    return forward();
  }

  /**
   * 统计指定的批次
   */
  public String result() {
    Integer sessionId = getInt("session.id");
    return forward();

  }

  public void setWenMingService(WenMingService wenMingService) {
    this.wenMingService = wenMingService;
  }

}
