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

/**
 * 文明科室申报
 * 
 * @author chaostone
 */
public class ApplyAction extends SecurityActionSupport {

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
    return state == AssessState.Draft || state == AssessState.DepartUnpassed
        || state == AssessState.SchoolUnpassed;
  }

  @Override
  public String edit() {
    GoodOffice apply = (GoodOffice) getEntity();
    WenmingSession session = entityDao.get(WenmingSession.class, apply.getSession().getId());
    if (!session.isOpened()) return redirect("info", "不在申请时间段", "&session.id=" + apply.getSession().getId());
    if (editable(apply.getState())) {
      put("ifAdvise", ifAdvise(apply));
      put(getShortName(), apply);
      return forward();
    } else {
      return redirect("info", "不能修该状态的申请", "&session.id=" + apply.getSession().getId());
    }
  }

  private boolean ifAdvise(GoodOffice apply) {
    return apply!=null && apply.getState()!=null && apply.getState().equals(AssessState.SchoolUnpassed);
  }

  public String submit() {
    GoodOffice apply = (GoodOffice) getEntity();
    WenmingSession session = entityDao.get(WenmingSession.class, apply.getSession().getId());
    if (!session.isOpened()) return redirect("info", "不在申请时间段", "&session.id=" + apply.getSession().getId());
    if (editable(apply.getState())) {
      apply.setState(AssessState.Submit);
      apply.setSubmitAt(new Date());
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

  @Override
  public String save() throws Exception {
    GoodOffice apply = (GoodOffice) populateEntity();
    WenmingSession session = entityDao.get(WenmingSession.class, apply.getSession().getId());
    if (!session.isOpened()) return redirect("info", "不在申请时间段", "&session.id=" + apply.getSession().getId());
    UrpUserBean user = (UrpUserBean) entityDao.get(User.class, getUserId());
    if (editable(apply.getState())) {
      if (apply.isTransient()) apply.setDepartment(user.getDepartment());
      apply.setSubmitBy(user);
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
