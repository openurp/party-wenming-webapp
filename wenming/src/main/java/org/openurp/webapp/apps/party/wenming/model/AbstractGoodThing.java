package org.openurp.webapp.apps.party.wenming.model;

import org.beangle.commons.entity.pojo.NumberIdTimeObject;
import org.openurp.kernel.base.unit.model.Department;

/**
 * 优秀项目，好人好事，文明示范岗的基类
 * 
 * @author chaostone
 */
public abstract class AbstractGoodThing extends NumberIdTimeObject<Long> {

  private static final long serialVersionUID = -8349323778250029726L;

  private String name;

  private Department depart;

  private AssessState state;

  private String contactPerson;

  private String contactPhone;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Department getDepart() {
    return depart;
  }

  public void setDepart(Department depart) {
    this.depart = depart;
  }

  public AssessState getState() {
    return state;
  }

  public void setState(AssessState state) {
    this.state = state;
  }

  public String getContactPerson() {
    return contactPerson;
  }

  public void setContactPerson(String contactPerson) {
    this.contactPerson = contactPerson;
  }

  public String getContactPhone() {
    return contactPhone;
  }

  public void setContactPhone(String contactPhone) {
    this.contactPhone = contactPhone;
  }

}
