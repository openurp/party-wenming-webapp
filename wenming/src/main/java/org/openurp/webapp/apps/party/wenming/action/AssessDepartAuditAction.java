package org.openurp.webapp.apps.party.wenming.action;

import java.util.List;

import org.beangle.commons.dao.query.builder.OqlBuilder;
import org.openurp.webapp.apps.party.wenming.model.AbstractAssessInfo;
import org.openurp.webapp.apps.party.wenming.model.AbstractAssessItemInfo;
import org.openurp.webapp.apps.party.wenming.model.AssessItem;
import org.openurp.webapp.apps.party.wenming.model.AssessSchema;
import org.openurp.webapp.apps.party.wenming.model.AssessSession;
import org.openurp.webapp.apps.party.wenming.model.AssessState;

public abstract class AssessDepartAuditAction <T extends AbstractAssessInfo, I extends AbstractAssessItemInfo> extends AssessAction<T, I>{
  
  protected List<AssessItem> findAssessItem(AssessSession assessSession, AssessSchema schema){
    return null;
  }

  protected void indexSetting(AssessSession assessSession) {
    put("schemas", assessSession.getSchemas());
  }

  protected List<AbstractAssessInfo> findAssess(Integer assessSessionId, Integer schemaId) {
    OqlBuilder<AbstractAssessInfo> query = getQueryBuilder(assessSessionId, schemaId);
    query.where("o.assessBy.id = :assessById", getLong("assessBy.id"));
    @SuppressWarnings("unchecked")
    List<AbstractAssessInfo> malist = search(query);
    return malist;
  }
  
  @Override
  public String search() {
    OqlBuilder<T> query = OqlBuilder.from(getAssessClass(), "o");
    boolean forAudit = getBool("forAudit");
    if(forAudit){
      put("forAudit", forAudit);
      query.where("o.state = :state", AssessState.Submit);
    }else{
      query.where("o.state <> :state", AssessState.Submit);
    }
    String groupBy = "o.session.id, o.session.name, o.schema.id, o.schema.name, o.assessBy.id, o.assessBy.name, o.assessBy.fullname, o.assessAt, o.state";
    query.groupBy(groupBy);
    query.select(groupBy);
    put("assessList", search(query));
    return forward();
  }

  @Override
  protected String redirectSave(AbstractAssessInfo assess) {
    return redirect("search", "操作成功", "forAudit=1");
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
