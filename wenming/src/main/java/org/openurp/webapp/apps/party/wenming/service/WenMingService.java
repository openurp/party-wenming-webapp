package org.openurp.webapp.apps.party.wenming.service;

import java.util.List;

import org.openurp.kernel.base.unit.model.Department;
import org.openurp.webapp.apps.party.wenming.model.AssessSchema;
import org.openurp.webapp.apps.party.wenming.model.AssessSession;

public interface WenMingService {

	List<AssessSchema> findSchema();

	List<Department> findDepartment();

  AssessSession getAssessSession();

  AssessSchema getSchema(Department department);
  
  List<AssessSession> findSession();

}
