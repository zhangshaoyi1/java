/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.edu.ccnu.imd.gsmis.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import cn.edu.ccnu.imd.gsmis.entity.Major;

/**
 * 专业信息管理DAO接口
 * @author cgq
 * @version 2018-08-13
 */
@MyBatisDao
public interface MajorDao extends CrudDao<Major> {

	Major findByName(Major major);
	
}