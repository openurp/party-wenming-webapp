package org.openurp.webapp.apps.party.wenming.depart.action;

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

  protected void indexSetting(AssessSession assessSession) {
    put("schemas", mutualAssessService.getSchemas(assessSession, getDepartment()));
  }

}
