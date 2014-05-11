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
  private static final long serialVersionUID = 9065392523456371155L;
  /** 评价指标分类 **/
	private AssessItemGroup group;
	/** 指标内容 **/
	private String content;
	/** 评分单位 **/
	private List<AssessItemDepartment> departs = CollectUtils.newArrayList();
	/** 是否为互评指标 **/
	private boolean mutual;
	/** 是否督察组测评指标 **/
	private boolean forSupervisor;
	/** 指标分值 **/
	private float score;
	/** 排列顺序 **/
	private int orderNumber;

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

	public boolean isMutual() {
		return mutual;
	}

	public void setMutual(boolean mutual) {
		this.mutual = mutual;
	}

	public boolean isForSupervisor() {
		return forSupervisor;
	}

	public void setForSupervisor(boolean forSupervisor) {
		this.forSupervisor = forSupervisor;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

}
