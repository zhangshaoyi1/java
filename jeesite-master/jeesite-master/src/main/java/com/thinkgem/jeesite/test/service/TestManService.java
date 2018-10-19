/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.test.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.test.entity.TestMan;
import com.thinkgem.jeesite.test.dao.TestManDao;

/**
 * 人员信息管理Service
 * @author 张绍燚
 * @version 2018-07-16
 */
@Service
@Transactional(readOnly = true)
public class TestManService extends CrudService<TestManDao, TestMan> {

	public TestMan get(String id) {
		return super.get(id);
	}
	
	public List<TestMan> findList(TestMan testMan) {
		return super.findList(testMan);
	}
	
	public Page<TestMan> findPage(Page<TestMan> page, TestMan testMan) {
		return super.findPage(page, testMan);
	}
	
	@Transactional(readOnly = false)
	public void save(TestMan testMan) {
		super.save(testMan);
	}
	
	@Transactional(readOnly = false)
	public void delete(TestMan testMan) {
		super.delete(testMan);
	}
	
}