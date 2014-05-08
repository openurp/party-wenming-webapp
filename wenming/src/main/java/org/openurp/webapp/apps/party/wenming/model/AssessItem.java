package org.openurp.webapp.apps.party.wenming.model;

import java.util.List;

import org.beangle.commons.collection.CollectUtils;
import org.beangle.commons.entity.pojo.IntegerIdObject;
import org.openurp.kernel.base.unit.model.Department;

/**
 * 评价指标
 * 
 * @author chaostone
 * 
 */
public class AssessItem extends IntegerIdObject {
	private static final long serialVersionUID = -2793874216028524341L;
	private AssessItemGroup category;
	/** 指标内容 **/
	private String content;
	/** 指标分值 **/
	private float score;

	private List<Department> departs = CollectUtils.newArrayList();

	public AssessItemGroup getCategory() {
		return category;
	}

	public void setCategory(AssessItemGroup category) {
		this.category = category;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public List<Department> getDeparts() {
		return departs;
	}

	public void setDeparts(List<Department> departs) {
		this.departs = departs;
	}

}
