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
import org.openurp.webapp.apps.party.wenming.depart.model.AssessState;
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
    put("departs", departs);
    put("departCount", departs.size());
    put("funcDepartCount", funcDepartCount);
    
    //申报材料统计
    OqlBuilder<AssessApply> builder = OqlBuilder.from(AssessApply.class, "apply");
    builder.where("apply.session.id=:sessionId", sessionId);
    builder.groupBy("apply.state").select("apply.state,count(*)");
    put("applyStat", entityDao.search(builder));

    OqlBuilder<AssessApply> dataBuilder = OqlBuilder.from(AssessApply.class, "apply");
    dataBuilder.where("apply.session.id=:sessionId and apply.state in(:states)", sessionId,
        CollectUtils.newArrayList(AssessState.DepartApproved, AssessState.SchoolApproved));
    dataBuilder.select("apply.department.id");
    put("appliedDepartIds", entityDao.search(dataBuilder));
    
    //自评统计
    OqlBuilder<SelfAssess> sabuilder = OqlBuilder.from(SelfAssess.class, "assess");
    sabuilder.where("assess.session.id=:sessionId", sessionId);
    sabuilder.groupBy("assess.state").select("assess.state,count(*)");
    put("selfAssessStat", entityDao.search(sabuilder));

    OqlBuilder<SelfAssess> saDataBuilder = OqlBuilder.from(SelfAssess.class, "assess");
    saDataBuilder.where("assess.session.id=:sessionId and assess.state in(:states)", sessionId,
        CollectUtils.newArrayList(AssessState.DepartApproved, AssessState.SchoolApproved));
    saDataBuilder.select("assess.department.id");
    put("selfAssessDepartIds", entityDao.search(saDataBuilder));
    
    //互评统计
    OqlBuilder<MutualAssess> mabuilder = OqlBuilder.from(MutualAssess.class, "assess");
    mabuilder.where("assess.session.id=:sessionId", sessionId);
    mabuilder.groupBy("assess.state").select("assess.state,count(distinct assess.assessDepartment.id)");
    put("mutualAssessStat", entityDao.search(mabuilder));

    OqlBuilder<MutualAssess> maDatabuilder = OqlBuilder.from(MutualAssess.class, "assess");
    maDatabuilder.where("assess.session.id=:sessionId and assess.state in(:states)", sessionId,
        CollectUtils.newArrayList(AssessState.DepartApproved, AssessState.SchoolApproved));
    maDatabuilder.select("distinct assess.assessDepartment.id");
    put("mutualAssessDepartIds", entityDao.search(maDatabuilder));
    
    //职能部门测评
    OqlBuilder<FuncDepartAssess> fabuilder = OqlBuilder.from(FuncDepartAssess.class, "assess");
    fabuilder.where("assess.session.id=:sessionId", sessionId);
    fabuilder.groupBy("assess.state").select("assess.state,count(distinct assess.assessDepartment.id)");
    put("funcDepartAssessStat", entityDao.search(fabuilder));
    
    OqlBuilder<FuncDepartAssess> faDatabuilder = OqlBuilder.from(FuncDepartAssess.class, "assess");
    faDatabuilder.where("assess.session.id=:sessionId and assess.state in(:states)", sessionId,
        CollectUtils.newArrayList(AssessState.DepartApproved, AssessState.SchoolApproved));
    faDatabuilder.select("distinct assess.assessDepartment.id");
    put("funcDepartAssessDepartIds", entityDao.search(faDatabuilder));
    return forward();
  }

  public void setWenMingService(WenMingService wenMingService) {
    this.wenMingService = wenMingService;
  }

}
