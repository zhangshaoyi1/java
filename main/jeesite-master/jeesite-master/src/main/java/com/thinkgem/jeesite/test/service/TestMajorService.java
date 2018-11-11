/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.test.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.test.entity.TestMajor;
import com.thinkgem.jeesite.test.dao.TestMajorDao;

/**
 * 专业信息管理Service
 * @author 张绍燚
 * @version 2018-07-16
 */
@Service
@Transactional(readOnly = true)
public class TestMajorService extends CrudService<TestMajorDao, TestMajor> {

	public TestMajor get(String id) {
		return super.get(id);
	}
	
	public List<TestMajor> findList(TestMajor testMajor) {
		return super.findList(testMajor);
	}
	
	public Page<TestMajor> findPage(Page<TestMajor> page, TestMajor testMajor) {
		return super.findPage(page, testMajor);
	}
	
	@Transactional(readOnly = false)
	public void save(TestMajor testMajor) {
		super.save(testMajor);
	}
	
	@Transactional(readOnly = false)
	public void delete(TestMajor testMajor) {
		super.delete(testMajor);
	}
	
}