/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.test.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.test.entity.TestMeet;

/**
 * 报名信息DAO接口
 * @author 张绍燚
 * @version 2018-07-17
 */
@MyBatisDao
public interface TestMeetDao extends CrudDao<TestMeet> {
	
}