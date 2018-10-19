/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.test.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 报名信息Entity
 * @author 张绍燚
 * @version 2018-07-17
 */
public class TestMeet extends DataEntity<TestMeet> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// name
	private String sex;		// sex
	private String tel;		// tel
	private String depart;		// depart
	private Integer timex;		// timex
	private String trans;		// trans
	private String num;		// num
	private String stationId;		// station_id
	
	public TestMeet() {
		super();
	}

	public TestMeet(String id){
		super(id);
	}

	@ExcelField(title="姓名",align=2 ,sort=10)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@ExcelField(title="性别",align=2 ,sort=20)
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@ExcelField(title="电话",align=2 ,sort=30)
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	@ExcelField(title="部门",align=2 ,sort=40)
	public String getDepart() {
		return depart;
	}

	public void setDepart(String depart) {
		this.depart = depart;
	}
	@ExcelField(title="次数",align=2 ,sort=50)
	public Integer getTimex() {
		return timex;
	}

	public void setTimex(Integer timex) {
		this.timex = timex;
	}
	
	@ExcelField(title="交通方式",align=2 ,sort=60)
	public String getTrans() {
		return trans;
	}

	public void setTrans(String trans) {
		this.trans = trans;
	}
	
	@ExcelField(title="数量",align=2 ,sort=70)
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}
	
	@ExcelField(title="车站id",align=2 ,sort=80)
	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}
	
}