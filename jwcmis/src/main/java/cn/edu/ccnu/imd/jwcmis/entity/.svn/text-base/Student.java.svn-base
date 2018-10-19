/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.edu.ccnu.imd.jwcmis.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 学生信息管理Entity
 * @author 黑白
 * @version 2018-09-23
 */
public class Student extends DataEntity<Student> {
	
	private static final long serialVersionUID = 1L;
	private String userid;		// 用户ID
	private String name;		// 学生姓名
	private String studentnum;		// 学号
	private String supervisor;		// 导师名
	private String department;		// 学院
	private String email;		// 邮箱
	private String phone;		// 手机号
	private String major;		// 专业
	
	public Student() {
		super();
	}

	public Student(String id){
		super(id);
	}

	@Length(min=1, max=64, message="用户ID长度必须介于 1 和 64 之间")
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	@Length(min=1, max=64, message="学生姓名长度必须介于 1 和 64 之间")
	@ExcelField(title="学生姓名",align=2, sort=10)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=64, message="学号长度必须介于 1 和 64 之间")
	@ExcelField(title="学号",align=2, sort=20)
	public String getStudentnum() {
		return studentnum;
	}

	public void setStudentnum(String studentnum) {
		this.studentnum = studentnum;
	}
	
	@Length(min=1, max=64, message="导师名长度必须介于 1 和 64 之间")
	@ExcelField(title="导师姓名",align=2, sort=30)
	public String getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}
	
	@Length(min=1, max=64, message="学院长度必须介于 1 和 64 之间")
	@ExcelField(title="学院名",align=2, sort=30)
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
	@Length(min=1, max=64, message="邮箱长度必须介于 1 和 64 之间")
	@ExcelField(title="邮箱",align=2, sort=40)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Length(min=1, max=64, message="手机号长度必须介于 1 和 64 之间")
	@ExcelField(title="手机号",align=2, sort=50)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Length(min=1, max=64, message="专业长度必须介于 1 和 64 之间")
	@ExcelField(title="专业名",align=2, sort=60)
	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}
	
}