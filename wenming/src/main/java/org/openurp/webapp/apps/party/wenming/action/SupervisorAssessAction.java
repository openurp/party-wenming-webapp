package org.openurp.webapp.apps.party.wenming.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.beangle.commons.dao.query.builder.OqlBuilder;
import org.beangle.commons.entity.Entity;
import org.openurp.kernel.base.unit.model.Department;
import org.openurp.kernel.base.unit.model.UrpUserBean;
import org.openurp.webapp.apps.party.wenming.model.AssessItem;
import org.openurp.webapp.apps.party.wenming.model.AssessSchema;
import org.openurp.webapp.apps.party.wenming.model.AssessSession;
import org.openurp.webapp.apps.party.wenming.model.AssessState;
import org.openurp.webapp.apps.party.wenming.model.SupervisorAssess;
import org.openurp.webapp.apps.party.wenming.model.SupervisorAssessItem;

/**
 * 督察组测评
 * 
 * @author chaostone
 */
public class SupervisorAssessAction extends WenMingAction {

  @Override
  protected String getEntityName() {
    return SupervisorAssess.class.getName();
  }

  @Override
  public String index() throws Exception {
    if (hasNoAssess()) { return redirect("editIndex"); }
    return super.index();
  }

  private boolean hasNoAssess() {
    AssessSession assessSession = wenMingService.getAssessSessionByAssessTime();
    if (assessSession != null) {
      @SuppressWarnings("unchecked")
      List<SupervisorAssess> list = search(getQueryBuilder());
      if (list.isEmpty()) { return true; }
      for (SupervisorAssess ma : list) {
        if (editable(ma.getState())) { return true; }
      }
    }
    return false;
  }

  @Override
  protected <T extends Entity<?>> OqlBuilder<T> getQueryBuilder() {
    OqlBuilder<T> query = super.getQueryBuilder();
    query.where("assessBy.id = :assessByid", getUserId());
    return query;
  }

  public String editIndex() {
    AssessSession assessSession = wenMingService.getAssessSessionByAssessTime();
    put("assessSession", assessSession);
     put("schemas", assessSession.getSchemas());
    return forward();
  }

  @Override
  public String edit() {
    AssessSchema schema = entityDao.get(AssessSchema.class, getInt("schema.id"));
    AssessSession assessSession = wenMingService.getAssessSessionByAssessTime();
    List<SupervisorAssess> malist = findSupervisorAssess(assessSession.getId(), schema.getId());
    //Department assessDepartment = getDepartment();
    List<AssessItem> items = wenMingService.findAssessItemBySupervisor(schema);
    if (malist.isEmpty()) {
      malist = new ArrayList<SupervisorAssess>();
      for (Department d : schema.getDeparts()) {
        SupervisorAssess ma = new SupervisorAssess();
        ma.setSchema(schema);
        ma.setDepartment(d);
        for (AssessItem item : items) {
          SupervisorAssessItem mai = new SupervisorAssessItem();
          mai.setItem(item);
          ma.getItems().add(mai);
        }
        malist.add(ma);
      }
    } else if (!editable(malist.get(0).getState())) { return redirect("info", null, "schema.id="
        + malist.get(0).getSchema().getId() + "&session.id=" + malist.get(0).getSession().getId()); }
    put("malist", malist);
    put("items", items);
    put("schema", schema);
    return forward();
  }


  private List<SupervisorAssess> findSupervisorAssess(Integer assessSessionId, Integer schemaId) {
    OqlBuilder<SupervisorAssess> query = OqlBuilder.from(SupervisorAssess.class);
    query.where("schema.id = :schemaid", schemaId);
    query.where("session.id = :sessionid", assessSessionId);
    List<SupervisorAssess> malist = search(query);
    return malist;
  }

  @Override
  public String save() throws Exception {
    UrpUserBean assessBy = getUrpUser();
    @SuppressWarnings("unchecked")
    List<SupervisorAssess> malist = (List<SupervisorAssess>) getAll();
    boolean save = getBool("save");
    if(editable(malist.get(0).getState())){
      AssessSession session = wenMingService.getAssessSessionByAssessTime();
      Department assessDepartment = getDepartment();
      for (SupervisorAssess assess : malist) {
        assess.setAssessBy(assessBy);
        assess.setAssessDepartment(assessDepartment);
        @SuppressWarnings("unchecked")
        List<SupervisorAssessItem> items = (List<SupervisorAssessItem>) getAll(SupervisorAssessItem.class, "item"
            + assess.getDepartment().getId());
        assess.getItems().clear();
        assess.getItems().addAll(items);
        assess.setSession(session);
        assess.setAssessAt(new Date());
        float totalScore = 0;
        for (SupervisorAssessItem item : items) {
          item.setAssess(assess);
          totalScore += item.getScore();
        }
        assess.setTotalScore(totalScore);
        if (save) {
          assess.setState(AssessState.Draft);
        } else {
          assess.setState(AssessState.Submit);
        }
      }
      saveOrUpdate(malist);
    }
    return redirect("info", save ? "保存成功" : "提交成功", "schema.id=" + malist.get(0).getSchema().getId() + "&session.id=" + malist.get(0).getSession().getId());
  }
  
  @Override
  public String info() throws Exception {
    List<SupervisorAssess> malist = findSupervisorAssess(getInt("session.id"), getInt("schema.id"));
    put("malist", malist);
    if(editable(malist.get(0).getState())){
      put("editable", true);
    }
    return super.info();
  }
}