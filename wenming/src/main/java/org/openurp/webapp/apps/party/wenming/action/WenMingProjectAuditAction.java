package org.openurp.webapp.apps.party.wenming.action;

import java.util.Date;

import org.beangle.commons.collection.Order;
import org.beangle.commons.dao.query.builder.OqlBuilder;
import org.beangle.commons.entity.Entity;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessState;
import org.openurp.webapp.apps.party.wenming.model.AbstractWenmingObject;

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
    if (getBool("save")) {
      obj.setState(AssessState.DepartApproved);
    } else if (getBool("passed")) {
      obj.setState(AssessState.SchoolApproved);
    } else {
      obj.setState(AssessState.SchoolUnpassed);
    }
  }
  
  @Override
  protected boolean editable(AssessState state) {
    return AssessState.DepartApproved.equals(state);
  }

}
