package org.openurp.webapp.apps.party.wenming.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.beangle.commons.dao.query.builder.OqlBuilder;
import org.beangle.commons.entity.Entity;
import org.openurp.kernel.base.unit.model.Department;
import org.openurp.webapp.apps.party.wenming.model.AbstractAssessInfo;
import org.openurp.webapp.apps.party.wenming.model.AbstractAssessItemInfo;
import org.openurp.webapp.apps.party.wenming.model.AssessItem;
import org.openurp.webapp.apps.party.wenming.model.AssessSchema;
import org.openurp.webapp.apps.party.wenming.model.AssessSession;
import org.openurp.webapp.apps.party.wenming.model.MutualAssess;
import org.openurp.webapp.apps.party.wenming.model.MutualAssessItem;

/**
 * 互评
 * 
 * @author chaostone
 * 
 */
public class MutualAssessAction extends WenMingAction {

  @Override
  protected String getEntityName() {
    return MutualAssess.class.getName();
  }

  @Override
  public String index() throws Exception {
    if (hasNoAssess()) {
      return redirect("editIndex");
    }
    return super.index();
  }

  private boolean hasNoAssess() {
    AssessSession assessSession = wenMingService.getAssessSessionByAssessTime();
    if (assessSession != null) {
      @SuppressWarnings("unchecked")
      List<MutualAssess> list = search(getQueryBuilder());
      if (list.isEmpty()) {
        return true;
      }
      for (MutualAssess ma : list) {
        if (editable(ma.getState())) {
          return true;
        }
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
    put("schemas", getSchemas(assessSession));
    return forward();
  }

  private Object getSchemas(AssessSession assessSession) {
    Department department = getDepartment();
    List<AssessSchema> list = new ArrayList<AssessSchema>();
    if (assessSession != null) {
      for (AssessSchema schema : assessSession.getSchemas()) {
        if (department.isTeaching() != schema.isForTeaching()) {
          list.add(schema);
        }
      }
    }
    return list;
  }

  @Override
  public String edit() {
    AssessSchema schema = entityDao.get(AssessSchema.class, getInt("schema.id"));
    AssessSession assessSession = wenMingService.getAssessSessionByAssessTime();
    List<MutualAssess> malist = findMuaualAssess(assessSession.getId(), schema.getId());
    List<AssessItem> items = wenMingService.findAssessItemByMutual(schema);
    if (malist.isEmpty()) {
      malist = new ArrayList<MutualAssess>();
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

  private List<MutualAssess> findMuaualAssess(Integer assessSessionId, Integer schemaId) {
    OqlBuilder<MutualAssess> query = OqlBuilder.from(MutualAssess.class);
    query.where("schema.id = :schemaid", schemaId);
    query.where("session.id = :sessionid", assessSessionId);
    List<MutualAssess> malist = search(query);
    return malist;
  }

  @Override
  public String save() throws Exception {
    @SuppressWarnings("unchecked")
    List<MutualAssess> malist = getAllAssess(MutualAssess.class, MutualAssessItem.class);
    if (editable(malist.get(0).getState())) {
      saveOrUpdate(malist);
    }
    return redirect("info", null, "schema.id=" + malist.get(0).getSchema().getId() + "&session.id="
        + malist.get(0).getSession().getId());
  }

  @Override
  public String info() throws Exception {
    List<MutualAssess> malist = findMuaualAssess(getInt("session.id"), getInt("schema.id"));
    put("malist", malist);
    if (editable(malist.get(0).getState())) {
      put("editable", true);
    }
    return super.info();
  }
}
