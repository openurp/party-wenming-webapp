package org.openurp.webapp.apps.party.wenming.action;

import java.util.Date;

import org.beangle.commons.dao.query.builder.OqlBuilder;
import org.openurp.webapp.apps.party.wenming.model.AssessApply;
import org.openurp.webapp.apps.party.wenming.model.AssessSession;
import org.openurp.webapp.apps.party.wenming.model.AssessState;

/**
 * 文明办管理申请
 * 
 * @author chaostone
 */
public class ApplyManageAction extends AbstractApplyAction {

  @Override
  protected void indexSetting() {
    OqlBuilder<AssessSession> builder = OqlBuilder.from(AssessSession.class, "ss");
    builder.where("ss.enabled=true").orderBy("ss.beginOn desc");
    put("sessions", entityDao.search(builder));
    put("departments", departmentService.getActives());
    put("states", AssessState.values());
  }

  private boolean editable(AssessState state) {
    return state == AssessState.DepartApproved || state == AssessState.SchoolUnpassed;
  }

  @Override
  public String info() throws Exception {
    Long entityId = getId(getShortName(), Long.class);
    if (null != entityId) {
      AssessApply apply = entityDao.get(AssessApply.class, entityId);
      put("editable", editable(apply.getState()));
      put(getShortName(), apply);
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
    if (editable(apply.getState())) {
      apply.setState(AssessState.SchoolApproved);
      apply.setUpdatedAt(new Date());
      entityDao.saveOrUpdate(apply);
      return redirect("search", "info.save.success", "&session.id=" + apply.getSession().getId());
    } else {
      return redirect("search", "不能修该状态的申请", "&session.id=" + apply.getSession().getId());
    }
  }

  /**
   * 退回修改
   * 
   * @return
   */
  public String disApprove() {
    AssessApply apply = (AssessApply) getEntity();
    if (editable(apply.getState())) {
      apply.setState(AssessState.SchoolUnpassed);
      apply.setUpdatedAt(new Date());
      entityDao.saveOrUpdate(apply);
      return redirect("search", "info.save.success", "&session.id=" + apply.getSession().getId());
    } else {
      return redirect("search", "不能修该状态的申请", "&session.id=" + apply.getSession().getId());
    }
  }
}
