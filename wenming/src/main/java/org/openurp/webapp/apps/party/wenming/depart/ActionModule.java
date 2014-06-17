package org.openurp.webapp.apps.party.wenming.depart;

import org.beangle.commons.inject.bind.AbstractBindModule;
import org.openurp.webapp.apps.party.wenming.depart.action.ApplyAction;
import org.openurp.webapp.apps.party.wenming.depart.action.ApplyAuditAction;
import org.openurp.webapp.apps.party.wenming.depart.action.ApplyManageAction;
import org.openurp.webapp.apps.party.wenming.depart.action.AssessBonusAction;
import org.openurp.webapp.apps.party.wenming.depart.action.AssessBonusItemAction;
import org.openurp.webapp.apps.party.wenming.depart.action.AssessBonusTypeAction;
import org.openurp.webapp.apps.party.wenming.depart.action.AssessItemAction;
import org.openurp.webapp.apps.party.wenming.depart.action.AssessItemGroupAction;
import org.openurp.webapp.apps.party.wenming.depart.action.AssessSchemaAction;
import org.openurp.webapp.apps.party.wenming.depart.action.AssessSessionAction;
import org.openurp.webapp.apps.party.wenming.depart.action.AssessStatAction;
import org.openurp.webapp.apps.party.wenming.depart.action.AssessSummaryAction;
import org.openurp.webapp.apps.party.wenming.depart.action.FuncDepartAssessAction;
import org.openurp.webapp.apps.party.wenming.depart.action.FuncDepartAssessAuditAction;
import org.openurp.webapp.apps.party.wenming.depart.action.MutualAssessAction;
import org.openurp.webapp.apps.party.wenming.depart.action.MutualAssessAuditAction;
import org.openurp.webapp.apps.party.wenming.depart.action.SelfAssessAction;
import org.openurp.webapp.apps.party.wenming.depart.action.SelfAssessAuditAction;
import org.openurp.webapp.apps.party.wenming.depart.action.SupervisorAction;
import org.openurp.webapp.apps.party.wenming.depart.action.SupervisorAssessAction;
import org.openurp.webapp.apps.party.wenming.depart.action.VoteAction;

public class ActionModule extends AbstractBindModule {

  @Override
  protected void doBinding() {
    bind(AssessSessionAction.class, AssessItemGroupAction.class, AssessItemAction.class);
    bind(AssessSchemaAction.class);
    bind(SupervisorAction.class, SupervisorAssessAction.class, SelfAssessAction.class,
        SelfAssessAuditAction.class, MutualAssessAction.class, MutualAssessAuditAction.class,
        ApplyAction.class, ApplyAuditAction.class, ApplyManageAction.class, AssessStatAction.class);
    bind(AssessBonusTypeAction.class);
    bind(AssessBonusItemAction.class);
    bind(AssessBonusAction.class);
    bind(SupervisorAction.class);
    bind(SupervisorAssessAction.class);
    bind(FuncDepartAssessAction.class);
    bind(FuncDepartAssessAuditAction.class);
    bind(VoteAction.class);
    bind(AssessSummaryAction.class);
  }
}
