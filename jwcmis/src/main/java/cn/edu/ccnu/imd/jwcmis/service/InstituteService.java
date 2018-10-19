/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.edu.ccnu.imd.jwcmis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import cn.edu.ccnu.imd.jwcmis.entity.Institute;
import cn.edu.ccnu.imd.jwcmis.dao.InstituteDao;
import cn.edu.ccnu.imd.jwcmis.entity.Major;
import cn.edu.ccnu.imd.jwcmis.dao.MajorDao;

/**
 * 学院信息表Service
 * @author 黑白
 * @version 2018-10-06
 */
@Service
@Transactional(readOnly = true)
public class InstituteService extends CrudService<InstituteDao, Institute> {

	@Autowired
	private MajorDao majorDao;
	
	public Institute get(String id) {
		Institute institute = super.get(id);
		institute.setMajorList(majorDao.findList(new Major(institute)));
		return institute;
	}
	
	public List<Institute> findList(Institute institute) {
		return super.findList(institute);
	}
	
	public Page<Institute> findPage(Page<Institute> page, Institute institute) {
		return super.findPage(page, institute);
	}
	
	@Transactional(readOnly = false)
	public void save(Institute institute) {
		super.save(institute);
		for (Major major : institute.getMajorList()){
			if (major.getId() == null){
				continue;
			}
			if (Major.DEL_FLAG_NORMAL.equals(major.getDelFlag())){
				if (StringUtils.isBlank(major.getId())){
					major.setInstitute(institute);
					major.preInsert();
					majorDao.insert(major);
				}else{
					major.preUpdate();
					majorDao.update(major);
				}
			}else{
				majorDao.delete(major);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(Institute institute) {
		super.delete(institute);
		majorDao.delete(new Major(institute));
	}
	
}