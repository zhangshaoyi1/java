/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.edu.ccnu.imd.gsmis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import cn.edu.ccnu.imd.gsmis.entity.Major;
import cn.edu.ccnu.imd.gsmis.dao.MajorDao;

/**
 * 专业信息管理Service
 * @author cgq
 * @version 2018-08-13
 */
@Service
@Transactional(readOnly = true)
public class MajorService extends CrudService<MajorDao, Major> {

	@Autowired 
	public MajorDao majorDao;
	
	
	public Major get(String id) {
		return super.get(id);
	}
	
	public List<Major> findList(Major major) {
		return super.findList(major);
	}
	
	public Page<Major> findPage(Page<Major> page, Major major) {
		return super.findPage(page, major);
	}
	
	@Transactional(readOnly = false)
	public void save(Major major) {
		super.save(major);
	}
	
	@Transactional(readOnly = false)
	public void delete(Major major) {
		super.delete(major);
	}

	public Major findByName(Major major) {
		// TODO Auto-generated method stub
		return majorDao.findByName(major);
	}
	
}