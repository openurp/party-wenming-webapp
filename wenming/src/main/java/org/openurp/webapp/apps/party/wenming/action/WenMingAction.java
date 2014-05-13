package org.openurp.webapp.apps.party.wenming.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.beangle.ems.web.action.SecurityActionSupport;
import org.openurp.kernel.base.unit.model.Department;
import org.openurp.kernel.base.unit.model.UrpUserBean;
import org.openurp.webapp.apps.party.wenming.model.AbstractAssessInfo;
import org.openurp.webapp.apps.party.wenming.model.AbstractAssessItemInfo;
import org.openurp.webapp.apps.party.wenming.model.AssessSession;
import org.openurp.webapp.apps.party.wenming.model.AssessState;
import org.openurp.webapp.apps.party.wenming.model.MutualAssess;
import org.openurp.webapp.apps.party.wenming.model.MutualAssessItem;
import org.openurp.webapp.apps.party.wenming.service.WenMingService;

public class WenMingAction extends SecurityActionSupport {
	
	protected WenMingService wenMingService;
	
	public void setWenMingService(WenMingService wenMingService) {
		this.wenMingService = wenMingService;
	}
	
	protected void putSchemas(){
		put("schemas", wenMingService.findSchema());
	}

	protected Department getDepartment() {
    return getUrpUser().getDepartment();
  }

	protected UrpUserBean getUrpUser() {
    return entityDao.get(UrpUserBean.class, getUserId());
  }

	protected boolean editable(AssessState state) {
    if(state == null || AssessState.Draft.equals(state) || AssessState.DepartUnpassed.equals(state) || AssessState.SchoolUnpassed.equals(state)){
      return true;
    }
    return false;
  }

	protected List<?> getAll(Class clazz, String index) {
    String[] indexes = getAll(index, String.class);
    List<Object> list = new ArrayList<Object>();
    for (String i : indexes) {
      Object ma;
      ma = populate(clazz, i);
      list.add(ma);
    }
    return list;
  }

	protected List<?> getAll() {
    try {
      return getAll(Class.forName(getEntityName()), "index");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }
  
	protected <T extends AbstractAssessInfo,I extends AbstractAssessItemInfo> List<T> getAllAssess(Class<T> assessClass, Class<I> itemClass) {
    UrpUserBean assessBy = getUrpUser();
    @SuppressWarnings("unchecked")
    List<T> malist = (List<T>) getAll();
    AssessSession session = wenMingService.getAssessSessionByAssessTime();
    boolean save = getBool("save");
    for (T assess : malist) {
      assess.setAssessBy(assessBy);
      @SuppressWarnings("unchecked")
      List<I> items = (List<I>) getAll(itemClass, "item"
          + assess.getDepartment().getId());

      assess.getItems().clear();
      assess.getItems().addAll((List) items);
      
      assess.setSession(session);
      assess.setAssessAt(new Date());
      float totalScore = 0;
      for (I item : items) {
        item.setAssess(assess);
        totalScore += item.getScore();
      }
      assess.setTotalScore(totalScore);
      if (save) {
        assess.setState(AssessState.Draft);
      } else {
        assess.setState(AssessState.Submit);
      }
    }
    return malist;
  }
}
