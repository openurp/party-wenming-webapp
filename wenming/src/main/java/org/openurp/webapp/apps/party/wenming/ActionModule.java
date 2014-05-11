package org.openurp.webapp.apps.party.wenming;

import org.beangle.commons.inject.bind.AbstractBindModule;
import org.openurp.webapp.apps.party.wenming.action.ApplyAction;
import org.openurp.webapp.apps.party.wenming.action.ApplyAuditAction;
import org.openurp.webapp.apps.party.wenming.action.ApplyManageAction;
import org.openurp.webapp.apps.party.wenming.action.AssessBonusTypeAction;
import org.openurp.webapp.apps.party.wenming.action.AssessItemGroupAction;
import org.openurp.webapp.apps.party.wenming.action.AssessSchemaAction;
import org.openurp.webapp.apps.party.wenming.action.AssessSessionAction;
import org.openurp.webapp.apps.party.wenming.action.AssessStatAction;
import org.openurp.webapp.apps.party.wenming.action.MutualAssessAction;
import org.openurp.webapp.apps.party.wenming.action.MutualAssessAuditAction;
import org.openurp.webapp.apps.party.wenming.action.SelfAssessAction;
import org.openurp.webapp.apps.party.wenming.action.SelfAssessAuditAction;
import org.openurp.webapp.apps.party.wenming.action.SupervisorAction;
import org.openurp.webapp.apps.party.wenming.action.SupervisorAssessAction;

public class ActionModule extends AbstractBindModule {

  @Override
  protected void doBinding() {
    bind(AssessSessionAction.class, AssessItemGroupAction.class);
    bind(AssessSchemaAction.class);
    bind(SupervisorAction.class, SupervisorAssessAction.class, SelfAssessAction.class,
        SelfAssessAuditAction.class, MutualAssessAction.class, MutualAssessAuditAction.class,
        ApplyAction.class, ApplyAuditAction.class, ApplyManageAction.class, AssessStatAction.class);
    bind(AssessBonusTypeAction.class);
  }
}
