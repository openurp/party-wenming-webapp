package org.openurp.webapp.apps.party.wenming.service;

import org.openurp.webapp.apps.party.wenming.model.WenmingSession;
import org.openurp.webapp.apps.party.wenming.model.WenmingType;

public interface WenMingSessionService {

  WenmingSession getSession(WenmingType wenmingType);

}
