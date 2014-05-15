package org.openurp.webapp.apps.party.wenming.depart.service;

import java.util.List;

import org.openurp.webapp.apps.party.wenming.depart.model.AssessItemGroup;


public interface AssessItemGroupService {

	void move(AssessItemGroup menu, AssessItemGroup location, int indexno);

	List<AssessItemGroup> findBySchema(Integer schemaId);
}
