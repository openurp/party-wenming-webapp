package org.openurp.webapp.apps.party.wenming.model;

import java.util.List;

import org.beangle.commons.collection.CollectUtils;

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

  /** 督察组人员账户 **/
  private String assessBy;

  public List<SupervisorAssessItem> getItems() {
    return items;
  }

  public void setItems(List<SupervisorAssessItem> items) {
    this.items = items;
  }

  public String getAssessBy() {
    return assessBy;
  }

  public void setAssessBy(String assessBy) {
    this.assessBy = assessBy;
  }

}
