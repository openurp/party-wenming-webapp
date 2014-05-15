package org.openurp.webapp.apps.party.wenming.depart.model;


/**
 * 每个自评指标对应的分数
 * 
 * @author chaostone
 */
public class SelfAssessItem extends AbstractAssessItemInfo {

  private static final long serialVersionUID = -1848383446708874473L;

  private SelfAssess assess;

  public SelfAssess getAssess() {
    return assess;
  }

  public void setAssess(AbstractAssessInfo assess) {
    this.assess = (SelfAssess) assess;
  }

}
