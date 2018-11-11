/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.test.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 专业信息管理Entity
 * @author 张绍燚
 * @version 2018-07-16
 */
public class TestMajor extends DataEntity<TestMajor> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// name
	private String zydm;		// zydm
	private String schoolXydm;		// school_xydm
	private String zyxz;		// zyxz
	private String sfxz;		// sfxz
	private String status;		// status
	private String schoolname;
	private Date createTime;		// 创建时间
	private Date updateTime;		// 更新时间
	
	@ExcelField(title="学院名",align=2 ,sort=10)
	public String getSchoolname() {
		return schoolname;
	}

	public void setSchoolname(String schoolname) {
		this.schoolname = schoolname;
	}

	public TestMajor() {
		super();
	}

	public TestMajor(String id){
		super(id);
	}

	@ExcelField(title="专业名",align=2 ,sort=20)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@ExcelField(title="专业编号",align=2 ,sort=30)
	public String getZydm() {
		return zydm;
	}

	public void setZydm(String zydm) {
		this.zydm = zydm;
	}
	
	@ExcelField(title="学院编号",align=2 ,sort=40)
	public String getSchoolXydm() {
		return schoolXydm;
	}

	public void setSchoolXydm(String schoolXydm) {
		this.schoolXydm = schoolXydm;
	}
	
	@ExcelField(title="专业性质",align=2 ,sort=50)
	public String getZyxz() {
		return zyxz;
	}

	public void setZyxz(String zyxz) {
		this.zyxz = zyxz;
	}
	
	@ExcelField(title="是否师范",align=2 ,sort=60)
	public String getSfxz() {
		return sfxz;
	}

	public void setSfxz(String sfxz) {
		this.sfxz = sfxz;
	}
	
	@ExcelField(title="状态",align=2 ,sort=70)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public void setMeetId(String id) {
		// TODO Auto-generated method stub
		
	}
	
	public void setTestInstitute(TestInstitute testInstitute) {
		// TODO Auto-generated method stub
		
	}
}