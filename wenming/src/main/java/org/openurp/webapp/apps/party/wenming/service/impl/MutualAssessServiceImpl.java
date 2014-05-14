package org.openurp.webapp.apps.party.wenming.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.beangle.commons.dao.query.builder.OqlBuilder;
import org.openurp.kernel.base.unit.model.Department;
import org.openurp.webapp.apps.party.wenming.model.AssessItem;
import org.openurp.webapp.apps.party.wenming.model.AssessSchema;
import org.openurp.webapp.apps.party.wenming.model.AssessSession;
import org.openurp.webapp.apps.party.wenming.model.AssessType;
import org.openurp.webapp.apps.party.wenming.service.MutualAssessService;
import org.openurp.webapp.apps.party.wenming.service.QueryInvoker;
import org.openurp.webapp.apps.party.wenming.service.WenMingService;

public class MutualAssessServiceImpl implements MutualAssessService {
  
  private WenMingService wenMingService;
  
  public void setWenMingService(WenMingService wenMingService) {
    this.wenMingService = wenMingService;
  }

  @Override
  public List<AssessSchema> getSchemas(AssessSession assessSession, Department department) {
    List<AssessSchema> list = new ArrayList<AssessSchema>();
    if (assessSession == null || department == null) {
      return list;
    }
    if (assessSession != null) {
      for (AssessSchema schema : assessSession.getSchemas()) {
        if (department.isTeaching() != schema.isForTeaching()) {
          list.add(schema);
        }
      }
    }
    return list;
  }

  @Override
  public List<AssessItem> findAssessItem(AssessSchema schema) {
    return wenMingService.findAssessItem(schema, new QueryInvoker() {
      @Override
      public void doth(OqlBuilder<?> query) {
        query.where("o.assessType = :assessType", AssessType.MUTUAL);
      }
    });
  }
}
