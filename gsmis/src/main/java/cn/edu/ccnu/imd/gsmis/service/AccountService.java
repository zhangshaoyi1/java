/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.edu.ccnu.imd.gsmis.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import cn.edu.ccnu.imd.gsmis.entity.Account;
import cn.edu.ccnu.imd.gsmis.dao.AccountDao;

/**
 * 论文信息管理Service
 * @author hyf
 * @version 2018-08-16
 */
@Service
@Transactional(readOnly = true)
public class AccountService extends CrudService<AccountDao, Account> {

	

	public List<Account> findList(Account account) {
		return super.findList(account);
	}
	
	public Page<Account> findPage(Page<Account> page, Account account) {
		return super.findPage(page, account);
	}
	@Transactional(readOnly = false)
	public boolean save1(Account account) {
		boolean flag=true;
		for(Account a:findList(new Account())) {
			if(a.getUsername().equals(account.getUsername())) {
			flag=false;
			return flag;
			}
		}
		super.save(account);
		return true;
	}
	@Transactional(readOnly = false)
	public void delete(Account account) {
		super.delete(account);
	}
	
}