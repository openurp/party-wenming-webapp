package org.openurp.webapp.apps.party.wenming.action;

import java.util.Date;
import java.util.List;

import org.beangle.commons.collection.CollectUtils;
import org.beangle.commons.entity.Entity;
import org.openurp.kernel.base.unit.model.Department;
import org.openurp.webapp.apps.party.wenming.model.AssessSchema;
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
    put("departments", departments);
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
