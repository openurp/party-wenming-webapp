package org.openurp.webapp.apps.party.wenming.depart.action;

import java.util.List;
import java.util.Set;

import org.beangle.commons.collection.CollectUtils;
import org.beangle.commons.dao.query.builder.OqlBuilder;
import org.beangle.ems.web.action.SecurityActionSupport;
import org.beangle.security.blueprint.User;
import org.openurp.kernel.base.unit.model.Department;
import org.openurp.kernel.base.unit.model.UrpUserBean;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessApply;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessSchema;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessSession;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessStat;
import org.openurp.webapp.apps.party.wenming.depart.model.FuncDepartAssess;
import org.openurp.webapp.apps.party.wenming.depart.model.MutualAssess;
import org.openurp.webapp.apps.party.wenming.depart.model.SelfAssess;
import org.openurp.webapp.apps.party.wenming.depart.service.WenMingService;

/**
 * 测评统计
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
  public String progress() {
    Integer sessionId = getInt("session.id");
    AssessSession session = entityDao.get(AssessSession.class, sessionId);
    Set<Department> departs = CollectUtils.newHashSet();
    int funcDepartCount = 0;
    for (AssessSchema schema : session.getSchemas()) {
      departs.addAll(schema.getDeparts());
    }
    for (Department depart : departs) {
      if (!depart.isTeaching()) funcDepartCount += 1;
    }
    put("departCount", departs.size());
    put("funcDepartCount",funcDepartCount);
    OqlBuilder<AssessApply> builder = OqlBuilder.from(AssessApply.class, "apply");
    builder.where("apply.session.id=:sessionId", sessionId);
    builder.groupBy("apply.state").select("apply.state,count(*)");
    put("applyStat", entityDao.search(builder));

    OqlBuilder<SelfAssess> sabuilder = OqlBuilder.from(SelfAssess.class, "assess");
    sabuilder.where("assess.session.id=:sessionId", sessionId);
    sabuilder.groupBy("assess.state").select("assess.state,count(*)");
    put("selfAssessStat", entityDao.search(sabuilder));

    OqlBuilder<MutualAssess> mabuilder = OqlBuilder.from(MutualAssess.class, "assess");
    mabuilder.where("assess.session.id=:sessionId", sessionId);
    mabuilder.groupBy("assess.state").select("assess.state,count(distinct assess.assessDepartment.id)");
    put("mutualAssessStat", entityDao.search(mabuilder));

    OqlBuilder<FuncDepartAssess> fabuilder = OqlBuilder.from(FuncDepartAssess.class, "assess");
    fabuilder.where("assess.session.id=:sessionId", sessionId);
    fabuilder.groupBy("assess.state").select("assess.state,count(distinct assess.assessDepartment.id)");
    put("funcDepartAssessStat", entityDao.search(fabuilder));
    return forward();
  }

  public void setWenMingService(WenMingService wenMingService) {
    this.wenMingService = wenMingService;
  }

}
