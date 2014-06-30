package org.openurp.webapp.apps.party.wenming.depart.action;

import java.util.List;

import org.beangle.commons.collection.CollectUtils;
import org.beangle.commons.dao.query.builder.OqlBuilder;
import org.beangle.security.blueprint.SecurityUtils;
import org.openurp.kernel.base.unit.model.UrpUserBean;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessItem;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessItemDepartment;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessItemGroup;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessSchema;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessSession;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessType;
import org.openurp.webapp.apps.party.wenming.depart.model.FuncDepartAssess;
import org.openurp.webapp.apps.party.wenming.depart.model.FuncDepartAssessItem;
import org.openurp.webapp.apps.party.wenming.depart.service.QueryInvoker;

/**
 * 职能部门评院系
 * 
 * @author chaostone
 */
public class FuncDepartAssessAction extends AssessAction<FuncDepartAssess, FuncDepartAssessItem> {

  @Override
  protected List<AssessItem> findAssessItem(AssessSession assessSession, AssessSchema schema) {
    return wenMingService.findAssessItem(schema, new QueryInvoker() {
      @Override
      public void doth(OqlBuilder<?> query) {
        query.join("o.departs", "d");
        query.where("d.department = :department", getDepartment());
        query.where("o.assessType = :assessType", AssessType.FUNC_DEPART);
      }
    });
  }

  protected void indexSetting(AssessSession assessSession) {
    //仅仅显示需要该部门进行职能部门测评的方案
    List<AssessSchema> schemas = CollectUtils.newArrayList();
    UrpUserBean user = entityDao.get(UrpUserBean.class, SecurityUtils.getUserId());
    for (AssessSchema schema : assessSession.getSchemas()) {
      schema_label: for (AssessItemGroup group : schema.getGroups()) {
        for (AssessItem item : group.getItems()) {
          for (AssessItemDepartment id : item.getDeparts()) {
            if (id.getDepartment().equals(user.getDepartment())) {
              schemas.add(schema);
              break schema_label;
            }
          }
        }
      }
    }
    put("schemas", schemas);
  }

}
