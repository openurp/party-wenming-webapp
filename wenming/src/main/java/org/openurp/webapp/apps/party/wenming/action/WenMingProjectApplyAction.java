package org.openurp.webapp.apps.party.wenming.action;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.beangle.commons.entity.util.EntityUtils;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessState;
import org.openurp.webapp.apps.party.wenming.model.AbstractWenmingFinalSummaryObject;
import org.openurp.webapp.apps.party.wenming.model.AbstractWenmingObject;
import org.openurp.webapp.apps.party.wenming.model.AbstractWenmingSummaryObject;
import org.openurp.webapp.apps.party.wenming.model.WenmingSession;
import org.openurp.webapp.apps.party.wenming.project.model.GoodProject;
import org.openurp.webapp.apps.party.wenming.project.model.GoodProjectFinalSummary;
import org.openurp.webapp.apps.party.wenming.project.model.GoodProjectMiddleSummary;

public abstract class WenMingProjectApplyAction extends WenMingProjectAction {

  public String middleSummary() {
    GoodProject good =  (GoodProject) getEntity();
    AbstractWenmingSummaryObject<GoodProject> obj = (AbstractWenmingSummaryObject<GoodProject>) good.getMiddleSummary();
    if (middleSummaryEditable(good)){
      if (obj == null){
        try {
          obj = (AbstractWenmingSummaryObject<GoodProject>) getMiddleSummaryClass().newInstance();
        } catch (Exception e) {
        }
      }
      obj.setGood(good);
      put(getShortName(getMiddleSummaryClass()), obj);
      return forward();
    }else{
      return redirect("search", "无法操作");
    }
  }

  @SuppressWarnings("rawtypes")
  public String saveMiddleSummary() {
    @SuppressWarnings("unchecked")
    GoodProjectMiddleSummary obj = (GoodProjectMiddleSummary) populateEntity(getMiddleSummaryClass(), getMiddleSummaryShortName());
    WenmingSession session = wenMingSessionService.getSession(getWenmingType());
    GoodProject good =  entityDao.get(getEntityName(), obj.getGood().getId());
    obj.setGood(good);
    if (session != null && middleSummaryEditable(good)) {
      obj.setUpdatedAt(new Date());
      obj.setUser(getUrpUser());
      obj.setCreatedAt(new Date());
      setAttachment(obj);
      setState(obj);
      entityDao.saveOrUpdate(obj);
      
      good.setMiddleSummary(obj);
      entityDao.saveOrUpdate(good);
      return redirect("search", "操作成功");
    } else {
      return redirect("search", "无法操作");
    }
  }
  public String finalSummary() {
    GoodProject good =  (GoodProject) getEntity();
    GoodProjectFinalSummary obj = (GoodProjectFinalSummary) good.getFinalSummary();
    if (finalSummaryEditable(good)){
      if (obj == null){
        try {
          obj = (GoodProjectFinalSummary) getFinalSummaryClass().newInstance();
        } catch (Exception e) {
        }
      }
      obj.setGood(good);
      obj.setContent(StringUtils.replace(obj.getContent(), "<br>", "\n"));
      put(getShortName(getFinalSummaryClass()), obj);
      put("ifAdvise", ifAdvise(obj));
      return forward();
    }else{
      return redirect("search", "无法操作");
    }
  }

  private boolean ifAdvise(GoodProjectFinalSummary obj) {
    return obj!=null && obj.getState()!=null &&obj.getState().equals(AssessState.SchoolUnpassed);
  }

  public String saveFinalSummary() {
    GoodProjectFinalSummary obj = (GoodProjectFinalSummary) populateEntity(getFinalSummaryClass(), getFinalSummaryShortName());
    WenmingSession session = wenMingSessionService.getSession(getWenmingType());
    GoodProject good =  entityDao.get(getEntityName(), obj.getGood().getId());
    obj.setGood(good);
    if (session != null && finalSummaryEditable(good)) {
      obj.setUpdatedAt(new Date());
      obj.setUser(getUrpUser());
      obj.setCreatedAt(new Date());
      obj.setContent(StringUtils.replace(obj.getContent(), "\n", "<br>"));
      setAttachment(obj);
      setState(obj);
      entityDao.saveOrUpdate(obj);
      
      good.setFinalSummary(obj);
      entityDao.saveOrUpdate(good);
      return redirect("search", "操作成功");
    } else {
      return redirect("search", "无法操作");
    }
  }

  protected boolean middleSummaryEditable(GoodProject obj) {
    return  AssessState.SchoolApproved.equals(obj.getState())
        && (obj.getMiddleSummary()==null || editable(obj.getMiddleSummary().getState()));
  }
  
  protected boolean finalSummaryEditable(GoodProject obj) {
    return AssessState.SchoolApproved.equals(obj.getState()) 
        && obj.getMiddleSummary() != null 
        && obj.getMiddleSummary().getState().equals(AssessState.DepartApproved) 
        && (obj.getFinalSummary()==null || editable(obj.getFinalSummary().getState()));
  }
  
  private String getMiddleSummaryShortName() {
    String name = getMiddleSummaryClass().getName();
    return EntityUtils.getCommandName(name);
  }

  protected abstract Class<?> getMiddleSummaryClass();
  
  private String getFinalSummaryShortName() {
    String name = getFinalSummaryClass().getName();
    return EntityUtils.getCommandName(name);
  }

  protected abstract Class<?> getFinalSummaryClass();

  protected void setState(AbstractWenmingSummaryObject obj) {
    if (getBool("save")) {
      obj.setState(AssessState.Draft);
    } else {
      obj.setState(AssessState.Submit);
    }
  }
}
