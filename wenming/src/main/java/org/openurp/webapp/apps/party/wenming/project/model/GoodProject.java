package org.openurp.webapp.apps.party.wenming.project.model;

import org.openurp.webapp.apps.party.wenming.model.AbstractWenmingObject;

/**
 * 优秀项目
 * 
 * @author chaostone
 */
public class GoodProject extends AbstractWenmingObject {

  private static final long serialVersionUID = 661863669638885448L;

  /**
   * 方案和目标
   */
  private String plan;

  public String getPlan() {
    return plan;
  }

  public void setPlan(String plan) {
    this.plan = plan;
  }

}
