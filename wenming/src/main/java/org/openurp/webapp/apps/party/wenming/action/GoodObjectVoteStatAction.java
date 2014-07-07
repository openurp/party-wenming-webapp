package org.openurp.webapp.apps.party.wenming.action;

import java.util.List;

import org.beangle.commons.dao.query.builder.SqlBuilder;
import org.beangle.ems.web.action.SecurityActionSupport;
import org.beangle.security.blueprint.User;
import org.openurp.kernel.base.unit.model.UrpUserBean;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessSession;
import org.openurp.webapp.apps.party.wenming.depart.service.WenMingService;
import org.openurp.webapp.apps.party.wenming.model.WenmingVoteSession;

/**
 * 测评统计
 * 
 * @author chaostone
 */
public class GoodObjectVoteStatAction extends SecurityActionSupport {

  WenMingService wenMingService;

  public void setWenMingService(WenMingService wenMingService) {
    this.wenMingService = wenMingService;
  }

  @Override
  protected void indexSetting() {
    UrpUserBean user = (UrpUserBean) entityDao.get(User.class, getUserId());
    List<WenmingVoteSession> sessions = wenMingService.findWenmingVoteSession(null);
    Integer sessionId = getInt("session.id");
    if (null != sessionId)
      put("assessSession", entityDao.get(AssessSession.class, sessionId));
    put("user", user);
    put("sessions", sessions);
  }

  /**
   * 统计指定的批次
   */
  public String progress() {
    Integer sessionId = getInt("session.id");
    String sql = "select v.fullname, project.voter_id project, person.voter_id person, post.voter_id post from wm_wenming_project_voters v "
        + " left join (select voter_id from wm_good_project_votes where session_id = :sessionId and submit=true group by voter_id) project on project.voter_id = v.id"
        + " left join (select voter_id from wm_good_person_votes where session_id = :sessionId and submit=true group by voter_id) person on person.voter_id = v.id"
        + " left join (select voter_id from wm_good_post_votes where session_id = :sessionId and submit=true group by voter_id) post on post.voter_id = v.id"
        + " order by v.name";
    SqlBuilder query = SqlBuilder.sql(sql);
    query.param("sessionId", sessionId);
    List<Object[]> datas = entityDao.search(query);
    put("datas", datas);
    return forward();
  }

}
