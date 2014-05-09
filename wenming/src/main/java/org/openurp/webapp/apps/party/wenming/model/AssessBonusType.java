package org.openurp.webapp.apps.party.wenming.model;

import org.beangle.commons.entity.pojo.BaseCode;

/**
 * 加分项类型
 * 
 * @author chaostone
 */
public class AssessBonusType extends BaseCode<Integer> {

  private static final long serialVersionUID = 6001068807525077703L;
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
