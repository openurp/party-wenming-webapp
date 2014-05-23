package org.openurp.webapp.apps.party.wenming.project.model;

import org.openurp.webapp.apps.party.wenming.model.AbstractWenmingObject;

/**
 * 优秀项目
 * 
 * @author chaostone
 */
public class GoodProject extends AbstractWenmingObject<GoodProjectMiddleSummary, GoodProjectFinalSummary> {

  private static final long serialVersionUID = 661863669638885448L;

  /**
   * 方案和目标
   */
  private String plan;
  
  private GoodProjectMiddleSummary middleSummary;
  
  private GoodProjectFinalSummary finalSummary;
  
  public GoodProjectMiddleSummary getMiddleSummary() {
    return middleSummary;
  }

  public void setMiddleSummary(GoodProjectMiddleSummary middleSummary) {
    this.middleSummary = middleSummary;
  }

  public GoodProjectFinalSummary getFinalSummary() {
    return finalSummary;
  }

  public void setFinalSummary(GoodProjectFinalSummary finalSummary) {
    this.finalSummary = finalSummary;
  }

  public String getPlan() {
    return plan;
  }

  public void setPlan(String plan) {
    this.plan = plan;
  }

}
