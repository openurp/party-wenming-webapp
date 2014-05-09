package org.openurp.kernel.base.unit.model;

import java.sql.Date;

import org.beangle.commons.entity.pojo.IntegerIdObject;

public class Department extends IntegerIdObject {

	private String code;

	private String name;

	private Date beginOn;

	private Date endOn;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBeginOn() {
		return beginOn;
	}

	public void setBeginOn(Date beginOn) {
		this.beginOn = beginOn;
	}

	public Date getEndOn() {
		return endOn;
	}

	public void setEndOn(Date endOn) {
		this.endOn = endOn;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
