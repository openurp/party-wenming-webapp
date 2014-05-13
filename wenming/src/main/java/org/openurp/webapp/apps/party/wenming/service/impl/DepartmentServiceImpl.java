package org.openurp.webapp.apps.party.wenming.service.impl;

import java.util.Date;
import java.util.List;

import org.beangle.commons.dao.impl.BaseServiceImpl;
import org.beangle.commons.dao.query.builder.OqlBuilder;
import org.openurp.kernel.base.unit.model.Department;
import org.openurp.webapp.apps.party.wenming.service.DepartmentService;

public class DepartmentServiceImpl extends BaseServiceImpl implements DepartmentService {

  @Override
  public List<Department> getActives() {
    OqlBuilder<Department> builder = OqlBuilder.from(Department.class, "depart");
    builder.where("depart.endOn is null or depart.endOn>=:now", new Date(System.currentTimeMillis()));
    builder.orderBy("depart.code");
    return entityDao.search(builder);
  }

  @Override
  public List<Department> getActives(boolean isTeaching) {
    OqlBuilder<Department> builder = OqlBuilder.from(Department.class, "depart");
    builder.where("depart.endOn is null or depart.endOn>=:now", new Date(System.currentTimeMillis()));
    builder.orderBy("depart.code").where("depart.teaching=:teaching", isTeaching);
    return entityDao.search(builder);
  }

}
