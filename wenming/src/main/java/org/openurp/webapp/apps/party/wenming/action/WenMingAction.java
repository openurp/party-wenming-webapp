package org.openurp.webapp.apps.party.wenming.action;

import org.beangle.commons.dao.query.builder.OqlBuilder;
import org.beangle.ems.web.action.SecurityActionSupport;

public class WenMingAction extends SecurityActionSupport {
	
	public void addorder(OqlBuilder<?> query) {
		if (get("orderBy") != null && !get("orderBy").equals("")) {
			query.orderBy(get("orderBy"));
		}
		query.limit(getPageLimit());
	}
}
