package org.openurp.webapp.apps.party.wenming.action;

import java.util.List;

import org.openurp.webapp.apps.party.wenming.model.AbstractAssessInfo;
import org.openurp.webapp.apps.party.wenming.model.AssessItem;
import org.openurp.webapp.apps.party.wenming.model.AssessSchema;
import org.openurp.webapp.apps.party.wenming.model.AssessSession;
import org.openurp.webapp.apps.party.wenming.model.SelfAssess;
import org.openurp.webapp.apps.party.wenming.model.SelfAssessItem;

/**
 * 自评审批
 * 
 * @author chaostone
 */
public class SelfAssessAuditAction extends AssessDepartAuditAction<SelfAssess, SelfAssessItem> {
  
  @Override
  protected Class<SelfAssess> getAssessClass() {
    return SelfAssess.class;
  }

  @Override
  protected Class<SelfAssessItem> getItemClass() {
    return SelfAssessItem.class;
  }

  @Override
  protected List<AssessItem> findAssessItem(AssessSession assessSession, AssessSchema schema) {
    return wenMingService.findAssessItem(schema);
  }

  protected void indexSetting(AssessSession assessSession) {
    AssessSchema schema = wenMingService.getSchema(assessSession, getDepartment());
//    List<User> users = findUsers(assessSession, schema);
//    put("users", users);
    put("schema", schema);
  }
  
  @Override
  protected String redirectSave(AbstractAssessInfo assess) {
    return redirectInfo(assess);
  }

//  @SuppressWarnings("unchecked")
//  private List<User> findUsers(AssessSession assessSession, AssessSchema schema) {
//    OqlBuilder<?> query = OqlBuilder.from(SelfAssess.class, "o");
//    query.where("o.session = :session", assessSession);
//    query.where("o.schema = :schema", schema);
//    query.select("distinct o.assessBy");
//    return (List<User>) entityDao.search(query);
//  }

}
