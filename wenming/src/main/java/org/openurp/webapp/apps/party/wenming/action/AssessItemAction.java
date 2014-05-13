package org.openurp.webapp.apps.party.wenming.action;

import java.util.List;

import org.beangle.commons.dao.query.builder.OqlBuilder;
import org.beangle.commons.entity.Entity;
import org.openurp.webapp.apps.party.wenming.model.AssessItem;
import org.openurp.webapp.apps.party.wenming.model.AssessItemDepartment;
import org.openurp.webapp.apps.party.wenming.model.AssessItemGroup;
import org.openurp.webapp.apps.party.wenming.service.DepartmentService;

/**
 * 评价指标管理
 * 
 * @author chii
 */
public class AssessItemAction extends WenMingAction {

  private DepartmentService departmentService;

  @Override
  protected String getEntityName() {
    return AssessItem.class.getName();
  }

  @Override
  protected void indexSetting() {
    put("assessItemGroupId", getInt("assessItemGroupId"));
    super.indexSetting();
  }

  @Override
  protected void editSetting(Entity<?> entity) {
    AssessItemGroup group = entityDao.get(AssessItemGroup.class.getName(), getInt("assessItem.group.id"));
    put("departments", departmentService.getActives(false));
    put("group", group);
    super.editSetting(entity);
  }

  @Override
  protected String saveAndForward(Entity<?> entity) {
    AssessItem item = (AssessItem) entity;
    item.getDeparts().clear();
    String[] indexes = getAll("index", String.class);
    if (indexes != null) {
      for (String shortName : indexes) {
        AssessItemDepartment aid = populate(AssessItemDepartment.class, shortName);
        aid.setItem(item);
        item.getDeparts().add(aid);
      }
    }
    setOrderNumber(item);
    return super.saveAndForward(entity);
  }

  private void setOrderNumber(AssessItem item) {
    Integer orderNumber = getInt("orderNumber");
    if (item.getOrderNumber() != null && orderNumber.equals(item.getOrderNumber())) { return; }
    item.setOrderNumber(orderNumber);
    Integer beginIndex = Math.min(orderNumber, item.getOrderNumber());
    OqlBuilder<AssessItem> query = OqlBuilder.from(AssessItem.class, "o");
    query.where("o.group.id = :groupid", item.getGroup().getId());
    if (item.getId() != null) {
      query.where("o.id <> :id", item.getId());
    }
    query.where("o.orderNumber >= :orderNumber", beginIndex);
    query.orderBy("orderNumber");
    List<AssessItem> list = entityDao.search(query);
    Integer index = beginIndex;
    for (AssessItem i : list) {
      if (index.equals(orderNumber)) {
        index++;
      }
      i.setOrderNumber(index++);
    }
    entityDao.saveOrUpdate(list);
  }

  public void setDepartmentService(DepartmentService departmentService) {
    this.departmentService = departmentService;
  }

}
