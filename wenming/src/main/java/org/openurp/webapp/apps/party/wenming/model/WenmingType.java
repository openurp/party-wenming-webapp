package org.openurp.webapp.apps.party.wenming.model;

/**
 * 文明单位类型
 * @author chaostone
 *
 */
public enum WenmingType {

  Department("文明单位"), Office("文明科室"), Project("优秀项目"), Person("好人好事"), Post("文明示范岗");

  private final String description;

  private WenmingType(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }

  public String getId() {
    return name();
  }

}
