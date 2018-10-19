/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.edu.ccnu.imd.jwcmis.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

import cn.edu.ccnu.imd.jwcmis.entity.Research;

/**
 * 研究方向管理DAO接口
 * @author cgq
 * @version 2018-08-23
 */
@MyBatisDao
public interface ResearchDao extends CrudDao<Research> {
	
}