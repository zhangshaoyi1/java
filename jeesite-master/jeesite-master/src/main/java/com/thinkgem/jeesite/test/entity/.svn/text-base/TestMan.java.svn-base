/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.test.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 人员信息管理Entity
 * @author 张绍燚
 * @version 2018-07-16
 */
public class TestMan extends DataEntity<TestMan> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// name
	private String sex;		// sex
	private String age;		// age
	private String tel;		// tel
	private String job;		// job
	
	public TestMan() {
		super();
	}

	public TestMan(String id){
		super(id);
	}

	@Length(min=0, max=20, message="name长度必须介于 0 和 20 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=1, message="sex长度必须介于 0 和 1 之间")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Length(min=0, max=2, message="age长度必须介于 0 和 2 之间")
	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
	@Length(min=0, max=11, message="tel长度必须介于 0 和 11 之间")
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	@Length(min=0, max=64, message="job长度必须介于 0 和 64 之间")
	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}
	
}