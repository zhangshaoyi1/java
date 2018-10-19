/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.edu.ccnu.imd.jwcmis.entity;

import org.hibernate.validator.constraints.Length;
import java.util.List;
import com.google.common.collect.Lists;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 学院信息表Entity
 * @author 黑白
 * @version 2018-10-06
 */
public class Institute extends DataEntity<Institute> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 学院名
	private List<Major> majorList = Lists.newArrayList();		// 子表列表
	
	public Institute() {
		super();
	}

	public Institute(String id){
		super(id);
	}

	@Length(min=1, max=64, message="学院名长度必须介于 1 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<Major> getMajorList() {
		return majorList;
	}

	public void setMajorList(List<Major> majorList) {
		this.majorList = majorList;
	}
}