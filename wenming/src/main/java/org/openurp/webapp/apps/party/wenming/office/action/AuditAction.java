package org.openurp.webapp.apps.party.wenming.office.action;

import java.util.Date;
import java.util.List;

import org.beangle.commons.dao.query.builder.OqlBuilder;
import org.beangle.ems.web.action.SecurityActionSupport;
import org.beangle.security.blueprint.User;
import org.openurp.kernel.base.unit.model.UrpUserBean;
import org.openurp.webapp.apps.party.wenming.action.AttachmentHelper;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessState;
import org.openurp.webapp.apps.party.wenming.model.WenmingSession;
import org.openurp.webapp.apps.party.wenming.office.model.GoodOffice;

public class AuditAction extends SecurityActionSupport {

  private AttachmentHelper attachmentHelper;

  @Override
  protected String getEntityName() {
    return GoodOffice.class.getName();
  }

  @Override
  protected void indexSetting() {
    UrpUserBean user = (UrpUserBean) entityDao.get(User.class, getUserId());
    List<WenmingSession> sessions = entityDao.getAll(WenmingSession.class);
    Integer sessionId = getInt("session.id");
    if (null != sessionId) put("wenmingSession", entityDao.get(WenmingSession.class, sessionId));
    put("user", user);
    put("sessions", sessions);
  }

  private boolean editable(AssessState state) {
    return state == AssessState.Submit;
  }

  /**
   * 查看信息
   */
  public String info() throws Exception {
    Integer sessionId = getInt("session.id");
    UrpUserBean user = (UrpUserBean) entityDao.get(User.class, getUserId());
    if (null != sessionId) {
      WenmingSession session = entityDao.get(WenmingSession.class, sessionId);
      OqlBuilder<GoodOffice> builder = OqlBuilder.from(GoodOffice.class, "aa");
      builder.where("aa.session=:session", session);
      builder.where("aa.department=:department", user.getDepartment());
      List<GoodOffice> applies = entityDao.search(builder);
      if (applies.size() == 1) {
        put("goodOffice", applies.get(0));
        put("editable", editable(applies.get(0).getState()));
      }
      put("wenmingSession", session);
    }
    return forward();
  }

  /**
   * 同意提交，审批通过
   * 
   * @return
   */
  public String approve() {
    UrpUserBean auditBy = (UrpUserBean) entityDao.get(User.class, getUserId());
    GoodOffice apply = (GoodOffice) getEntity();
    if (!apply.getSession().isOpened()) return redirect("info", "不在申请时间段", "&session.id="
        + apply.getSession().getId());
    if (editable(apply.getState())) {
      apply.setState(AssessState.DepartApproved);
      apply.setUpdatedAt(new Date());
      apply.setAuditBy(auditBy);
      apply.setAuditAt(new Date());
      entityDao.saveOrUpdate(apply);
      return redirect("info", "info.save.success", "&session.id=" + apply.getSession().getId());
    } else {
      return redirect("info", "不能修该状态的申请", "&session.id=" + apply.getSession().getId());
    }
  }

  /**
   * 退回修改
   * 
   * @return
   */
  public String disApprove() {
    GoodOffice apply = (GoodOffice) getEntity();
    UrpUserBean auditBy = (UrpUserBean) entityDao.get(User.class, getUserId());
    if (!apply.getSession().isOpened()) return redirect("info", "不在申请时间段", "&session.id="
        + apply.getSession().getId());
    if (editable(apply.getState())) {
      apply.setState(AssessState.DepartUnpassed);
      apply.setAuditBy(auditBy);
      apply.setAuditAt(new Date());
      apply.setUpdatedAt(new Date());
      entityDao.saveOrUpdate(apply);
      return redirect("info", "info.save.success", "&session.id=" + apply.getSession().getId());
    } else {
      return redirect("info", "不能修该状态的申请", "&session.id=" + apply.getSession().getId());
    }
  }

  @Override
  public String save() throws Exception {
    GoodOffice apply = (GoodOffice) populateEntity();
    WenmingSession session = entityDao.get(WenmingSession.class, apply.getSession().getId());
    if (!session.isOpened()) return redirect("info", "不在申请时间段", "&session.id=" + apply.getSession().getId());
    UrpUserBean user = (UrpUserBean) entityDao.get(User.class, getUserId());
    if (editable(apply.getState())) {
      if (apply.isTransient()) apply.setDepartment(user.getDepartment());
      if (!apply.isPersisted()) apply.setCreatedAt(new Date());
      apply.setUpdatedAt(new Date());
      attachmentHelper.setAttachment(apply);
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

  public void setAttachmentHelper(AttachmentHelper attachmentHelper) {
    this.attachmentHelper = attachmentHelper;
  }
}
