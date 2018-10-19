/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.test.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.test.entity.TestStation;
import com.thinkgem.jeesite.test.dao.TestStationDao;

/**
 * 车站信息Service
 * @author 张绍燚
 * @version 2018-07-17
 */
@Service
@Transactional(readOnly = true)
public class TestStationService extends CrudService<TestStationDao, TestStation> {

	public TestStation get(String id) {
		return super.get(id);
	}
	
	public List<TestStation> findList(TestStation testStation) {
		return super.findList(testStation);
	}
	
	public Page<TestStation> findPage(Page<TestStation> page, TestStation testStation) {
		return super.findPage(page, testStation);
	}
	
	@Transactional(readOnly = false)
	public void save(TestStation testStation) {
		super.save(testStation);
	}
	
	@Transactional(readOnly = false)
	public void delete(TestStation testStation) {
		super.delete(testStation);
	}
	
}