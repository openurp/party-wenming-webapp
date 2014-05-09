package org.openurp.webapp.apps.party.wenming.model;

import java.util.List;

import org.beangle.commons.collection.CollectUtils;
import org.beangle.commons.entity.pojo.NumberIdHierarchyObject;


/**
 * 评价指标分类
 * 
 * @author chaostone
 * 
 */
public class AssessItemGroup extends
		NumberIdHierarchyObject<AssessItemGroup, Integer> {
	private static final long serialVersionUID = -335322759192447543L;

	private List<AssessItem> items= CollectUtils.newArrayList();

	public List<AssessItem> getItems() {
		return items;
	}

	public void setItems(List<AssessItem> items) {
		this.items = items;
	}
	
}
