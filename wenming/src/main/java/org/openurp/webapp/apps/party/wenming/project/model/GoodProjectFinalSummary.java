package org.openurp.webapp.apps.party.wenming.project.model;

import org.openurp.webapp.apps.party.wenming.model.AbstractWenmingFinalSummaryObject;

public class GoodProjectFinalSummary extends AbstractWenmingFinalSummaryObject<GoodProject>{
  private GoodProject good;
  
  /**终期审核意见**/
  
  private String auditOpinion;

  public String getAuditOpinion() {
    return auditOpinion;
  }

  public void setAuditOpinion(String auditOpinion) {
    this.auditOpinion = auditOpinion;
  }


  public GoodProject getGood() {
    return good;
  }

  public void setGood(GoodProject good) {
    this.good = good;
  }
}
