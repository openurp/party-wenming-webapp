package org.openurp.webapp.apps.party.wenming.model;

/**
 * 每个自评指标对应的分数
 * 
 * @author chaostone
 */
public class FuncDepartAssessItem extends AbstractAssessItemInfo {

  private static final long serialVersionUID = -1848383446708874473L;

  private FuncDepartAssess assess;

  public FuncDepartAssess getAssess() {
    return assess;
  }

  public void setAssess(AbstractAssessInfo assess) {
    this.assess = (FuncDepartAssess) assess;
  }
}
