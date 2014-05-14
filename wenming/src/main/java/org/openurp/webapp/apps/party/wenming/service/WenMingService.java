package org.openurp.webapp.apps.party.wenming.service;

import java.util.List;

import org.openurp.kernel.base.unit.model.Department;
import org.openurp.webapp.apps.party.wenming.model.AssessItem;
import org.openurp.webapp.apps.party.wenming.model.AssessSchema;
import org.openurp.webapp.apps.party.wenming.model.AssessSession;

public interface WenMingService {

  List<AssessSchema> findSchema();

  AssessSession getAssessSessionByAssessTime();

  AssessSchema getSchema(AssessSession session, Department department);

  List<AssessSession> findSessions(Department department);

  List<AssessSession> findSessions();

  List<AssessItem> findAssessItemBySupervisor(AssessSchema schema);
  
  List<AssessItem> findAssessItem(AssessSchema schema, QueryInvoker invoker);
  
  List<AssessItem> findAssessItem(AssessSchema schema);
  
  List<Department> findDepartment();



}
