package org.openurp.webapp.apps.party.wenming.action;

import java.io.File;

import org.beangle.commons.lang.Strings;
import org.beangle.commons.web.io.StreamDownloader;
import org.beangle.ems.web.action.SecurityActionSupport;
import org.openurp.webapp.apps.party.wenming.model.Attachment;

/**
 * 附件下载
 * 
 * @author chaostone
 */
public class AttachmentAction extends SecurityActionSupport {

  private StreamDownloader downloader;

  @Override
  public String index() {
    String filePath = get("path");
    String fileName = get("name");

    String attachRoot = getConfig().get(Attachment.DIR_CONF_NAME).toString();
    if (Strings.isEmpty(fileName)) {
      downloader.download(getRequest(), getResponse(), new File(attachRoot + filePath));
    } else {
      downloader.download(getRequest(), getResponse(), new File(attachRoot + filePath), fileName);
    }
    return null;
  }

  public StreamDownloader getDownloader() {
    return downloader;
  }

  public void setDownloader(StreamDownloader downloader) {
    this.downloader = downloader;
  }

}
