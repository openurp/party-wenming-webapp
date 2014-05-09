package org.openurp.kernel.base.unit.model;

import org.beangle.commons.entity.pojo.IntegerIdObject;

public class Department extends IntegerIdObject {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
