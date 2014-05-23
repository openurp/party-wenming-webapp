package org.openurp.webapp.apps.party.wenming.action;

import java.util.Date;

import org.beangle.commons.collection.Order;
import org.beangle.commons.dao.query.builder.OqlBuilder;
import org.beangle.commons.entity.Entity;
import org.beangle.struts2.convention.route.Action;
import org.openurp.webapp.apps.party.wenming.depart.action.AssessItemGroupAction;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessState;
import org.openurp.webapp.apps.party.wenming.model.AbstractWenmingObject;
import org.openurp.webapp.apps.party.wenming.model.AbstractWenmingSummaryObject;
import org.openurp.webapp.apps.party.wenming.project.model.GoodProject;

public abstract class WenMingProjectAuditAction extends WenMingProjectAction {

  @Override
  protected <T extends Entity<?>> OqlBuilder<T> getQueryBuilder() {
    OqlBuilder<T> query = OqlBuilder.from(getEntityName(), getShortName());
    populateConditions(query);
    query.orderBy(get(Order.ORDER_STR)).limit(getPageLimit());
    return query;
  }

  protected void setState(AbstractWenmingObject obj) {
    obj.setUpdatedAt(new Date());
    if (AssessState.DepartApproved.equals(obj.getState())) {
      if (getBool("passed")) {
        obj.setState(AssessState.SchoolApproved);
      } else {
        obj.setState(AssessState.SchoolUnpassed);
      }
    } else if (AssessState.SchoolApproved.equals(obj.getState()) && obj instanceof GoodProject) {
      GoodProject good = (GoodProject) obj;
      if (good.getFinalSummary().getAttachment() != null
          && AssessState.DepartApproved.equals(good.getFinalSummary().getState())) {
        if (getBool("passed")) {
          good.getFinalSummary().setState(AssessState.SchoolApproved);
        } else {
          good.getFinalSummary().setState(AssessState.SchoolUnpassed);
        }
      }
    }
  }

  protected boolean editable(AbstractWenmingObject obj) {
    if(AssessState.DepartApproved.equals(obj.getState())){
      return true;
    }else if (obj instanceof GoodProject){
      GoodProject good = (GoodProject) obj;
      return good.getFinalSummary() != null && AssessState.DepartApproved.equals(good.getFinalSummary().getState());
    }
    return false;
  }
}
