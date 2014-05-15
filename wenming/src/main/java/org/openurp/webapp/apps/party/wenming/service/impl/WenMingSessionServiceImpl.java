package org.openurp.webapp.apps.party.wenming.service.impl;

import java.util.Date;
import java.util.List;

import org.beangle.commons.dao.impl.BaseServiceImpl;
import org.beangle.commons.dao.query.builder.OqlBuilder;
import org.openurp.webapp.apps.party.wenming.model.WenmingSession;
import org.openurp.webapp.apps.party.wenming.model.WenmingType;
import org.openurp.webapp.apps.party.wenming.service.WenMingSessionService;

public class WenMingSessionServiceImpl extends BaseServiceImpl implements WenMingSessionService {

  @Override
  public WenmingSession getSession(WenmingType wenmingType) {
    OqlBuilder<WenmingSession> query = OqlBuilder.from(WenmingSession.class, "o");
    query.where("o.beginOn <= :now and o.endOn >= :now", new Date());
    query.join("o.types", "t");
    query.where("t = :type", wenmingType);
    List<WenmingSession> list = entityDao.search(query);
    return list.isEmpty() ? null : list.get(0);
  }

}
