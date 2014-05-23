package org.openurp.webapp.apps.party.wenming.action;

import java.util.Date;

import org.beangle.commons.collection.Order;
import org.beangle.commons.dao.query.builder.OqlBuilder;
import org.beangle.commons.entity.Entity;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessState;
import org.openurp.webapp.apps.party.wenming.model.AbstractWenmingObject;
import org.openurp.webapp.apps.party.wenming.project.model.GoodProject;

public abstract class WenMingProjectDepartAuditAction extends WenMingProjectAction {

  @Override
  protected <T extends Entity<?>> OqlBuilder<T> getQueryBuilder() {
    OqlBuilder<T> query = OqlBuilder.from(getEntityName(), getShortName());
    populateConditions(query);
    query.orderBy(get(Order.ORDER_STR)).limit(getPageLimit());
    query.where("department = :department", getDepartment());
    return query;
  }

  protected void setState(AbstractWenmingObject obj) {
    obj.setUpdatedAt(new Date());
    if (AssessState.Submit.equals(obj.getState())) {
      if (!getBool("save")) {
        if (getBool("passed")) {
          obj.setState(AssessState.DepartApproved);
        } else {
          obj.setState(AssessState.DepartUnpassed);
        }
      }
    } else if (AssessState.SchoolApproved.equals(obj.getState()) && obj instanceof GoodProject) {
      GoodProject good = (GoodProject)obj;
      if (good.getMiddleSummary().getAttachment() != null && AssessState.Submit.equals(good.getMiddleSummary().getState())) {
        if (getBool("passed")) {
          good.getMiddleSummary().setState(AssessState.DepartApproved);
        } else {
          good.getMiddleSummary().setState(AssessState.DepartUnpassed);
        }
      } else if (good.getFinalSummary().getAttachment() != null && AssessState.Submit.equals(good.getFinalSummary().getState())) {
        if (getBool("passed")) {
          good.getFinalSummary().setState(AssessState.DepartApproved);
        } else {
          good.getFinalSummary().setState(AssessState.DepartUnpassed);
        }
      }
    }
  }

  protected boolean editable(AbstractWenmingObject obj) {
    if(AssessState.Submit.equals(obj.getState())){
      return true;
    }else if (obj instanceof GoodProject){
      GoodProject good = (GoodProject)obj;
      return (good.getMiddleSummary() != null && AssessState.Submit.equals(good.getMiddleSummary().getState()))
          || (good.getFinalSummary() != null && AssessState.Submit.equals(good.getFinalSummary().getState()));
    }
    return false;
  }
  
}
