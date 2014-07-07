package org.openurp.webapp.apps.party.wenming.project.action;

import java.util.List;

import org.openurp.webapp.apps.party.wenming.action.WenmingProjectVoteAction;
import org.openurp.webapp.apps.party.wenming.model.AbstractWenmingObject;
import org.openurp.webapp.apps.party.wenming.model.AbstractWenmingVote;
import org.openurp.webapp.apps.party.wenming.model.WenmingType;
import org.openurp.webapp.apps.party.wenming.model.WenmingVoteSession;
import org.openurp.webapp.apps.party.wenming.project.model.GoodProject;
import org.openurp.webapp.apps.party.wenming.project.model.GoodProjectVote;

public class VoteAction extends WenmingProjectVoteAction{
  
  protected WenmingType getWenmingType(){
    return WenmingType.Project;
  }

  @Override
  protected Class getWenmingObjectClass() {
    return GoodProject.class;
  }

  @Override
  protected <T extends AbstractWenmingVote> Class<T> getWenmingObjectVoteClass() {
    return (Class<T>) GoodProjectVote.class;
  }

  @Override
  protected Object getLimitNum(WenmingVoteSession wenmingSession) {
    return wenmingSession.getLimit();
  }

  @Override
  protected List findAbstractWenmingObject(WenmingVoteSession session) {
    return session.getProjects();
  }

  
}
