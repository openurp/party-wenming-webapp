package org.openurp.webapp.apps.party.wenming.project.model;

import org.openurp.webapp.apps.party.wenming.model.AbstractWenmingFinalSummaryObject;

public class GoodProjectFinalSummary extends AbstractWenmingFinalSummaryObject<GoodProject>{
  private GoodProject good;

  public GoodProject getGood() {
    return good;
  }

  public void setGood(GoodProject good) {
    this.good = good;
  }
}
