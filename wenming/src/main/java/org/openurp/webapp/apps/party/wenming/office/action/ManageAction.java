package org.openurp.webapp.apps.party.wenming.office.action;


import org.beangle.commons.entity.Entity;
import org.beangle.ems.web.action.SecurityActionSupport;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessState;
import org.openurp.webapp.apps.party.wenming.office.model.GoodOffice;

public class ManageAction  extends SecurityActionSupport {
  
  @Override
  protected String getEntityName() {
    return GoodOffice.class.getName();
  }

  @Override
  public String edit() {
    Entity<?> entity = getEntity();
    GoodOffice goodOffice = (GoodOffice) entity;
    if (AssessState.DepartApproved.equals(goodOffice.getState())) {
      put(getShortName(), entity);
      editSetting(entity);
      return forward();
    } else {
      return redirect("search", "无法修改");
    }
  }
  
  @Override
  protected String saveAndForward(Entity<?> entity) {
    GoodOffice goodOffice = (GoodOffice) entity;
    if (AssessState.DepartApproved.equals(goodOffice.getState())) {
      setState(goodOffice);
      return super.saveAndForward(entity);
    } else {
      return redirect("search", "无法操作");
    }
  }

  
  protected void setState(GoodOffice goodOffice){
    if(getBool("passed")){
      goodOffice.setState(AssessState.SchoolApproved);
    }else {
      goodOffice.setState(AssessState.SchoolUnpassed);
    }
  }
}
