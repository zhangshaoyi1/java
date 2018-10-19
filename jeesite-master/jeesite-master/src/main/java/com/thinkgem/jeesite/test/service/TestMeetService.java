/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.test.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.test.entity.TestMeet;
import com.thinkgem.jeesite.test.dao.TestMeetDao;

/**
 * 报名信息Service
 * @author 张绍燚
 * @version 2018-07-17
 */
@Service
@Transactional(readOnly = true)
public class TestMeetService extends CrudService<TestMeetDao, TestMeet> {

	public TestMeet get(String id) {
		return super.get(id);
	}
	
	public List<TestMeet> findList(TestMeet testMeet) {
		return super.findList(testMeet);
	}
	
	public Page<TestMeet> findPage(Page<TestMeet> page, TestMeet testMeet) {
		return super.findPage(page, testMeet);
	}
	
	@Transactional(readOnly = false)
	public void save(TestMeet testMeet) {
		super.save(testMeet);
	}
	
	@Transactional(readOnly = false)
	public void delete(TestMeet testMeet) {
		super.delete(testMeet);
	}
	
}