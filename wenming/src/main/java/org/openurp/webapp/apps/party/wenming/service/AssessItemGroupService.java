package org.openurp.webapp.apps.party.wenming.service;

import java.util.List;

import org.openurp.webapp.apps.party.wenming.model.AssessItemGroup;


public interface AssessItemGroupService {

	void move(AssessItemGroup menu, AssessItemGroup location, int indexno);

	List<AssessItemGroup> findBySchema(Integer schemaId);
}
