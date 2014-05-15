package org.openurp.webapp.apps.party.wenming.depart.action;

import java.util.List;

import org.openurp.webapp.apps.party.wenming.depart.model.AssessItem;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessSchema;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessSession;
import org.openurp.webapp.apps.party.wenming.depart.model.MutualAssess;
import org.openurp.webapp.apps.party.wenming.depart.model.MutualAssessItem;
import org.openurp.webapp.apps.party.wenming.depart.service.MutualAssessService;

/**
 * 互评审批
 * @author chaostone
 *
 */
public class MutualAssessAuditAction extends AssessDepartAuditAction<MutualAssess, MutualAssessItem>{
  
  private MutualAssessService mutualAssessService;
  
  public void setMutualAssessService(MutualAssessService mutualAssessService) {
    this.mutualAssessService = mutualAssessService;
  }

  @Override
  protected Class<MutualAssess> getAssessClass() {
    return MutualAssess.class;
  }

  @Override
  protected Class<MutualAssessItem> getItemClass() {
    return MutualAssessItem.class;
  }

  @Override
  protected List<AssessItem> findAssessItem(AssessSession assessSession, AssessSchema schema) {
    return null;
  }

  protected void indexSetting(AssessSession assessSession) {
    put("schemas", mutualAssessService.getSchemas(assessSession, getDepartment()));
  }

}
