package org.openurp.webapp.apps.party.wenming.model;

public enum AssessState {

  Draft("草稿"), Submit("已提交,等待部门审核"),

  DepartApproved("部门审核通过"),

  DepartUnpassed("部门审核不通过"),

  SchoolApproved("文明办审核通过"),

  SchoolUnpassed("文明办审核不通过");
  
  private String description;

  private AssessState(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

}
