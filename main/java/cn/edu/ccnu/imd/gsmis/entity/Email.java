/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.edu.ccnu.imd.gsmis.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 邮件模板管理Entity
 * @author cgq
 * @version 2018-08-14
 */
public class Email extends DataEntity<Email> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 模板名
	private String text;		// 内容
	
	public Email() {
		super();
	}

	public Email(String id){
		super(id);
	}

	@Length(min=1, max=64, message="模板名长度必须介于 1 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=255, message="内容长度必须介于 1 和 255 之间")
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}