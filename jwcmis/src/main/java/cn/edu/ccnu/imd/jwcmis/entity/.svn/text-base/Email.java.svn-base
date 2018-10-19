/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.edu.ccnu.imd.jwcmis.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 邮件模板管理Entity
 * @author cgq
 * @version 2018-08-13
 */
public class Email extends DataEntity<Email> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 模板名
	private String text;		// 内容
	private String status;
	
	public Email() {
		super();
	}

	public Email(String id){
		super(id);
	}
	@Length(min=1, max=1, message="状态0表示禁用，1表示使用，长度必须介于 1 和 10 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Length(min=1, max=64, message="模板名长度必须介于 1 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=20000, message="内容长度必须介于 1 和 20000之间")
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}