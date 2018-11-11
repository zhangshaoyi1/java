/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.test.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.test.entity.TestInstitute;

/**
 * 学院信息管理DAO接口
 * @author 之所以
 * @version 2018-07-16
 */
@MyBatisDao
public interface TestInstituteDao extends CrudDao<TestInstitute> {
	
}