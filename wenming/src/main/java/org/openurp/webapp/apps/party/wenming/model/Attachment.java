package org.openurp.webapp.apps.party.wenming.model;

import org.beangle.commons.entity.Component;

/**
 * 附件
 * @author chaostone
 *
 */
public class Attachment implements Component {

  public static final String DIR_CONF_NAME="attachment_dir";
  /**
   * 附件名称
   */
  private String name;

  /**
   * 附件文件路径
   */
  private String filePath;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getFilePath() {
    return filePath;
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }
  
}
