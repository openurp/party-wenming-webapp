package org.openurp.webapp.apps.party.wenming.person.model;

import org.openurp.webapp.apps.party.wenming.model.AbstractWenmingObject;


/**
 * 好人好事
 * 
 * @author chaostone
 */
public class GoodPerson extends AbstractWenmingObject {

  private static final long serialVersionUID = -3080682571590719887L;

  private String remark;

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }
  
}
