package org.openurp.webapp.apps.party.wenming.depart.action;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.beangle.commons.collection.CollectUtils;
import org.beangle.commons.dao.query.builder.OqlBuilder;
import org.beangle.commons.dao.query.builder.SqlBuilder;
import org.beangle.ems.web.action.SecurityActionSupport;
import org.beangle.security.blueprint.User;
import org.openurp.kernel.base.unit.model.Department;
import org.openurp.kernel.base.unit.model.UrpUserBean;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessApply;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessItem;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessItemDepartment;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessItemGroup;
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

  private void addDepartSchemaCount(Collection<Department> departs, Map<Integer, Integer> mutualCnts) {
    for (Department d : departs) {
      Integer count = mutualCnts.get(d.getId());
      if (null == count) count = 1;
      else count = count + 1;
      mutualCnts.put(d.getId(), count);
    }
  }

  /**
   * 将一个数组[key,value]集合转换成map，重复的key value会相加
   * 
   * @param objects
   * @return
   */
  private Map<Object, Object> toMap(List objects) {
    Map<Object, Object> data = CollectUtils.newHashMap();
    for (Object d : objects) {
      Object[] da = (Object[]) d;
      Number value = (Number) data.get(da[0]);
      if (value == null) value = (Number) da[1];
      else value = value.intValue() + ((Number) da[1]).intValue();
      data.put(da[0], value);
    }
    return data;
  }

  /**
   * 统计指定的批次
   */
  public String progress() {
    Integer sessionId = getInt("session.id");
    AssessSession session = entityDao.get(AssessSession.class, sessionId);
    Set<Department> departs = CollectUtils.newHashSet();
    Set<Department> funcDeparts = CollectUtils.newHashSet();
    // 统计互评次数
    Map<Integer, Integer> mutualCnts = CollectUtils.newHashMap();
    Map<Integer, Integer> funcDepartCnts = CollectUtils.newHashMap();
    Set<AssessSchema> processedSchemas = CollectUtils.newHashSet();
    for (AssessSchema schema : session.getSchemas()) {
      departs.addAll(schema.getDeparts());
      for (AssessSchema p : processedSchemas) {
        if (schema.isForTeaching() != p.isForTeaching()) {
          addDepartSchemaCount(p.getDeparts(), mutualCnts);
          addDepartSchemaCount(schema.getDeparts(), mutualCnts);
        }
      }
      processedSchemas.add(schema);

      // 统计职能部门列表
      Set<Department> schemaFuncDeparts = CollectUtils.newHashSet();
      for (AssessItemGroup group : schema.getGroups()) {
        for (AssessItem item : group.getItems()) {
          for (AssessItemDepartment id : item.getDeparts()) {
            schemaFuncDeparts.add(id.getDepartment());
          }
        }
      }
      funcDeparts.addAll(schemaFuncDeparts);
      addDepartSchemaCount(schemaFuncDeparts, funcDepartCnts);
    }
    put("departs", departs);
    put("funcDeparts", funcDeparts);
    put("departCount", departs.size());
    Integer mutualCount = 0;
    for (Integer a : mutualCnts.values())
      mutualCount += a;
    put("mutualCount", mutualCount);
    put("mutualCnts", mutualCnts);
    Integer funcDepartCount = 0;
    for (Integer a : funcDepartCnts.values())
      funcDepartCount += a;
    put("funcDepartCount", funcDepartCount);
    put("funcDepartCnts", funcDepartCnts);

    // 申报材料统计
    OqlBuilder<AssessApply> builder = OqlBuilder.from(AssessApply.class, "apply");
    builder.where("apply.session.id=:sessionId", sessionId);
    builder.groupBy("apply.state").select("apply.state,count(*)");
    put("applyStat", entityDao.search(builder));

    OqlBuilder<AssessApply> dataBuilder = OqlBuilder.from(AssessApply.class, "apply");
    dataBuilder.where("apply.session.id=:sessionId and apply.state in(:states)", sessionId,
        CollectUtils.newArrayList(AssessState.DepartApproved, AssessState.SchoolApproved));
    dataBuilder.select("apply.department.id");
    put("appliedDepartIds", entityDao.search(dataBuilder));

    // 自评统计
    OqlBuilder<SelfAssess> sabuilder = OqlBuilder.from(SelfAssess.class, "assess");
    sabuilder.where("assess.session.id=:sessionId", sessionId);
    sabuilder.groupBy("assess.state").select("assess.state,count(*)");
    put("selfAssessStat", entityDao.search(sabuilder));

    OqlBuilder<SelfAssess> saDataBuilder = OqlBuilder.from(SelfAssess.class, "assess");
    saDataBuilder.where("assess.session.id=:sessionId and assess.state in(:states)", sessionId,
        CollectUtils.newArrayList(AssessState.DepartApproved, AssessState.SchoolApproved));
    saDataBuilder.select("assess.department.id");
    put("selfAssessDepartIds", entityDao.search(saDataBuilder));

    // 互评统计
    OqlBuilder<MutualAssess> mabuilder = OqlBuilder.from(MutualAssess.class, "assess");
    mabuilder.where("assess.session.id=:sessionId", sessionId);
    mabuilder.groupBy("assess.state,assess.schema.id").select(
        "assess.state,count(distinct assess.assessDepartment.id)");
    put("mutualAssessStat", toMap(entityDao.search(mabuilder)));

    OqlBuilder<MutualAssess> maDatabuilder = OqlBuilder.from(MutualAssess.class, "assess");
    maDatabuilder.where("assess.session.id=:sessionId and assess.state in(:states)", sessionId,
        CollectUtils.newArrayList(AssessState.DepartApproved, AssessState.SchoolApproved));
    maDatabuilder.groupBy("assess.assessDepartment.id").select(
        "assess.assessDepartment.id,count(distinct assess.schema.id)");
    put("mutualAssessDeparts", toMap(entityDao.search(maDatabuilder)));

    // 职能部门测评
    OqlBuilder<FuncDepartAssess> fabuilder = OqlBuilder.from(FuncDepartAssess.class, "assess");
    fabuilder.where("assess.session.id=:sessionId", sessionId);
    fabuilder.groupBy("assess.state,assess.schema.id").select(
        "assess.state,count(distinct assess.assessDepartment.id)");
    put("funcDepartAssessStat", toMap(entityDao.search(fabuilder)));

    OqlBuilder<FuncDepartAssess> faDatabuilder = OqlBuilder.from(FuncDepartAssess.class, "assess");
    faDatabuilder.where("assess.session.id=:sessionId and assess.state in(:states)", sessionId,
        CollectUtils.newArrayList(AssessState.DepartApproved, AssessState.SchoolApproved));
    faDatabuilder.groupBy("assess.assessDepartment.id").select(
        "assess.assessDepartment.id,count(distinct assess.schema.id)");
    put("funcDepartAssessDeparts", toMap(entityDao.search(faDatabuilder)));
    return forward();
  }

  public void setWenMingService(WenMingService wenMingService) {
    this.wenMingService = wenMingService;
  }

}
