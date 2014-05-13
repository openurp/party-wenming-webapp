package org.openurp.webapp.apps.party.wenming.model;

import java.util.List;

import org.beangle.commons.collection.CollectUtils;

/**
 * 单位自评信息
 * 
 * @author chaostone
 */
public class SelfAssess extends AbstractAssessInfo {

  private static final long serialVersionUID = 8070990317191116252L;

  /**
   * 测评明细
   */
  private List<SelfAssessItem> items = CollectUtils.newArrayList();

  public List<SelfAssessItem> getItems() {
    return items;
  }

  public void setItems(List<SelfAssessItem> items) {
    this.items = items;
  }
}
