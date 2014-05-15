package org.openurp.webapp.apps.party.wenming.depart.action;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.apache.commons.io.FileUtils;
import org.beangle.commons.dao.query.builder.OqlBuilder;
import org.beangle.commons.lang.Strings;
import org.beangle.security.blueprint.User;
import org.openurp.kernel.base.unit.model.UrpUserBean;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessApply;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessSchema;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessSession;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessState;
import org.openurp.webapp.apps.party.wenming.model.Attachment;

/**
 * 文明单位申请
 * 
 * @author chaostone
 */
public class ApplyAction extends AbstractApplyAction {

  @Override
  protected void indexSetting() {
    UrpUserBean user = (UrpUserBean) entityDao.get(User.class, getUserId());
    List<AssessSession> sessions = wenMingService.findSessions(user.getDepartment());
    Integer sessionId = getInt("session.id");
    if (null != sessionId) put("assessSession", entityDao.get(AssessSession.class, sessionId));
    put("user", user);
    put("sessions", sessions);
  }

  private boolean editable(AssessState state) {
    return state == AssessState.Draft || state == AssessState.DepartUnpassed
        || state == AssessState.SchoolUnpassed;
  }

  @Override
  public String edit() {
    AssessApply apply = (AssessApply) getEntity();
    AssessSession session = entityDao.get(AssessSession.class, apply.getSession().getId());
    if (!session.isOpened()) return redirect("info", "不在申请时间段", "&session.id=" + apply.getSession().getId());
    if (editable(apply.getState())) {
      put(getShortName(), apply);
      return forward();
    } else {
      return redirect("info", "不能修该状态的申请", "&session.id=" + apply.getSession().getId());
    }
  }

  public String submit() {
    AssessApply apply = (AssessApply) getEntity();
    AssessSession session = entityDao.get(AssessSession.class, apply.getSession().getId());
    if (!session.isOpened()) return redirect("info", "不在申请时间段", "&session.id=" + apply.getSession().getId());
    if (editable(apply.getState())) {
      apply.setState(AssessState.Submit);
      apply.setUpdatedAt(new Date());
      entityDao.saveOrUpdate(apply);
      return redirect("info", "info.save.success", "&session.id=" + apply.getSession().getId());
    } else {
      return redirect("info", "不能修该状态的申请", "&session.id=" + apply.getSession().getId());
    }
  }

  /**
   * 查看信息
   */
  public String info() throws Exception {
    Integer sessionId = getInt("session.id");
    UrpUserBean user = (UrpUserBean) entityDao.get(User.class, getUserId());
    if (null != sessionId) {
      AssessSession session = entityDao.get(AssessSession.class, sessionId);
      OqlBuilder<AssessApply> builder = OqlBuilder.from(AssessApply.class, "aa");
      builder.where("aa.session=:session", session);
      builder.where("aa.department=:department", user.getDepartment());
      List<AssessApply> applies = entityDao.search(builder);
      if (applies.size() == 1) {
        put("assessApply", applies.get(0));
        put("editable", editable(applies.get(0).getState()));
        put("submitable",
            Objects.equals(applies.get(0).getState(), AssessState.Draft)
                || Objects.equals(applies.get(0).getState(), AssessState.DepartUnpassed));
      }
      put("assessSession", session);
    }
    return forward();
  }

  @Override
  public String save() throws Exception {
    AssessApply apply = (AssessApply) populateEntity();
    AssessSession session = entityDao.get(AssessSession.class, apply.getSession().getId());
    if (!session.isOpened()) return redirect("info", "不在申请时间段", "&session.id=" + apply.getSession().getId());
    UrpUserBean user = (UrpUserBean) entityDao.get(User.class, getUserId());
    if (editable(apply.getState())) {
      if (apply.isTransient()) apply.setDepartment(user.getDepartment());
      apply.setSubmitBy(user);
      if (!apply.isPersisted()) apply.setCreatedAt(new Date());
      apply.setUpdatedAt(new Date());
      Object[] files = getAll("attachment");
      if (null != files && files.length != 0 && files[0] instanceof File) {
        String[] fileNames = getAll("attachmentFileName", String.class);
        Attachment attach = new Attachment();
        String attachRoot = getConfig().get(Attachment.DIR_CONF_NAME).toString();
        attach.setName(fileNames[0]);
        attach.setFilePath("/apply/attachment/" + apply.getDepartment().getId() + "."
            + Strings.substringAfterLast(fileNames[0], "."));
        if (null != apply.getAttachment() && null != apply.getAttachment().getFilePath()) {
          new File(attachRoot + apply.getAttachment().getFilePath()).delete();
        }
        FileUtils.copyFile((File) files[0], new File(attachRoot + attach.getFilePath()));
        apply.setAttachment(attach);
      }
      try {
        if (apply.isTransient()) {
          for (AssessSchema schema : session.getSchemas()) {
            if (schema.getDeparts().contains(user.getDepartment())) {
              apply.setSchema(schema);
              break;
            }
          }
        }
        saveOrUpdate(apply);
        return redirect("info", "info.save.success", "&session.id=" + apply.getSession().getId());
      } catch (Exception e) {
        logger.info("saveAndForwad failure", e);
        return redirect("info", "info.save.failure", "&session.id=" + apply.getSession().getId());
      }
    } else {
      return redirect("info", "不能修该状态的申请", "&session.id=" + apply.getSession().getId());
    }
  }
}
