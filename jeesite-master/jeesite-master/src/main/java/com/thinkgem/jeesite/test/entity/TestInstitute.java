/**

 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.test.entity;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import com.thinkgem.jeesite.test.service.TestInstituteService;
import com.thinkgem.jeesite.common.persistence.Page;

/**
 * 学院信息管理Entity
 * @author 之所以
 * @version 2018-07-16
 */
public class TestInstitute extends DataEntity<TestInstitute> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// name
	private String xydm;		// xydm
	private String memo;		// memo
	
	public TestInstitute() {
		super();
	}

	public TestInstitute(String id){
		super(id);
	}

	@ExcelField(title="学院名",align=2 ,sort=10)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@ExcelField(title="学院编号",align=2 ,sort=20)
	public String getXydm() {
		return xydm;
	}

	public void setXydm(String xydm) {
		this.xydm = xydm;
	}
	
	@ExcelField(title="备注",align=2 ,sort=30)
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	//一对一---------begin
		@ExcelField(title="专业名",align=2 ,sort=40)
		private TestMajor testMajor;
		
		
		public TestMajor getTestMajor() {
			return testMajor;
		}

		public void setTestMajor(TestMajor testMajor) {
			this.testMajor = testMajor;
		}
		public String getTestMajorName() {
			if(null !=testMajor) {
				return this.testMajor.getName();
			}
			return "";
		}

		public static Page<TestInstitute> findPage(Page<TestInstitute> page, TestInstituteService testInstituteService) {
			// TODO Auto-generated method stub
			return null;
		}
		
		// 一对一 ----- ------end---------------------------------------
		public Object getLoginName() {
			// TODO Auto-generated method stub
			return null;
		}
		public void setPassword(String entryptPassword) {
			// TODO Auto-generated method stub
			
		}
		
	//一对多
	private List<TestMajor> majorList;//字表列表

	public List<TestMajor> getMajorList() {
		return majorList;
	}

	public void setMajorList(List<TestMajor> majorList) {
		this.majorList = majorList;
	}
	
	
}