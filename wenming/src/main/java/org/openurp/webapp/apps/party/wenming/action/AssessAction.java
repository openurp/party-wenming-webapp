package org.openurp.webapp.apps.party.wenming.action;

import java.util.ArrayList;
import java.util.List;

import org.beangle.commons.dao.query.builder.OqlBuilder;
import org.openurp.kernel.base.unit.model.Department;
import org.openurp.webapp.apps.party.wenming.model.AbstractAssessInfo;
import org.openurp.webapp.apps.party.wenming.model.AbstractAssessItemInfo;
import org.openurp.webapp.apps.party.wenming.model.AssessItem;
import org.openurp.webapp.apps.party.wenming.model.AssessSchema;
import org.openurp.webapp.apps.party.wenming.model.AssessSession;
import org.openurp.webapp.apps.party.wenming.model.MutualAssess;
import org.openurp.webapp.apps.party.wenming.model.MutualAssessItem;

public abstract class AssessAction extends WenMingAction {
  
  abstract protected Class<? extends AbstractAssessInfo> getAssessClass();
  abstract protected Class<? extends AbstractAssessItemInfo> getItemClass();
  abstract protected List<AssessItem> findAssessItem(AssessSession assessSession, AssessSchema schema);
  
  @Override
  public String index() throws Exception {
    List<AssessSession> assessSessions = wenMingService.findSessions();
    Integer sessionId = getInt("session.id");
    AssessSession assessSession = null;
    if(sessionId != null){
      assessSession = entityDao.get(AssessSession.class, sessionId);
    }else if(!assessSessions.isEmpty()){
      assessSession = assessSessions.get(0);
    }
    indexSetting(assessSession);
    put("assessSessions", assessSessions);
    put("assessSession", assessSession);
    return super.index();
  }

  protected void indexSetting(AssessSession assessSession) {
    put("schemas", assessSession.getSchemas());
  }

  @Override
  public String edit() {
    Integer schemaId = getInt("schema.id");
    if(schemaId == null){
      return null;
    }
    AssessSchema schema = entityDao.get(AssessSchema.class, schemaId);
    AssessSession assessSession = wenMingService.getAssessSessionByAssessTime();
    List<AbstractAssessInfo> malist = findAssess(assessSession.getId(), schema.getId());
    List<AssessItem> items = findAssessItem(assessSession, schema);
    if (malist.isEmpty()) {
      malist = new ArrayList<AbstractAssessInfo>();
      for (Department d : schema.getDeparts()) {
        MutualAssess ma = new MutualAssess();
        ma.setSchema(schema);
        ma.setDepartment(d);
        for (AssessItem item : items) {
          MutualAssessItem mai = new MutualAssessItem();
          mai.setItem(item);
          ma.getItems().add(mai);
        }
        malist.add(ma);
      }
    } else if (!editable(malist.get(0).getState())) {
      return redirect("info", null, "schema.id=" + malist.get(0).getSchema().getId() + "&session.id="
          + malist.get(0).getSession().getId());
    }
    put("malist", malist);
    put("items", items);
    put("schema", schema);
    return forward();
  }

  protected List<AbstractAssessInfo> findAssess(Integer assessSessionId, Integer schemaId) {
    OqlBuilder<AbstractAssessInfo> query = OqlBuilder.from(getEntityName());
    query.where("schema.id = :schemaid", schemaId);
    query.where("session.id = :sessionid", assessSessionId);
    @SuppressWarnings("unchecked")
    List<AbstractAssessInfo> malist = search(query);
    return malist;
  }

  @Override
  public String save() throws Exception {
    @SuppressWarnings("unchecked")
    List<AbstractAssessInfo> malist = (List<AbstractAssessInfo>) getAllAssess(getAssessClass(), getItemClass());
    if (malist != null) {
      saveOrUpdate(malist);
    }
    return redirect("info", getBool("save") ? "保存成功" : "提交成功", "schema.id=" + malist.get(0).getSchema().getId() + "&session.id="
        + malist.get(0).getSession().getId());
  }

  @Override
  public String info() throws Exception {
    List<AbstractAssessInfo> malist = findAssess(getInt("session.id"), getInt("schema.id"));
    put("malist", malist);
    if (editable(malist.get(0).getState())) {
      put("editable", true);
    }
    return super.info();
  }
}
