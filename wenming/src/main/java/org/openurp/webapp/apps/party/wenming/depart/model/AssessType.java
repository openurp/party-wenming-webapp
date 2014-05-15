package org.openurp.webapp.apps.party.wenming.depart.model;

public enum AssessType {

  MUTUAL("互评"), FUNC_DEPART("职能部门测评"), SUPERVISOR("督察组测评");

  private final String description;

  private AssessType(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }

  public String getId() {
    return this.name();
  }
}
