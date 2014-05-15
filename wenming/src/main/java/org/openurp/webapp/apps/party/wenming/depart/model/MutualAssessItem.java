package org.openurp.webapp.apps.party.wenming.depart.model;



/**
 * 单位互评指标信息
 * 
 * @author chaostone
 */
public class MutualAssessItem extends AbstractAssessItemInfo {

  private static final long serialVersionUID = -5188633050091433255L;

  private MutualAssess assess;

  public MutualAssess getAssess() {
    return assess;
  }

  public void setAssess(AbstractAssessInfo assess) {
    this.assess = (MutualAssess) assess;
  }

}
