package org.openurp.webapp.apps.party.wenming.model;

import java.sql.Date;

import javax.persistence.Entity;

import org.beangle.commons.entity.pojo.LongIdObject;

/**
 * 宣传部 考核批次 fpcheng 2011/09/05
 */
@Entity(name = "org.openurp.webapp.apps.party.wenming.model.AssessBatch")
public class AssessBatch extends LongIdObject {

	private static final long serialVersionUID = -8409762431661348326L;

	/** 批次名称 **/
	private String name;
	/** 创建时间 **/
	private Date createTime;
	/** 是否有效 **/
	private Boolean ifEnable = true;
	/** 测评开关状态(true:开,false:关) **/
	private Boolean ifSwitch = false;
	/** 测评开始时间 **/
	private Date beginTime;
	/** 测评结束时间 **/
	private Date endTime;
	/** 投票开关状态(true:开,false:关) **/
	private Boolean ifVoteSwitch = false;
	/** 投票开始时间 **/
	private Date voteBeginTime;
	/** 投票结束时间 **/
	private Date voteEndTime;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Boolean getIfEnable() {
		return ifEnable;
	}

	public void setIfEnable(Boolean ifEnable) {
		this.ifEnable = ifEnable;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Boolean getIfSwitch() {
		return ifSwitch;
	}

	public void setIfSwitch(Boolean ifSwitch) {
		this.ifSwitch = ifSwitch;
	}

	public Boolean getIfVoteSwitch() {
		return ifVoteSwitch;
	}

	public void setIfVoteSwitch(Boolean ifVoteSwitch) {
		this.ifVoteSwitch = ifVoteSwitch;
	}

	public Date getVoteBeginTime() {
		return voteBeginTime;
	}

	public void setVoteBeginTime(Date voteBeginTime) {
		this.voteBeginTime = voteBeginTime;
	}

	public Date getVoteEndTime() {
		return voteEndTime;
	}

	public void setVoteEndTime(Date voteEndTime) {
		this.voteEndTime = voteEndTime;
	}

}
