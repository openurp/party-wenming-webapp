package org.openurp.webapp.apps.party.wenming.model;

public class SupervisorAssessItem extends AbstractAssessItemInfo {

  private static final long serialVersionUID = 2538701026363312722L;
  
  private SupervisorAssess assess;

  public SupervisorAssess getAssess() {
    return assess;
  }

  public void setAssess(AbstractAssessInfo assess) {
    this.assess = (SupervisorAssess) assess;
  }

}
