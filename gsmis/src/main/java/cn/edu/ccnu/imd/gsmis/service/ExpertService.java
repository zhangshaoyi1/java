/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.edu.ccnu.imd.gsmis.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.security.Cryptos;
import com.thinkgem.jeesite.common.service.CrudService;
import cn.edu.ccnu.imd.gsmis.entity.Expert;
import cn.edu.ccnu.imd.gsmis.dao.ExpertDao;

/**
 * 专家信息管理Service
 * @author cgq
 * @version 2018-08-13
 */
@Service
@Transactional(readOnly = true)
public class ExpertService extends CrudService<ExpertDao, Expert> {

	public Expert get(String id) {
		//从数据库中读取数据，由于数据库中的数据已经加密，读取时需要解密，然后返回解密后的数据
		Expert e = super.get(id);
		e.setExpertname(Cryptos.aesDecrypt(e.getExpertname()));
		e.setEmail(Cryptos.aesDecrypt(e.getEmail()));
		e.setPhone(Cryptos.aesDecrypt(e.getPhone()));
		e.setBankcardnumber(Cryptos.aesDecrypt(e.getBankcardnumber()));
		
		return e;
	}
	
	public List<Expert> findList(Expert expert) {
		
		//解密可以放入findlist，也可以放在fandpage中的fandlist
		List<Expert> experts = super.findList(expert);
		for(Expert k : experts ) {
			k.setExpertname(Cryptos.aesDecrypt(k.getExpertname()));
			k.setEmail(Cryptos.aesDecrypt(k.getEmail()));
			k.setPhone(Cryptos.aesDecrypt(k.getPhone()));
			k.setBankcardnumber(Cryptos.aesDecrypt(k.getBankcardnumber()));
		}
		return experts;
		
		//return super.findList(expert);
	}
	
	public Page<Expert> findPage(Page<Expert> page, Expert expert) {
		/*List<Expert> experts = findList(expert);
		for(Expert k : experts ) {
			k.setExpertname(Cryptos.aesDecrypt(k.getExpertname()));
			k.setEmail(Cryptos.aesDecrypt(k.getEmail()));
			k.setPhone(Cryptos.aesDecrypt(k.getPhone()));
			k.setBankcardnumber(Cryptos.aesDecrypt(k.getBankcardnumber()));
			page.setList(findList(k));
		}
		page.setList(experts);
		return super.findPage(page, expert);*/
		
		expert.setPage(page);
		page.setList(findList(expert));
		return page;
		
	}
	
	@Transactional(readOnly = false)
	public void save(Expert expert) {
		
		//对专家姓名、手机号、邮箱、银行卡号进行AES加密
		expert.setExpertname(Cryptos.aesEncrypt(expert.getExpertname()));
		expert.setEmail(Cryptos.aesEncrypt(expert.getEmail()));
		expert.setPhone(Cryptos.aesEncrypt(expert.getPhone()));
		expert.setBankcardnumber(Cryptos.aesEncrypt(expert.getBankcardnumber()));
				
		super.save(expert);
	}
	
	@Transactional(readOnly = false)
	public void delete(Expert expert) {
		super.delete(expert);
	}
	
}