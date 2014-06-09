package org.openurp.webapp.apps.party.wenming.project.model;

import org.openurp.webapp.apps.party.wenming.model.AbstractWenmingObject;
import org.openurp.webapp.apps.party.wenming.model.AbstractWenmingVote;

public class GoodProjectVote extends AbstractWenmingVote{
  
  private GoodProject goodProject;

  public GoodProject getGoodProject() {
    return goodProject;
  }

  public void setGoodProject(GoodProject goodProject) {
    this.goodProject = goodProject;
  }

  @Override
  public void setGoodObject(AbstractWenmingObject obj) {
    goodProject = (GoodProject) obj;
  }

}
