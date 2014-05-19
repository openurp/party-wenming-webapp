package org.openurp.webapp.apps.party.wenming.depart.action;

import java.util.ArrayList;
import java.util.List;

import org.beangle.commons.entity.util.EntityUtils;
import org.beangle.commons.lang.Strings;
import org.beangle.ems.web.action.SecurityActionSupport;
import org.openurp.kernel.base.unit.model.Department;
import org.openurp.kernel.base.unit.model.UrpUserBean;
import org.openurp.webapp.apps.party.wenming.action.AttachmentHelper;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessState;
import org.openurp.webapp.apps.party.wenming.depart.service.WenMingService;
import org.openurp.webapp.apps.party.wenming.model.AttachmentObject;

public class WenMingAction extends SecurityActionSupport {

  protected WenMingService wenMingService;

  protected AttachmentHelper attachmentHelper;

  public void setWenMingService(WenMingService wenMingService) {
    this.wenMingService = wenMingService;
  }

  protected void putSchemas() {
    put("schemas", wenMingService.findSchema());
  }

  protected Department getDepartment() {
    return getUrpUser().getDepartment();
  }

  protected UrpUserBean getUrpUser() {
    return entityDao.get(UrpUserBean.class, getUserId());
  }

  protected boolean saveable(AssessState state) {
    if (AssessState.Draft.equals(state) || AssessState.Submit.equals(state)) { return true; }
    return false;
  }

  protected boolean editable(AssessState state) {
    if (state == null || AssessState.Draft.equals(state) || AssessState.DepartUnpassed.equals(state)
        || AssessState.SchoolUnpassed.equals(state)) { return true; }
    return false;
  }

  protected <T> List<?> getAll(Class<T> clazz, String index) {
    String[] indexes = getAll(index, String.class);
    List<Object> list = new ArrayList<Object>();
    if (indexes != null) {
      for (String i : indexes) {
        Object ma;
        ma = populateEntity(clazz, i);
        list.add(ma);
      }
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

  protected void setAttachment(AttachmentObject obj) {
    attachmentHelper.setAttachment(obj);
  }

  public void setAttachmentHelper(AttachmentHelper attachmentHelper) {
    this.attachmentHelper = attachmentHelper;
  }
  
  protected String getShortName(Class<?> cls) {
    String name = cls.getName();
    if (Strings.isNotEmpty(name)) return EntityUtils.getCommandName(name);
    else return null;
  }

}
