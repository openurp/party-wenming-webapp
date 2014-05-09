package org.openurp.webapp.apps.party.wenming.model;

import java.util.List;

import org.beangle.commons.collection.CollectUtils;
import org.beangle.commons.entity.pojo.IntegerIdObject;

/**
 * 评价指标
 * 
 * @author chaostone
 * 
 */
public class AssessItem extends IntegerIdObject {
	/** 评价指标分类 **/
	private AssessItemGroup group;
	/** 指标内容 **/
	private String content;
	/** 评分单位 **/
	private List<AssessItemDepartment> departs = CollectUtils.newArrayList();

	public AssessItemGroup getGroup() {
		return group;
	}

	public void setGroup(AssessItemGroup category) {
		this.group = category;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<AssessItemDepartment> getDeparts() {
		return departs;
	}

	public void setDeparts(List<AssessItemDepartment> departs) {
		this.departs = departs;
	}

}
