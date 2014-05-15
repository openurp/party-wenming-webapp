package org.openurp.webapp.apps.party.wenming.depart.model;

import java.util.List;

import org.beangle.commons.collection.CollectUtils;
import org.beangle.commons.entity.pojo.NumberIdHierarchyObject;

/**
 * 测评指标分类
 * 
 * @author chaostone
 * 
 */
public class AssessItemGroup extends NumberIdHierarchyObject<AssessItemGroup, Integer> {
  private static final long serialVersionUID = 1L;

  private String name;

	private AssessSchema schema;

	private List<AssessItem> items = CollectUtils.newArrayList();

	public List<AssessItem> getItems() {
		return items;
	}

	public void setItems(List<AssessItem> items) {
		this.items = items;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AssessSchema getSchema() {
		return schema;
	}

	public void setSchema(AssessSchema schema) {
		this.schema = schema;
	}

}
