/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.edu.ccnu.imd.gsmis.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 论文信息管理Entity
 * @author hyf
 * @version 2018-08-16
 */
public class Account extends DataEntity<Account> {
	
	private static final long serialVersionUID = 1L;
	private String studentid;//学生id
	private String username;		// 用户名
	private String password;		// 密码
	private Thesis thesis;//论文主表
	public Account() {
		super();
	}
	
	public Account(String id){
		super(id);
	}
	public Account(Thesis thesis){
		this.thesis = thesis;
	}
	public Account(String studentid,String username,String password){
		this.studentid=studentid;
		this.username=username;
		this.password=password;
	}
	@Length(min=1, max=64, message="学号长度必须介于 1 和 64 之间")
	public String getStudentid() {
		return studentid;
	}
	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}
	@Length(min=1, max=64, message="用户名长度必须介于 1 和 64 之间")
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsername() {
		return username;
	}
	@Length(min=1, max=64, message="密码长度必须介于 1 和 64 之间")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	@Length(min=0, max=64, message="论文主表长度必须介于 0 和 64 之间")
	public Thesis getThesis() {
		return thesis;
	}
	public void setThesis(Thesis thesis) {
		this.thesis = thesis;
	}
}