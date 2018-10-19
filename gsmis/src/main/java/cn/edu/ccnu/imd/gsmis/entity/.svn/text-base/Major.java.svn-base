/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.edu.ccnu.imd.gsmis.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 专业信息管理Entity
 * @author cgq
 * @version 2018-08-13
 */
public class Major extends DataEntity<Major> {
	
	private static final long serialVersionUID = 1L;	
	private String name;		// 专业名

	public Major() {
		super();
	}

	public Major(String id){
		super(id);
	}
	
	@Length(min=1, max=64, message="专业名长度必须介于 1 和 64 之间")
	@ExcelField(title="专业名称",align=2, sort=20)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}