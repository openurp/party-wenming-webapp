package org.openurp.webapp.apps.party.wenming.service;

import org.beangle.commons.dao.query.builder.OqlBuilder;

public interface QueryInvoker {
  void doth(OqlBuilder<?> query);
}
