/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.test.entity.TestDataChild;
import com.thinkgem.jeesite.test.entity.TestInstitute;
import com.thinkgem.jeesite.test.entity.TestMajor;
import com.thinkgem.jeesite.test.dao.TestInstituteDao;
import com.thinkgem.jeesite.test.dao.TestMajorDao;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.persistence.Page;

/**
 * 学院信息管理Service
 * @author 之所以
 * @version 2018-07-16
 */
@Service
@Transactional(readOnly = true)
public class TestInstituteService extends CrudService<TestInstituteDao, TestInstitute> {
	@Autowired 
	private TestMajorDao testMajorDao;
	
	public TestInstitute  get(String id) {
		TestInstitute testInstitute = this.dao.get(id);
		//根据major的id去查询成员
		TestMajor testMajor = new TestMajor();
		testMajor.setZydm(id);
		testMajorDao.findList(testMajor);
		return testInstitute;
	}

//  一对多--------------begin-------------------------------------------save---------
	@Transactional(readOnly = false)
	public void save(TestInstitute testInstitute) {
		super.save(testInstitute);
		for (TestMajor testMajor : testInstitute.getMajorList()){
			if (testMajor.getId() == null){
				continue;
			}
			//testMajor.DEL_FLAG_NORMAL=0
			if ("0".equals(testMajor.getDelFlag())){
				if (StringUtils.isBlank(testMajor.getId())){
					//判断id是否为空，如果为空就执行下面语句
					testMajor.setTestInstitute(testInstitute);//
					testMajor.preInsert();//插入之前调用的方法，可以调用相关方法，得到更新时间，创建时间，创建用户，更新用户等
					testMajorDao.insert(testMajor);//调用dao里面的方法插入
				}else{
					testMajor.preUpdate();
					testMajorDao.update(testMajor);
				}
			}else{
				testMajorDao.delete(testMajor);
			}
		}
	}

	
		
public List<TestInstitute> findList(TestInstitute testInstitute) {
	return super.findList(testInstitute);
}
public Page<TestInstitute> findPage(Page<TestInstitute> page, TestInstitute testInstitute) {
	
	return super.findPage(page, testInstitute);
}	
}