package org.openurp.webapp.apps.party.wenming.model;

import org.beangle.commons.entity.pojo.IntegerIdObject;
import org.openurp.kernel.base.unit.model.Department;

/**
 * 评价指标
 * 
 * @author chaostone
 * 
 */
public class AssessItemDepartment extends IntegerIdObject {
	/** 评价指标 **/
	private AssessItem item;
	/** 评分单位 **/
	private Department department;
	/** 分数 **/
	private float score;
	
	public AssessItem getItem() {
		return item;
	}
	public void setItem(AssessItem item) {
		this.item = item;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}

}
