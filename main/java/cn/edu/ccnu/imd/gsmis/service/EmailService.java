/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.edu.ccnu.imd.gsmis.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import cn.edu.ccnu.imd.gsmis.entity.Email;
import cn.edu.ccnu.imd.gsmis.dao.EmailDao;

/**
 * 邮件模板管理Service
 * @author cgq
 * @version 2018-08-14
 */
@Service
@Transactional(readOnly = true)
public class EmailService extends CrudService<EmailDao, Email> {

	public Email get(String id) {
		return super.get(id);
	}
	
	public List<Email> findList(Email email) {
		return super.findList(email);
	}
	
	public Page<Email> findPage(Page<Email> page, Email email) {
		return super.findPage(page, email);
	}
	
	@Transactional(readOnly = false)
	public void save(Email email) {
		super.save(email);
	}
	
	@Transactional(readOnly = false)
	public void delete(Email email) {
		super.delete(email);
	}
	
}