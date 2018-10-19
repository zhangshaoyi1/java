/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.edu.ccnu.imd.jwcmis.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 学院信息表Entity
 * @author 黑白
 * @version 2018-10-06
 */
public class Major extends DataEntity<Major> {
	
	private static final long serialVersionUID = 1L;
	private Institute institute;		// 学院ID 父类
	private String name;		// 专业名
	
	public Major() {
		super();
	}

	public Major(String id){
		super(id);
	}

	public Major(Institute institute){
		this.institute = institute;
	}

	@Length(min=1, max=64, message="学院ID长度必须介于 1 和 64 之间")
	public Institute getInstitute() {
		return institute;
	}

	public void setInstitute(Institute institute) {
		this.institute = institute;
	}
	
	@Length(min=1, max=64, message="专业名长度必须介于 1 和 64 之间")
	@ExcelField(title="专业名称",align=2, sort=20)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ExcelField(title="学院名称",align=2, sort=20)
	public String getInstituteName() {
		return institute.getName();
	}

	public void setInstituteName(String name) {
		this.institute.setName(name);
	}
}