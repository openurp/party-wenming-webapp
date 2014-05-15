package org.openurp.webapp.apps.party.wenming.depart.model;

import org.beangle.commons.entity.pojo.BaseCode;

/**
 * 加分项目类型
 * 
 * @author chaostone
 */
public class AssessBonusType extends BaseCode<Integer> {

  private static final long serialVersionUID = 6001068807525077703L;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
