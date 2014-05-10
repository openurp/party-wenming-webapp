package org.openurp.webapp.apps.party.wenming.model;

import java.util.List;

import org.beangle.commons.collection.CollectUtils;
import org.beangle.security.blueprint.User;
import org.openurp.kernel.base.unit.model.Department;

/**
 * 单位互评信息
 * 
 * @author chaostone
 */
public class MutualAssess extends AbstractAssessInfo {

  private static final long serialVersionUID = -2755520695535451658L;

  /** 测评人员 **/
  private User assessBy;

  /** 测评单位 **/
  private Department assessDepartment;
  /**
   * 测评明细
   */
  private List<MutualAssessItem> items = CollectUtils.newArrayList();

  public List<MutualAssessItem> getItems() {
    return items;
  }

  public void setItems(List<MutualAssessItem> items) {
    this.items = items;
  }

  public User getAssessBy() {
    return assessBy;
  }

  public void setAssessBy(User assessBy) {
    this.assessBy = assessBy;
  }

  public Department getAssessDepartment() {
    return assessDepartment;
  }

  public void setAssessDepartment(Department assessDepartment) {
    this.assessDepartment = assessDepartment;
  }

}
