package org.openurp.kernel.base.unit.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.beangle.security.blueprint.model.UserBean;

@Entity(name = "org.beangle.security.blueprint.User")
public class UrpUserBean extends UserBean {

  private static final long serialVersionUID = 1920392743471671193L;
  @ManyToOne
  private Department department;

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

}
