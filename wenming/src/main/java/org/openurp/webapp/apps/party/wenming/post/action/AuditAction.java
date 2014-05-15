package org.openurp.webapp.apps.party.wenming.post.action;

import org.openurp.webapp.apps.party.wenming.action.WenMingProjectDepartAuditAction;
import org.openurp.webapp.apps.party.wenming.model.WenmingType;
import org.openurp.webapp.apps.party.wenming.post.model.GoodPost;

public class AuditAction extends WenMingProjectDepartAuditAction {

  @Override
  protected String getEntityName() {
    return GoodPost.class.getName();
  }

  @Override
  protected WenmingType getWenmingType() {
    return WenmingType.Post;
  }

}