/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.edu.ccnu.imd.jwcmis.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import cn.edu.ccnu.imd.jwcmis.entity.ReviewResult;
import cn.edu.ccnu.imd.jwcmis.dao.ReviewResultDao;

/**
 * 评审结果管理Service
 * @author 黑白
 * @version 2018-09-23
 */
@Service
@Transactional(readOnly = true)
public class ReviewResultService extends CrudService<ReviewResultDao, ReviewResult> {

	public ReviewResult get(String id) {
		return super.get(id);
	}
	
	public List<ReviewResult> findList(ReviewResult reviewResult) {
		return super.findList(reviewResult);
	}
	
	public Page<ReviewResult> findPage(Page<ReviewResult> page, ReviewResult reviewResult) {
		return super.findPage(page, reviewResult);
	}
	
	@Transactional(readOnly = false)
	public void save(ReviewResult reviewResult) {
		super.save(reviewResult);
	}
	
	@Transactional(readOnly = false)
	public void delete(ReviewResult reviewResult) {
		super.delete(reviewResult);
	}
	
}