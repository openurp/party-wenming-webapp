package org.openurp.webapp.apps.party.wenming.action;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.beangle.commons.collection.CollectUtils;
import org.beangle.commons.entity.Entity;
import org.beangle.commons.lang.Strings;
import org.beangle.ems.web.action.SecurityActionSupport;
import org.beangle.security.blueprint.User;
import org.beangle.struts2.convention.route.Action;
import org.openurp.kernel.base.unit.model.UrpUserBean;
import org.openurp.webapp.apps.party.wenming.model.AssessApply;
import org.openurp.webapp.apps.party.wenming.model.AssessBonus;
import org.openurp.webapp.apps.party.wenming.model.AssessBonusItem;
import org.openurp.webapp.apps.party.wenming.model.AssessSchema;
import org.openurp.webapp.apps.party.wenming.model.Attachment;

/**
 * 测评加分维护
 * 
 * @author chaostone
 */
public class AssessBonusAction extends SecurityActionSupport {
  protected String getEntityName() {
    return AssessBonus.class.getName();
  }

  @Override
  protected void editSetting(Entity<?> entity) {
    Long applyId = getLong("assessBonus.apply.id");
    UrpUserBean user = (UrpUserBean) entityDao.get(User.class, getUserId());
    AssessApply apply = entityDao.get(AssessApply.class, applyId);
    List<AssessBonusItem> items = CollectUtils.newArrayList();
    for (AssessSchema schema : apply.getSession().getSchemas()) {
      if (schema.getDeparts().contains(user.getDepartment())) {
        items.addAll(schema.getBonusItems());
      }
    }
    if (items.isEmpty()) {
      items = entityDao.getAll(AssessBonusItem.class);
    }
    put("bonusItems", items);
  }

  @Override
  public String save() throws Exception {
    AssessBonus bonus = (AssessBonus) populateEntity();
    AssessApply apply = entityDao.get(AssessApply.class, bonus.getApply().getId());
    Object[] files = getAll("attachment");
    if (null != files && files.length != 0 && files[0] instanceof File) {
      String[] fileNames = getAll("attachmentFileName", String.class);
      for (String fileName : fileNames) {
        Attachment attach = new Attachment();
        String attachRoot = getConfig().get(Attachment.DIR_CONF_NAME).toString();
        attach.setName(fileName);
        attach.setFilePath("/apply/bonus/" + apply.getId() + "/" + bonus.getAttachments().size() + "."
            + Strings.substringAfterLast(fileName, "."));
        FileUtils.copyFile((File) files[0], new File(attachRoot + attach.getFilePath()));
        bonus.getAttachments().add(attach);
      }
    } else if (bonus.isTransient()) { return forward(new Action(this, "edit"), "请上传材料"); }
    apply.setUpdatedAt(new Date());
    try {
      entityDao.saveOrUpdate(apply, bonus);
      return redirect("search", "info.save.success", "&assessBonus.apply.id=" + apply.getId());
    } catch (Exception e) {
      logger.info("saveAndForwad failure", e);
      return redirect("search", "info.save.failure", "&assessBonus.apply.id=" + apply.getId());
    }
  }

  @Override
  public String remove() throws Exception {
    List<AssessBonus> entities = entityDao.get(AssessBonus.class, getIds("assessBonus", Long.class));
    String attachRoot = getConfig().get(Attachment.DIR_CONF_NAME).toString();
    for (AssessBonus bonus : entities) {
      for (Attachment attach : bonus.getAttachments())
        new File(attachRoot + attach.getFilePath()).delete();
    }
    entityDao.remove(entities);
    return redirect("search", "info.remove.success");
  }

  @Override
  public String info() throws Exception {
    Long applyId = getLong("assessBonus.apply.id");
    AssessApply apply = entityDao.get(AssessApply.class, applyId);
    put("assessBonuses", apply.getBonuses());
    return forward();
  }
}
