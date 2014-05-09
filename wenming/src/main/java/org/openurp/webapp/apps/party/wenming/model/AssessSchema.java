package org.openurp.webapp.apps.party.wenming.model;

import java.util.List;

import org.beangle.commons.collection.CollectUtils;
import org.beangle.commons.entity.pojo.NumberIdTimeObject;
import org.openurp.kernel.base.unit.model.Department;

/**
 * 评价方案
 * 
 * @author chaostone
 */
public class AssessSchema extends NumberIdTimeObject<Integer> {

  private static final long serialVersionUID = 2565921632983531365L;

  private String name;

  private List<AssessItemGroup> groups = CollectUtils.newArrayList();

  private List<Department> departs = CollectUtils.newArrayList();

  private List<AssessBonus> bonuses = CollectUtils.newArrayList();

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<AssessItemGroup> getGroups() {
    return groups;
  }

  public void setGroups(List<AssessItemGroup> groups) {
    this.groups = groups;
  }

  public List<Department> getDeparts() {
    return departs;
  }

  public void setDeparts(List<Department> departs) {
    this.departs = departs;
  }

  public List<AssessBonus> getBonuses() {
    return bonuses;
  }

  public void setBonuses(List<AssessBonus> bonuses) {
    this.bonuses = bonuses;
  }

}
