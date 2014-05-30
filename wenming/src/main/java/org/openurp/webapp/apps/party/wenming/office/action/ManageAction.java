package org.openurp.webapp.apps.party.wenming.office.action;

import org.beangle.ems.web.action.SecurityActionSupport;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessState;
import org.openurp.webapp.apps.party.wenming.office.model.GoodOffice;

public class ManageAction  extends SecurityActionSupport {
  
  @Override
  protected String getEntityName() {
    return GoodOffice.class.getName();
  }

  protected void setState(GoodOffice gd){
    if(getBool("pass")){
      gd.setState(AssessState.SchoolApproved);
    }else {
      gd.setState(AssessState.SchoolUnpassed);
    }
  }

}
