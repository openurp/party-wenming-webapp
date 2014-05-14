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
import org.openurp.webapp.apps.party.wenming.model.AssessType;
import org.openurp.webapp.apps.party.wenming.model.SupervisorAssess;
import org.openurp.webapp.apps.party.wenming.model.SupervisorAssessItem;
import org.openurp.webapp.apps.party.wenming.service.QueryInvoker;

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
