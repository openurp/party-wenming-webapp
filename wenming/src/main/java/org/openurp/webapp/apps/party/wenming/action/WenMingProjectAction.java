package org.openurp.webapp.apps.party.wenming.action;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import org.apache.commons.lang.StringUtils;
import org.beangle.commons.dao.query.builder.OqlBuilder;
import org.beangle.commons.entity.Entity;
import org.openurp.kernel.base.unit.model.UrpUserBean;
import org.openurp.webapp.apps.party.wenming.depart.action.WenMingAction;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessSession;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessState;
import org.openurp.webapp.apps.party.wenming.model.AbstractWenmingObject;
import org.openurp.webapp.apps.party.wenming.model.WenmingSession;
import org.openurp.webapp.apps.party.wenming.model.WenmingType;
import org.openurp.webapp.apps.party.wenming.project.model.GoodProject;
import org.openurp.webapp.apps.party.wenming.service.WenMingSessionService;

public abstract class WenMingProjectAction extends WenMingAction {

  protected WenMingSessionService wenMingSessionService;

  public void setWenMingSessionService(WenMingSessionService wenMingSessionService) {
    this.wenMingSessionService = wenMingSessionService;
  }

  protected boolean editable() {
    return wenMingSessionService.getSession(getWenmingType()) != null;
  }

  abstract protected WenmingType getWenmingType();

  @Override
  protected void indexSetting() {
    OqlBuilder<WenmingSession> builder = OqlBuilder.from(WenmingSession.class, "ss");
    builder.join("ss.types", "t");
    builder.where("t=:t", getWenmingType()).orderBy("ss.beginOn desc");
    put("sessions", entityDao.search(builder));
  }
  @Override
  public String search() {
    put("editable", editable());
    return super.search();
  }

  @Override
  protected <T extends Entity<?>> OqlBuilder<T> getQueryBuilder() {
    OqlBuilder<T> query = super.getQueryBuilder();
    UrpUserBean user = entityDao.get(UrpUserBean.class, getUserId());
    query.where("department = :department", user.getDepartment());
    return query;
  }

  @Override
  public String edit() {
    Entity<?> entity = getEntity();
    WenmingSession session = wenMingSessionService.getSession(getWenmingType());
    AbstractWenmingObject obj = (AbstractWenmingObject) entity;
    obj.setStatements(StringUtils.replace(obj.getStatements(), "<br>", "\n"));
    obj.setFeatures(StringUtils.replace(obj.getFeatures(), "<br>", "\n"));
    put("alterable", alterable(obj));
    put("ifApplyAudit", ifApplyAudit(obj));
    if (obj instanceof GoodProject) {
      GoodProject gp = (GoodProject) entity;
      put("ifMiddleSummary", ifMiddleSummary(gp));
      put("ifFinalSummary", ifFinalSummary(gp));
      gp.setPlan(StringUtils.replace(gp.getPlan(), "<br>", "\n"));
    }
    if (session != null && (obj.getSession() == null || session.equals(obj.getSession())) && editable(obj)) {
      put(getShortName(), entity);
      editSetting(entity);
      return forward();
    } else {
      return redirect("search", "无法修改");
    }
  }

  protected boolean editable(AbstractWenmingObject obj) {
    return editable(obj.getState());
  }

  @Override
  protected String saveAndForward(Entity<?> entity) {
    AbstractWenmingObject obj = (AbstractWenmingObject) entity;
    WenmingSession session = wenMingSessionService.getSession(getWenmingType());
    if (session != null && editable(obj)) {
      if (obj.getId() == null) {
        obj.setSession(wenMingSessionService.getSession(getWenmingType()));
        obj.setDepartment(getDepartment());
        obj.setCreatedAt(new Date());
      }
      obj.setUpdatedAt(new Date());
      obj.setSubmitBy(getUrpUser());
      obj.setSubmitAt(new Date());
      obj.setStatements(StringUtils.replace(obj.getStatements(), "\n", "<br>"));
      obj.setFeatures(StringUtils.replace(obj.getFeatures(), "\n", "<br>"));
      if (obj instanceof GoodProject) {
        GoodProject gp = (GoodProject) entity;
        gp.setPlan(StringUtils.replace(gp.getPlan(), "\n", "<br>"));
      }
      setAttachment(obj);
      setState(obj);
      return super.saveAndForward(entity);
    } else {
      return redirect("search", "无法修改");
    }
  }

  protected void setState(AbstractWenmingObject obj) {
    if (getBool("save")) {
      obj.setState(AssessState.Draft);
    } else {
      obj.setState(AssessState.Submit);
    }
  }

  @Override
  protected String removeAndForward(Collection<?> entities) {
    int reserved = 0;
    for (@SuppressWarnings("unchecked")
    Iterator<AbstractWenmingObject> itor = (Iterator<AbstractWenmingObject>) entities.iterator(); itor
        .hasNext();) {
      AbstractWenmingObject obj = itor.next();
      if (!editable(obj.getState())) {
        itor.remove();
        reserved += 1;
      }
    }
    if (reserved > 0) return redirect("search", "无法删除" + reserved + "个申请");
    else return super.removeAndForward(entities);
  }

  protected boolean alterable(AbstractWenmingObject obj) {
    return AssessState.Submit.equals(obj.getState());
  }

  protected boolean ifApplyAudit(AbstractWenmingObject obj) {
    return AssessState.DepartApproved.equals(obj.getState());
  }

  protected boolean ifMiddleSummary(GoodProject obj) {
    return (obj.getMiddleSummary() != null && obj.getMiddleSummary().getAttachment() != null && obj
        .getFinalSummary() == null);
  }

  protected boolean ifFinalSummary(GoodProject obj) {
    return (obj.getMiddleSummary() != null && obj.getMiddleSummary().getAttachment() != null
        && obj.getFinalSummary() != null && obj.getFinalSummary().getAttachment() != null);
  }
}
