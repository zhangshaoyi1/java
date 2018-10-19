/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.edu.ccnu.imd.jwcmis.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import cn.edu.ccnu.imd.jwcmis.entity.ReviewResult;

/**
 * 评审结果管理DAO接口
 * @author 黑白
 * @version 2018-09-23
 */
@MyBatisDao
public interface ReviewResultDao extends CrudDao<ReviewResult> {
	
}