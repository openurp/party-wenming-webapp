package org.openurp.webapp.apps.party.wenming.action;

import java.util.List;

import org.beangle.commons.dao.query.builder.OqlBuilder;
import org.beangle.security.blueprint.User;
import org.openurp.webapp.apps.party.wenming.model.AbstractAssessInfo;
import org.openurp.webapp.apps.party.wenming.model.AbstractAssessItemInfo;
import org.openurp.webapp.apps.party.wenming.model.AssessItem;
import org.openurp.webapp.apps.party.wenming.model.AssessSchema;
import org.openurp.webapp.apps.party.wenming.model.AssessSession;
import org.openurp.webapp.apps.party.wenming.model.MutualAssess;
import org.openurp.webapp.apps.party.wenming.model.MutualAssessItem;
import org.openurp.webapp.apps.party.wenming.model.SelfAssess;
import org.openurp.webapp.apps.party.wenming.model.SelfAssessItem;

/**
 * 自评审批
 * 
 * @author chaostone
 */
public class SelfAssessAuditAction extends AssessDepartAuditAction {
  
  @Override
  protected Class<? extends AbstractAssessInfo> getAssessClass() {
    return SelfAssess.class;
  }

  @Override
  protected Class<? extends AbstractAssessItemInfo> getItemClass() {
    return SelfAssessItem.class;
  }

  @Override
  protected List<AssessItem> findAssessItem(AssessSession assessSession, AssessSchema schema) {
    return wenMingService.findAssessItem(schema);
  }

  protected void indexSetting(AssessSession assessSession) {
    AssessSchema schema = wenMingService.getSchema(assessSession, getDepartment());
    List<User> users = findUsers(assessSession, schema);
    put("users", users);
    put("schema", schema);
  }

  @SuppressWarnings("unchecked")
  private List<User> findUsers(AssessSession assessSession, AssessSchema schema) {
    OqlBuilder<?> query = OqlBuilder.from(SelfAssess.class, "o");
    query.where("o.session = :session", assessSession);
    query.where("o.schema = :schema", schema);
    query.select("distinct o.assessBy");
    return (List<User>) entityDao.search(query);
  }

}
