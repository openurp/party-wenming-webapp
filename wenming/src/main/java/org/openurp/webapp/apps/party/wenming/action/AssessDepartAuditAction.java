package org.openurp.webapp.apps.party.wenming.action;

import java.util.List;

import org.beangle.commons.dao.query.builder.OqlBuilder;
import org.openurp.webapp.apps.party.wenming.model.AbstractAssessInfo;
import org.openurp.webapp.apps.party.wenming.model.AbstractAssessItemInfo;
import org.openurp.webapp.apps.party.wenming.model.AssessSession;
import org.openurp.webapp.apps.party.wenming.model.AssessState;

@SuppressWarnings("rawtypes")
public abstract class AssessDepartAuditAction extends AssessAction{

  protected void indexSetting(AssessSession assessSession) {
    put("schemas", assessSession.getSchemas());
  }

  protected List<AbstractAssessInfo> findAssess(Integer assessSessionId, Integer schemaId) {
    @SuppressWarnings("unchecked")
    OqlBuilder<AbstractAssessInfo> query = getQueryBuilder(assessSessionId, schemaId);
    query.where("o.assessBy.id = :assessById", getLong("assessBy.id"));
    @SuppressWarnings("unchecked")
    List<AbstractAssessInfo> malist = search(query);
    return malist;
  }

  @Override
  protected boolean editable(AssessState state) {
    return AssessState.Submit.equals(state);
  }

  protected boolean editCreateAble() {
    return false;
  }
  
  @Override
  protected void setState(AbstractAssessInfo assess) {
    boolean save = getBool("save");
    if(!save){
      boolean passed = getBool("passed");
      if(passed){
        assess.setState(AssessState.DepartApproved);
      }else{
        assess.setState(AssessState.DepartUnpassed);
      }
    }
  }
  
}
