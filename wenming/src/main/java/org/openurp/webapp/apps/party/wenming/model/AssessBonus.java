package org.openurp.webapp.apps.party.wenming.model;

import org.beangle.commons.entity.pojo.IntegerIdObject;

/**
 * 加分项目
 * 
 * @author chaostone
 */
public class AssessBonus extends IntegerIdObject {

  private static final long serialVersionUID = 1625841007012233533L;

  private String name;

  private AssessSchema schema;

  /** 具体加分内容 **/
  private String content;

  /** 加分方法 */
  private String method;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public AssessSchema getSchema() {
    return schema;
  }

  public void setSchema(AssessSchema schema) {
    this.schema = schema;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getMethod() {
    return method;
  }

  public void setMethod(String method) {
    this.method = method;
  }

}
