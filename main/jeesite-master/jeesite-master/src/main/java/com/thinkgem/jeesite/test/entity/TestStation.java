/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.test.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 车站信息Entity
 * @author 张绍燚
 * @version 2018-07-17
 */
public class TestStation extends DataEntity<TestStation> {
	
	private static final long serialVersionUID = 1L;
	private String stationId;		// station_id
	private String name;		// name
	private String memo;		// memo
	
	public TestStation() {
		super();
	}

	public TestStation(String id){
		super(id);
	}

	@Length(min=0, max=64, message="station_id长度必须介于 0 和 64 之间")
	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}
	
	@Length(min=0, max=20, message="name长度必须介于 0 和 20 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=255, message="memo长度必须介于 0 和 255 之间")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
}