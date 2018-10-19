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
import com.thinkgem.jeesite.common.utils.StringUtils;
import cn.edu.ccnu.imd.gsmis.entity.Thesis;
import cn.edu.ccnu.imd.gsmis.dao.ThesisDao;
import cn.edu.ccnu.imd.gsmis.entity.Account;
import cn.edu.ccnu.imd.gsmis.dao.AccountDao;

/**
 * 论文信息管理Service
 * @author hyf
 * @version 2018-08-16
 */
@Service
@Transactional(readOnly = true)
public class ThesisService extends CrudService<ThesisDao, Thesis> {

	@Autowired
	private AccountDao accountDao;
	@Autowired
	private AccountService accountService;
	public Thesis get(String id) {
		Thesis thesis = super.get(id);
		Account account= new Account();
		account.setStudentid(thesis.getStudentid());
		thesis.setAccountList(accountDao.findList(account));
		return thesis;
	}
	public List<Thesis> findList(Thesis thesis) {
		return super.findList(thesis);
	}
	
	public Page<Thesis> findPage(Page<Thesis> page, Thesis thesis) {
		return super.findPage(page, thesis);
	}
	
	@Transactional(readOnly = false)
	public String save1(Thesis thesis) {
		//super.save(thesis);
		String flag="";
		if (thesis.getId() == null||thesis.getId().isEmpty()){
			Thesis thesis1=new Thesis();
			for(Thesis t:findList(thesis1)) {
				if(thesis.getStudentid().equals(t.getStudentid())) {
				flag="论文信息表中已存在学号为"+thesis.getStudentid()+"的记录";//如果存在相同学号
				return flag;
					}
				}
			}
		System.out.println("hshh"+thesis.getAccountList());
		for (Account account : thesis.getAccountList()){
			if (Account.DEL_FLAG_NORMAL.equals(account.getDelFlag())){
				System.out.println("hahha");
				if (StringUtils.isBlank(account.getStudentid())){
					for(Account a:accountService.findList(new Account())) {
						if(account.getUsername().equals(a.getUsername())) {
							flag="已存在用户名为:"+account.getUsername()+"的记录";
							return flag;
						}
					}
					account.setStudentid(thesis.getStudentid());
					account.setThesis(thesis);
					account.preInsert();
					accountDao.insert(account);
				}else{
					account.preUpdate();
					accountDao.update(account);
				}
			}else{
				accountDao.delete(account);
			}
		}
		super.save(thesis);
		return flag;
	}
	
	@Transactional(readOnly = false)
	public void delete(Thesis thesis) {
//		AccountService accountService=new AccountService();
		super.delete(thesis);
		for(Account account:thesis.getAccountList()) {
			accountDao.delete(account);	
		}
	}
}