/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.edu.ccnu.imd.jwcmis.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import cn.edu.ccnu.imd.jwcmis.entity.Institute;

/**
 * 学院信息表DAO接口
 * @author 黑白
 * @version 2018-10-06
 */
@MyBatisDao
public interface InstituteDao extends CrudDao<Institute> {
	
}