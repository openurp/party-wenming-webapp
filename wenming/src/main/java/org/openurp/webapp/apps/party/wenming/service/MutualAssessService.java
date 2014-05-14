package org.openurp.webapp.apps.party.wenming.service;

import java.util.List;

import org.openurp.kernel.base.unit.model.Department;
import org.openurp.webapp.apps.party.wenming.model.AssessItem;
import org.openurp.webapp.apps.party.wenming.model.AssessSchema;
import org.openurp.webapp.apps.party.wenming.model.AssessSession;

public interface MutualAssessService {

  /**
   * 教学部门与评职能部门互评
   * 
   * @param assessSession
   * @param department 
   * @return
   */
  List<AssessSchema> getSchemas(AssessSession assessSession, Department department);

  List<AssessItem> findAssessItem(AssessSchema schema);

}
