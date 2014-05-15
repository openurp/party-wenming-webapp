package org.openurp.webapp.apps.party.wenming.depart.action;

import java.util.List;

import org.beangle.commons.dao.query.builder.OqlBuilder;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessItem;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessSchema;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessSession;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessType;
import org.openurp.webapp.apps.party.wenming.depart.model.FuncDepartAssess;
import org.openurp.webapp.apps.party.wenming.depart.model.FuncDepartAssessItem;
import org.openurp.webapp.apps.party.wenming.depart.service.QueryInvoker;


/**
 * 职能部门评院系
 * 
 * @author chaostone
 * 
 */
public class FuncDepartAssessAction extends AssessAction<FuncDepartAssess, FuncDepartAssessItem> {

  @Override
  protected List<AssessItem> findAssessItem(AssessSession assessSession, AssessSchema schema) {
    return wenMingService.findAssessItem(schema, new QueryInvoker() {
      @Override
      public void doth(OqlBuilder<?> query) {
        query.join("o.departs", "d");
        query.where("d.department = :department", getDepartment());
        query.where("o.assessType = :assessType", AssessType.FUNC_DEPART);
      }
    });
  }
  
}
