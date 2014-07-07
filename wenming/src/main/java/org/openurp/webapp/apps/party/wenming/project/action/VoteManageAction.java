package org.openurp.webapp.apps.party.wenming.project.action;

import java.util.List;

import org.beangle.commons.dao.query.builder.SqlBuilder;
import org.openurp.webapp.apps.party.wenming.action.WenmingProjectVoteManageAction;

public class VoteManageAction extends WenmingProjectVoteManageAction{
  
  public String info() {
    Integer sessionId = getInt("session.id");
    String sql = "select p.id,p.name, count(*)"
        + " from wm_good_project_votes v"
        + " join wm_good_projects p on p.id = v.good_project_id"
        + " where ayes = true and v.session_id = :sessionId and v.submit=true"
        + " group by p.id, p.name order by count(*) desc";
    SqlBuilder query = SqlBuilder.sql(sql);
    query.param("sessionId", sessionId);
    System.out.println(sql);
    System.out.println(sessionId);
    List<Object[]> datas = entityDao.search(query);
    put("datas", datas);
    return forward();
  }

}
