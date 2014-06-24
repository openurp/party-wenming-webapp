package org.openurp.webapp.apps.party.wenming.depart.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.beangle.commons.dao.query.builder.OqlBuilder;
import org.openurp.kernel.base.unit.model.Department;
import org.openurp.webapp.apps.party.wenming.depart.model.AbstractAssessInfo;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessItem;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessSchema;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessSession;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessType;
import org.openurp.webapp.apps.party.wenming.depart.model.MutualAssess;
import org.openurp.webapp.apps.party.wenming.depart.model.MutualAssessItem;
import org.openurp.webapp.apps.party.wenming.depart.model.SupervisorAssess;
import org.openurp.webapp.apps.party.wenming.depart.model.SupervisorAssessItem;
import org.openurp.webapp.apps.party.wenming.depart.service.QueryInvoker;


/**
 * 督察组测评
 * 
 * @author chaostone
 */
public class SupervisorAssessAction extends AssessAction<SupervisorAssess, SupervisorAssessItem> {

  @Override
  protected String getEntityName() {
    return SupervisorAssess.class.getName();
  }

  @Override
  protected Class<SupervisorAssess> getAssessClass() {
    return SupervisorAssess.class;
  }

  @Override
  protected Class<SupervisorAssessItem> getItemClass() {
    return SupervisorAssessItem.class;
  }

  @Override
  protected List<AssessItem> findAssessItem(AssessSession assessSession, AssessSchema schema) {
    return wenMingService.findAssessItem(schema, new QueryInvoker() {
      @Override
      public void doth(OqlBuilder<?> query) {
        query.where("o.assessType = :assessType", AssessType.SUPERVISOR);
      }
    });
  }

  protected void editSetting(AssessSession assessSession, AssessSchema schema, List<AbstractAssessInfo> malist) {
    if (malist.get(0).getId() != null) {
      List<AssessItem> items = findAssessItem(assessSession, schema);
      for (Department d : schema.getDeparts()) {
        boolean hasDepartment = false;
        for (AbstractAssessInfo assess : malist) {
          if (assess.getDepartment().equals(d)) {
            hasDepartment = true;
          }
        }
        if (!hasDepartment) {
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
      }
    }
  }

  protected void saveAndForward(List<AbstractAssessInfo> malist) {
    Long[] ids = new Long[malist.size()];
    for (int i = 0; i < malist.size(); i++) {
      ids[i] = malist.get(i).getId();
    }
    AssessSession session = wenMingService.getAssessSessionByAssessTime();
    Integer schemaId = getInt("schemaId");
    OqlBuilder<SupervisorAssess> query = OqlBuilder.from(SupervisorAssess.class, "o");
    if(malist.size() > 0){
      query.where("o.id not in (:ids)", ids);
    }
    query.where("o.session.id = :sessionid", session.getId());
    query.where("o.schema.id = :schemaid", schemaId);
    query.where("o.assessBy.id = :assessbyid", getUserId());
    @SuppressWarnings("unchecked")
    List<SupervisorAssess> removeList = search(query);
    remove(removeList);
  }
  

}