package org.openurp.webapp.apps.party.wenming.depart.action;

import java.util.ArrayList;
import java.util.List;

import org.beangle.commons.dao.query.builder.OqlBuilder;
import org.openurp.kernel.base.unit.model.Department;
import org.openurp.webapp.apps.party.wenming.depart.model.AbstractAssessInfo;
import org.openurp.webapp.apps.party.wenming.depart.model.AbstractAssessItemInfo;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessItem;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessSchema;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessSession;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessType;
import org.openurp.webapp.apps.party.wenming.depart.model.SupervisorAssess;
import org.openurp.webapp.apps.party.wenming.depart.model.SupervisorAssessItem;
import org.openurp.webapp.apps.party.wenming.depart.service.QueryInvoker;

/**
 * 督察组测评
 * 
 * @author chaostone
 */
public class SupervisorAssessAction extends AssessAction {

  @Override
  protected String getEntityName() {
    return SupervisorAssess.class.getName();
  }

  @Override
  protected Class<? extends AbstractAssessInfo> getAssessClass() {
    return SupervisorAssess.class;
  }

  @Override
  protected Class<? extends AbstractAssessItemInfo> getItemClass() {
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

}
