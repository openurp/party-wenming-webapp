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
import org.openurp.webapp.apps.party.wenming.model.MutualAssess;
import org.openurp.webapp.apps.party.wenming.model.MutualAssessItem;
import org.openurp.webapp.apps.party.wenming.service.QueryInvoker;

/**
 * 互评
 * 
 * @author chaostone
 * 
 */
public class MutualAssessAction extends AssessAction {

  @Override
  protected Class<? extends AbstractAssessInfo> getAssessClass() {
    return MutualAssess.class;
  }

  @Override
  protected Class<? extends AbstractAssessItemInfo> getItemClass() {
    return MutualAssessItem.class;
  }

  protected void indexSetting(AssessSession assessSession) {
    put("schemas", getSchemas(assessSession));
  }

  /**
   * 教学部门与评职能部门互评
   * 
   * @param assessSession
   * @return
   */
  private Object getSchemas(AssessSession assessSession) {
    if (assessSession == null) {
      return null;
    }
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
  protected List<AssessItem> findAssessItem(AssessSession assessSession, AssessSchema schema) {
    return wenMingService.findAssessItem(schema, new QueryInvoker() {
      @Override
      public void doth(OqlBuilder<?> query) {
        query.where("o.assessType = :assessType", AssessType.MUTUAL);
      }
    });
  }

}
