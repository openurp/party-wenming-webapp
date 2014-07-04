package org.openurp.webapp.apps.party.wenming.depart.service;

import java.util.List;

import org.openurp.kernel.base.unit.model.Department;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessItem;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessSchema;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessSession;
import org.openurp.webapp.apps.party.wenming.depart.model.VoteSession;
import org.openurp.webapp.apps.party.wenming.model.WenmingSession;
import org.openurp.webapp.apps.party.wenming.model.WenmingVoteSession;

public interface WenMingService {

  List<AssessSchema> findSchema();

  AssessSession getAssessSessionByAssessTime();


  AssessSchema getSchema(AssessSession session, Department department);

  List<AssessSession> findSessions(Department department);

  List<VoteSession> findVoteSession(Department department);

  List<AssessSession> findAssessSessions();

  List<AssessItem> findAssessItemBySupervisor(AssessSchema schema);
  
  List<AssessItem> findAssessItem(AssessSchema schema, QueryInvoker invoker);
  
  List<AssessItem> findAssessItem(AssessSchema schema);
  
  List<Department> findDepartment();

  List<WenmingSession> findWenmingSessions();
  
  WenmingSession getWenmingSessionByAssessTime();
  
  VoteSession getVoteSession();

  List<VoteSession> findVoteSession();

  WenmingVoteSession getWenmingVoteSession();

  List<WenmingVoteSession> findWenmingVoteSession();

}
