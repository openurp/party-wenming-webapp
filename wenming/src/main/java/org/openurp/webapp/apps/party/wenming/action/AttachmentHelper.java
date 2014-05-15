package org.openurp.webapp.apps.party.wenming.action;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.beangle.commons.config.property.PropertyConfig;
import org.beangle.commons.lang.Strings;
import org.beangle.struts2.helper.Params;
import org.openurp.webapp.apps.party.wenming.model.Attachment;
import org.openurp.webapp.apps.party.wenming.model.AttachmentObject;

public class AttachmentHelper {
  
  protected PropertyConfig config;

  public void setAttachment(AttachmentObject obj) {
    Object[] files = Params.getAll("attachment");
    if (null != files && files.length == 1 && files[0] instanceof File) {
      String[] fileNames =Params.getAll("attachmentFileName", String.class);
      Attachment attach = new Attachment();
      String attachRoot =config.get(Attachment.DIR_CONF_NAME).toString();
      attach.setName(fileNames[0]);
      attach.setFilePath("/wenming/" + new SimpleDateFormat("yyyy").format(new Date()) + "/"
          + obj.getClass().getSimpleName().toLowerCase() + "/" + UUID.randomUUID()
          + Strings.substringAfterLast(fileNames[0], "."));
      if (null != obj.getAttachment() && null != obj.getAttachment().getFilePath()) {
        new File(attachRoot + obj.getAttachment().getFilePath()).delete();
      }
      try {
        FileUtils.copyFile((File) files[0], new File(attachRoot + attach.getFilePath()));
      } catch (IOException e) {
        e.printStackTrace();
      }
      obj.setAttachment(attach);
    }
  }

  public void setConfig(PropertyConfig config) {
    this.config = config;
  }
}
