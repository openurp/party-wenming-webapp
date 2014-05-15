package org.openurp.webapp.apps.party.wenming.depart.service;

import org.beangle.commons.dao.query.builder.OqlBuilder;

public interface QueryInvoker {
  void doth(OqlBuilder<?> query);
}
