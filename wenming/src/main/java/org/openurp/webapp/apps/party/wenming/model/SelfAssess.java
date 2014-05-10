package org.openurp.webapp.apps.party.wenming.model;

import java.util.List;

import org.beangle.commons.collection.CollectUtils;
import org.beangle.security.blueprint.User;

/**
 * 单位自评信息
 * 
 * @author chaostone
 */
public class SelfAssess extends AbstractAssessInfo {

  private static final long serialVersionUID = 8070990317191116252L;

  /** 测评人员 **/
  private User assessBy;
  /**
   * 测评明细
   */
  private List<SelfAssessItem> items = CollectUtils.newArrayList();

  public User getAssessBy() {
    return assessBy;
  }

  public void setAssessBy(User assessBy) {
    this.assessBy = assessBy;
  }

  public List<SelfAssessItem> getItems() {
    return items;
  }

  public void setItems(List<SelfAssessItem> items) {
    this.items = items;
  }
}
