package org.openurp.webapp.apps.party.wenming.project.model;

import org.openurp.webapp.apps.party.wenming.model.AbstractWenmingSummaryObject;

public class GoodProjectMiddleSummary extends AbstractWenmingSummaryObject<GoodProject>{
  
  private GoodProject good;

  public GoodProject getGood() {
    return good;
  }

  public void setGood(GoodProject good) {
    this.good = good;
  }
}
