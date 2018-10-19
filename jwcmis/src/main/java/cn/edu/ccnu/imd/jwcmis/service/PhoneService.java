/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.edu.ccnu.imd.jwcmis.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import cn.edu.ccnu.imd.jwcmis.entity.Phone;
import cn.edu.ccnu.imd.jwcmis.dao.PhoneDao;

/**
 * 短信模板管理Service
 * @author cgq
 * @version 2018-08-13
 */
@Service
@Transactional(readOnly = true)
public class PhoneService extends CrudService<PhoneDao, Phone> {

	public Phone get(String id) {
		return super.get(id);
	}
	
	public List<Phone> findList(Phone phone) {
		return super.findList(phone);
	}
	
	public Page<Phone> findPage(Page<Phone> page, Phone phone) {
		return super.findPage(page, phone);
	}
	
	@Transactional(readOnly = false)
	public void save(Phone phone) {
		super.save(phone);
	}
	
	@Transactional(readOnly = false)
	public void delete(Phone phone) {
		super.delete(phone);
	}
	
}