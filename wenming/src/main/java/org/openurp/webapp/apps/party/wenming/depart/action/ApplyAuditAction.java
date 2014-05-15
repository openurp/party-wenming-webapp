package org.openurp.webapp.apps.party.wenming.depart.action;

import java.util.Date;
import java.util.List;

import org.beangle.commons.dao.query.builder.OqlBuilder;
import org.beangle.security.blueprint.User;
import org.openurp.kernel.base.unit.model.UrpUserBean;
import org.openurp.webapp.apps.party.wenming.action.AttachmentHelper;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessApply;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessSession;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessState;

/**
 * 文明单位申请审批
 * 
 * @author chaostone
 */
public class ApplyAuditAction extends AbstractApplyAction {

  private AttachmentHelper attachmentHelper;

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
    return state == AssessState.Submit;
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
      }
      put("assessSession", session);
    }
    return forward();
  }

  /**
   * 同意提交，审批通过
   * 
   * @return
   */
  public String approve() {
    AssessApply apply = (AssessApply) getEntity();
    if (!apply.getSession().isOpened()) return redirect("info", "不在申请时间段", "&session.id="
        + apply.getSession().getId());
    if (editable(apply.getState())) {
      apply.setState(AssessState.DepartApproved);
      apply.setUpdatedAt(new Date());
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
    AssessApply apply = (AssessApply) getEntity();
    if (!apply.getSession().isOpened()) return redirect("info", "不在申请时间段", "&session.id="
        + apply.getSession().getId());
    if (editable(apply.getState())) {
      apply.setState(AssessState.DepartUnpassed);
      apply.setUpdatedAt(new Date());
      entityDao.saveOrUpdate(apply);
      return redirect("info", "info.save.success", "&session.id=" + apply.getSession().getId());
    } else {
      return redirect("info", "不能修该状态的申请", "&session.id=" + apply.getSession().getId());
    }
  }

  /**
   * 审核部门可以再次修改
   */
  @Override
  public String save() throws Exception {
    AssessApply apply = (AssessApply) populateEntity();
    if (!apply.getSession().isOpened()) return redirect("info", "不在申请时间段", "&session.id="
        + apply.getSession().getId());
    if (editable(apply.getState())) {
      UrpUserBean user = (UrpUserBean) entityDao.get(User.class, getUserId());
      if (apply.isTransient()) apply.setDepartment(user.getDepartment());
      apply.setAuditBy(user);
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
