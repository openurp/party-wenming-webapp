package org.openurp.webapp.apps.party.wenming.service;

import java.util.List;

import org.openurp.kernel.base.unit.model.Department;

public interface DepartmentService {

  /**
   * 查询当前活跃的部门
   * 
   * @return
   */
  List<Department> getActives();

  List<Department> getActives(boolean isTeaching);
}
