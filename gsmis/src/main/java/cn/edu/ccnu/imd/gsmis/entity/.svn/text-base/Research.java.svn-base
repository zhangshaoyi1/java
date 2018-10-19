/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.edu.ccnu.imd.gsmis.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 研究方向管理Entity
 * @author cgq
 * @version 2018-08-23
 */
public class Research extends DataEntity<Research> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 研究方向名
	
	public Research() {
		super();
	}

	public Research(String id){
		super(id);
	}

	@Length(min=1, max=255, message="研究方向名长度必须介于 1 和 255 之间")
	@ExcelField(title="研究方向",align=2, sort=20)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}