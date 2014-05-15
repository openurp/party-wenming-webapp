package org.openurp.webapp.apps.party.wenming.depart.model;

import java.util.List;

import org.beangle.commons.collection.CollectUtils;

/**
 * 职能部门测评信息
 * 
 * @author chaostone
 */
public class FuncDepartAssess extends AbstractAssessInfo {

  private static final long serialVersionUID = 8070990317191116252L;

  /**
   * 测评明细
   */
  private List<FuncDepartAssessItem> items = CollectUtils.newArrayList();

  public List<FuncDepartAssessItem> getItems() {
    return items;
  }

  public void setItems(List<FuncDepartAssessItem> items) {
    this.items = items;
  }
}
