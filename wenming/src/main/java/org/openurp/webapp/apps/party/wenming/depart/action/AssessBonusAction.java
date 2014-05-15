package org.openurp.webapp.apps.party.wenming.depart.action;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.beangle.commons.collection.CollectUtils;
import org.beangle.commons.dao.Operation;
import org.beangle.commons.entity.Entity;
import org.beangle.ems.web.action.SecurityActionSupport;
import org.beangle.security.blueprint.User;
import org.beangle.struts2.convention.route.Action;
import org.openurp.kernel.base.unit.model.UrpUserBean;
import org.openurp.webapp.apps.party.wenming.action.AttachmentHelper;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessApply;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessBonus;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessBonusItem;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessSchema;
import org.openurp.webapp.apps.party.wenming.model.Attachment;

/**
 * 测评加分维护
 * 
 * @author chaostone
 */
public class AssessBonusAction extends SecurityActionSupport {
  private AttachmentHelper attachmentHelper;

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
    put("bonusItems", items);
  }

  @Override
  public String save() throws Exception {
    AssessBonus bonus = (AssessBonus) populateEntity();
    AssessApply apply = entityDao.get(AssessApply.class, bonus.getApply().getId());
    attachmentHelper.addAttachments(bonus);
    if (bonus.getAttachments().isEmpty() && bonus.isTransient()) { return forward(new Action(this, "edit"),
        "请上传材料"); }
    apply.setUpdatedAt(new Date());
    calcBonusScore(apply);
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
      bonus.getApply().getBonuses().remove(bonus);
      for (Attachment attach : bonus.getAttachments()) {
        try {
          new File(attachRoot + attach.getFilePath()).delete();
        } catch (Exception e) {
        }
      }
    }
    AssessApply apply = entities.get(0).getApply();
    calcBonusScore(apply);
    entityDao.execute(Operation.remove(entities).saveOrUpdate(apply));
    return redirect("search", "info.remove.success");
  }

  @Override
  public String info() throws Exception {
    Long applyId = getLong("assessBonus.apply.id");
    AssessApply apply = entityDao.get(AssessApply.class, applyId);
    put("assessBonuses", apply.getBonuses());
    return forward();
  }

  private int calcBonusScore(AssessApply apply) {
    int sum = 0;
    for (AssessBonus bonus : apply.getBonuses()) {
      sum += bonus.getScore();
    }
    apply.setBonus(sum);
    return sum;
  }

  public void setAttachmentHelper(AttachmentHelper attachmentHelper) {
    this.attachmentHelper = attachmentHelper;
  }
}
