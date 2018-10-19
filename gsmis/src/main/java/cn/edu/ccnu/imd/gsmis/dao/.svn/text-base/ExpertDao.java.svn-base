/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.edu.ccnu.imd.gsmis.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

import cn.edu.ccnu.imd.gsmis.entity.Expert;
import cn.edu.ccnu.imd.gsmis.entity.Research;

/**
 * 专家信息管理DAO接口
 * @author cgq
 * @version 2018-08-13
 */
@MyBatisDao
public interface ExpertDao extends CrudDao<Expert> {
	
	public List<Expert> findByIdIn(String[] ids);
//	{
//		return find("from Article where id in (:p1)", new Parameter(new Object[]{ids}));
//	}
	
	public int updateHitsAddOne(String id);
//	{
//		return update("update Article set hits=hits+1 where id = :p1", new Parameter(id));
//	}
	
	public int updateExpiredWeight(Expert expert);
	
	public List<Research> findStats(Research research);
//	{
//		return update("update Article set weight=0 where weight > 0 and weightDate < current_timestamp()");
//	}
	
}