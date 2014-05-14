package org.openurp.webapp.apps.party.wenming.model;

import java.util.List;

import org.beangle.commons.collection.CollectUtils;
import org.openurp.kernel.base.unit.model.Department;

/**
 * 督察组测评
 * 
 * @author chaostone
 */
public class SupervisorAssess extends AbstractAssessInfo {
  private static final long serialVersionUID = -2681554073051364406L;
  /**
   * 测评明细
   */
  private List<SupervisorAssessItem> items = CollectUtils.newArrayList();

  public List<SupervisorAssessItem> getItems() {
    return items;
  }

  public void setItems(List<SupervisorAssessItem> items) {
    this.items = items;
  }

}
