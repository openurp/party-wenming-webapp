package org.openurp.webapp.apps.party.wenming.action;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import org.beangle.commons.dao.query.builder.OqlBuilder;
import org.beangle.commons.entity.Entity;
import org.openurp.webapp.apps.party.wenming.depart.action.WenMingAction;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessState;
import org.openurp.webapp.apps.party.wenming.model.AbstractWenmingObject;
import org.openurp.webapp.apps.party.wenming.model.WenmingSession;
import org.openurp.webapp.apps.party.wenming.model.WenmingType;
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
  public String search() {
    put("editable", editable());
    return super.search();
  }

  @Override
  protected <T extends Entity<?>> OqlBuilder<T> getQueryBuilder() {
    OqlBuilder<T> query = super.getQueryBuilder();
    query.where("submitBy.id = :submitByid", getUserId());
    return query;
  }
  
  @Override
  public String edit() {
    Entity<?> entity = getEntity();
    WenmingSession session = wenMingSessionService.getSession(getWenmingType());
    AbstractWenmingObject obj = (AbstractWenmingObject) entity;
    if (session != null && (obj.getSession() == null || session.equals(obj.getSession())) && editable(obj.getState())) {
      put(getShortName(), entity);
      editSetting(entity);
      return forward();
    } else {
      return redirect("search", "无法修改", getShortName() + ".id=" + obj.getId());
    }
  }

  @Override
  protected String saveAndForward(Entity<?> entity) {
    AbstractWenmingObject obj = (AbstractWenmingObject) entity;
    WenmingSession session = wenMingSessionService.getSession(getWenmingType());
    if (session != null && editable(obj.getState())) {
      if (obj.getId() == null) {
        obj.setSession(wenMingSessionService.getSession(getWenmingType()));
        obj.setDepartment(getDepartment());
        obj.setCreatedAt(new Date());
      }
      obj.setUpdatedAt(new Date());
      obj.setSubmitBy(getUrpUser());
      obj.setSubmitAt(new Date());
      setAttachment(obj);
      if (getBool("save")) {
        obj.setState(AssessState.Draft);
      } else {
        obj.setState(AssessState.Submit);
      }
      return super.saveAndForward(entity);
    } else {
      return redirect("search", "无法修改", getShortName() + ".id=" + obj.getId());
    }
  }
  
  @Override
  protected String removeAndForward(Collection<?> entities) {
    for(@SuppressWarnings("unchecked")
    Iterator<AbstractWenmingObject> itor = (Iterator<AbstractWenmingObject>) entities.iterator(); itor.hasNext();){
      AbstractWenmingObject obj = itor.next();
      if(!editable(obj.getState())){
        itor.remove();
      }
    }
    return super.removeAndForward(entities);
  }

}
