package org.openurp.webapp.apps.party.wenming.action;

import java.util.List;

import org.beangle.security.blueprint.User;
import org.openurp.webapp.apps.party.wenming.depart.action.WenMingAction;
import org.openurp.webapp.apps.party.wenming.model.WenmingProjectVoter;

public class WenmingProjectVoterCommAction extends WenMingAction{
  public static final String WENMINGPROJECTVOTER_ID = "WENMINGPROJECTVOTER_ID";
  
  protected Integer getWenmingProjectVoterId() {
    Integer wenmingProjectVoterId = (Integer) getSession().get(WENMINGPROJECTVOTER_ID);
    try {
      if(wenmingProjectVoterId == null && getUserId() != null) {
        User user = getUrpUser();
        List<WenmingProjectVoter> list = entityDao.get(WenmingProjectVoter.class, "name",user.getName());
        if(!list.isEmpty()) {
          wenmingProjectVoterId = list.get(0).getId();
          getSession().put(WENMINGPROJECTVOTER_ID, wenmingProjectVoterId);
        }
      }
    } catch (Exception e) {
    }
    return wenmingProjectVoterId;
  }
  
  protected WenmingProjectVoter getWenmingProjectVoter() {
    return entityDao.get(WenmingProjectVoter.class, getWenmingProjectVoterId());
  }

}
