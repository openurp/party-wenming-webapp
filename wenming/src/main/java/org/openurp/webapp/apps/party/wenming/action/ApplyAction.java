package org.openurp.webapp.apps.party.wenming.action;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.apache.commons.io.FileUtils;
import org.beangle.commons.dao.query.builder.OqlBuilder;
import org.beangle.commons.lang.Strings;
import org.beangle.ems.web.action.SecurityActionSupport;
import org.beangle.security.blueprint.User;
import org.openurp.kernel.base.unit.model.UrpUserBean;
import org.openurp.webapp.apps.party.wenming.model.AssessApply;
import org.openurp.webapp.apps.party.wenming.model.AssessSession;
import org.openurp.webapp.apps.party.wenming.model.AssessState;
import org.openurp.webapp.apps.party.wenming.model.Attachment;

/**
 * 文明单位申请
 * 
 * @author chaostone
 */
public class ApplyAction extends SecurityActionSupport {

  @Override
  protected void indexSetting() {
    OqlBuilder<AssessSession> builder = OqlBuilder.from(AssessSession.class, "ss");
    builder.where("ss.enabled=true").orderBy("ss.beginOn desc");
    Integer sessionId = getInt("session.id");
    if (null != sessionId) put("assessSession", entityDao.get(AssessSession.class, sessionId));
    UrpUserBean user = (UrpUserBean) entityDao.get(User.class, getUserId());
    put("user", user);
    put("sessions", entityDao.search(builder));
  }

  protected String getEntityName() {
    return AssessApply.class.getName();
  }

  private boolean editable(AssessState state) {
    return state == AssessState.Draft || state == AssessState.DepartUnpassed
        || state == AssessState.SchoolUnpassed;
  }

  @Override
  public String edit() {
    AssessApply apply = (AssessApply) getEntity();
    if (editable(apply.getState())) {
      put(getShortName(), apply);
      return forward();
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
      OqlBuilder<AssessApply> builder = OqlBuilder.from(AssessApply.class, "aa");
      builder.where("aa.session.id=:sessionid", sessionId);
      builder.where("aa.department=:department", user.getDepartment());
      List<AssessApply> applies = entityDao.search(builder);
      if (applies.size() == 1) {
        put("assessApply", applies.get(0));
        put("editable", editable(applies.get(0).getState()));
        put("submitable", Objects.equals(applies.get(0).getState(), AssessState.Draft));
      }
    }
    return forward();
  }

  @Override
  public String save() throws Exception {
    AssessApply apply = (AssessApply) populateEntity();
    if (editable(apply.getState())) {
      UrpUserBean user = (UrpUserBean) entityDao.get(User.class, getUserId());
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
