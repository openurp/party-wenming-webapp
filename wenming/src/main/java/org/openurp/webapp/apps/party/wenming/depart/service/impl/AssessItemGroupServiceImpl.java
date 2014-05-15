package org.openurp.webapp.apps.party.wenming.depart.service.impl;

import java.util.List;

import org.beangle.commons.collection.CollectUtils;
import org.beangle.commons.dao.impl.AbstractHierarchyService;
import org.beangle.commons.dao.query.builder.OqlBuilder;
import org.openurp.webapp.apps.party.wenming.depart.model.AssessItemGroup;
import org.openurp.webapp.apps.party.wenming.depart.service.AssessItemGroupService;

public class AssessItemGroupServiceImpl extends AbstractHierarchyService<AssessItemGroup> implements
		AssessItemGroupService {

	@Override
	public void move(AssessItemGroup menu, AssessItemGroup location, int indexno) {
		super.move((AssessItemGroup) menu, (AssessItemGroup) location, indexno);
	}

	@Override
	protected List<AssessItemGroup> getTopNodes(AssessItemGroup aig) {
		List<AssessItemGroup> topNodes = CollectUtils.newArrayList();
		for (AssessItemGroup m : aig.getSchema().getGroups())
			if (null == m.getParent())
				topNodes.add(m);
		System.out.println("topNodes.size() => " + topNodes.size());
		return topNodes;
	}

	@Override
	public List<AssessItemGroup> findBySchema(Integer schemaId) {
	  OqlBuilder<AssessItemGroup> query = OqlBuilder.from(AssessItemGroup.class, "o");
	  query.where("schema.id = :schemaid", schemaId);
	  query.orderBy("indexno");
		return entityDao.search(query);
	}

}
