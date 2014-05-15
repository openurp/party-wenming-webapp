package org.openurp.webapp.apps.party.wenming.depart.action;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.beangle.commons.bean.comparators.PropertyComparator;
import org.beangle.commons.collection.CollectUtils;
import org.beangle.commons.entity.Entity;
import org.beangle.struts2.convention.route.Action;
import org.openurp.kernel.base.unit.model.Department;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessBonusItem;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessItem;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessItemDepartment;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessItemGroup;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessSchema;
import org.openurp.webapp.apps.party.wenming.service.DepartmentService;

/**
 * 测评方案控制器
 * 
 * @author xinzhou
 **/

public class AssessSchemaAction extends WenMingAction {
  private DepartmentService departmentService;

  @Override
  protected String getEntityName() {
    return AssessSchema.class.getName();
  }

  @Override
  protected void editSetting(Entity<?> entity) {
    List<Department> departments = departmentService.getActives();
    AssessSchema schema = (AssessSchema) entity;
    put("allDepartments", CollectUtils.newArrayList(departments));
    departments.removeAll(schema.getDeparts());
    for(Department depart:departments){
      System.out.println(depart.getName());
    }
    put("departments", departments);
  }

  /**
   * 维护指标
   */
  public String item() {
    return redirect(new Action(AssessItemGroupAction.class, "search", "&assessItemGroup.schema.id="
        + getInt("assessSchema.id")), null);
  }

  /**
   * 加分
   */
  public String bonus() {
    return redirect(new Action(AssessBonusItemAction.class, "search", "&assessBonusItem.schema.id="
        + getInt("assessSchema.id")), null);
  }

  /**
   * 复制方案
   */
  public String copy() {
    Integer schemaId = getInt("assessSchema.id");
    AssessSchema schema = entityDao.get(AssessSchema.class, schemaId);
    AssessSchema newer = new AssessSchema();
    newer.setName(schema.getName() + "(复制)");
    newer.setUpdatedAt(new Date());
    newer.setCreatedAt(new Date());
    newer.setForTeaching(schema.isForTeaching());
    for (AssessBonusItem bonus : schema.getBonusItems()) {
      AssessBonusItem newerBonus = new AssessBonusItem();
      newerBonus.setBonusType(bonus.getBonusType());
      newerBonus.setContent(bonus.getContent());
      newerBonus.setMethod(bonus.getMethod());
      newerBonus.setName(bonus.getName());
      newerBonus.setSchema(newer);
      newer.getBonusItems().add(newerBonus);
    }
    Map<String, AssessItemGroup> groups = CollectUtils.newHashMap();
    List<AssessItemGroup> originGroups = CollectUtils.newArrayList(schema.getGroups());
    Collections.sort(originGroups, new PropertyComparator("indexno"));
    for (AssessItemGroup group : originGroups) {
      AssessItemGroup newerGroup = new AssessItemGroup();
      newerGroup.setIndexno(group.getIndexno());
      newer.getGroups().add(newerGroup);
      groups.put(group.getIndexno(), newerGroup);
      newerGroup.setName(group.getName());
      newerGroup.setSchema(newer);
      if (null != group.getParent()) {
        AssessItemGroup myParent = groups.get(group.getParent().getIndexno());
        newerGroup.setParent(myParent);
        myParent.getChildren().add(newerGroup);
      }
      for (AssessItem item : group.getItems()) {
        AssessItem newerItem = new AssessItem();
        newerGroup.getItems().add(newerItem);
        newerItem.setContent(item.getContent());
        newerItem.setOrderNumber(item.getOrderNumber());
        newerItem.setAssessType(item.getAssessType());
        newerItem.setScore(item.getScore());
        newerItem.setGroup(newerGroup);
        for (AssessItemDepartment idepart : item.getDeparts()) {
          AssessItemDepartment newIdpart = new AssessItemDepartment();
          newerItem.getDeparts().add(newIdpart);
          newIdpart.setDepartment(idepart.getDepartment());
          newIdpart.setItem(newerItem);
          newIdpart.setScore(item.getScore());
        }
      }
    }
    newer.getDeparts().addAll(schema.getDeparts());
    saveOrUpdate(newer);
    return redirect("search", "info.save.success");
  }

  @Override
  protected String saveAndForward(Entity<?> entity) {
    AssessSchema schema = (AssessSchema) entity;
    Integer[] departIds = getAll("departId", Integer.class);
    schema.getDeparts().clear();
    schema.getDeparts().addAll(entityDao.get(Department.class, departIds));
    schema.setUpdatedAt(new Date());
    if (schema.getCreatedAt() == null) {
      schema.setCreatedAt(new Date());
    }
    return super.saveAndForward(schema);
  }

  public void setDepartmentService(DepartmentService departmentService) {
    this.departmentService = departmentService;
  }

}
