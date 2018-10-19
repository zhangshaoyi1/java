/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.edu.ccnu.imd.jwcmis.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.security.Cryptos;
import com.thinkgem.jeesite.common.service.CrudService;

import cn.edu.ccnu.imd.jwcmis.entity.DistributeResult;
import cn.edu.ccnu.imd.jwcmis.dao.DistributeResultDao;

/**
 * 分配结果管理Service
 * @author zjy
 * @version 2018-08-16
 */
@Service
@Transactional(readOnly = true)
public class DistributeResultService extends CrudService<DistributeResultDao, DistributeResult> {

	public DistributeResult get(String id) {
		return super.get(id);
	}
	
	public List<DistributeResult> findList(DistributeResult distributeResult) {
		
		List<DistributeResult> distributeResults = super.findList(distributeResult);
		for(DistributeResult d : distributeResults) {
			d.setInexpertName(Cryptos.aesDecrypt(d.getInexpertName()));
			d.setOutexpertName(Cryptos.aesDecrypt(d.getOutexpertName()));
		}
		return distributeResults;
	}
	
	public Page<DistributeResult> findPage(Page<DistributeResult> page, DistributeResult distributeResult) {
		distributeResult.setPage(page);
		page.setList(findList(distributeResult));
		return page;
	}
	
	@Transactional(readOnly = false)
	public void save(DistributeResult distributeResult) {
		super.save(distributeResult);
	}
	
	@Transactional(readOnly = false)
	public void delete(DistributeResult distributeResult) {
		super.delete(distributeResult);
	}
	
}