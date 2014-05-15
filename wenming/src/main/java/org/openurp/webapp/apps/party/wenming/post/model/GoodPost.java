package org.openurp.webapp.apps.party.wenming.post.model;

import org.openurp.webapp.apps.party.wenming.model.AbstractWenmingObject;

/**
 * 文明示范岗
 * 
 * @author chaostone
 */
public class GoodPost extends AbstractWenmingObject {

  private static final long serialVersionUID = -8549949502746276473L;
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
