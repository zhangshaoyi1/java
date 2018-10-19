/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.edu.ccnu.imd.jwcmis.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.security.Cryptos;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.dao.UserDao;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.Role;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

import cn.edu.ccnu.imd.jwcmis.entity.Expert;
import cn.edu.ccnu.imd.jwcmis.entity.Major;
import cn.edu.ccnu.imd.jwcmis.dao.ExpertDao;

/**
 * 专家信息管理Service
 * @author cgq
 * @version 2018-08-13
 */
@Service
@Transactional(readOnly = true)
public class ExpertService extends CrudService<ExpertDao, Expert> {

	public static final String Expert_Role = "2";
	 
	@Autowired 
	public ExpertDao expertDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	public SystemService systemService;
	@Autowired
	public MajorService majorService;
	@Autowired
	public OfficeService officeService;
	
	public Expert get(String id) {
		//从数据库中读取数据，由于数据库中的数据已经加密，读取时需要解密，然后返回解密后的数据
		Expert e = super.get(id);
		decryption(e);
		return e;
	}
	
	public List<Expert> findList(Expert expert) {
		//解密可以放入findlist，也可以放在fandpage中的fandlist
		List<Expert> expertList = super.findList(expert);
		for(Expert k : expertList ) {
			decryption(k);
		}
		return expertList;
	}
	
	public Page<Expert> findPage(Page<Expert> page, Expert expert) {
		expert.setPage(page);
		//查询前，由于数据库中的数据是加密的，而查询条件没有加密，所以要对查询条件进行加密操作
		encryption(expert);
		page.setList(findList(expert));
		//查询后，要对页面中的查询条件解密，否则刷新后查询条件会显示加密的结果
		decryption(expert);
		return page;
	}
	
	@Transactional(readOnly = false)
	public void save(Expert expert) {
		encryption(expert);
		super.save(expert);
		decryption(expert);
		addUser(expert);
	}
	
	@Transactional(readOnly = false)
	public void delete(Expert expert) {
		super.delete(expert);
	}
	
	//保存一条数据时自动生成一个用户
	@Transactional(readOnly = false)
	public void addUser(Expert expert) {
		User user = new User();
		user.setId(expert.getId());
		Office company = new Office();
		//根据专家类型分配用户类型（校内1和校外5）
		if(expert.getExpertType().equals("0")) {
			company.setId("1");
		}else {
			company.setId("5");
		}
		//根据用户类型获取一整条数据
		company = officeService.findTheData(company);
		user.setCompany(company);
		
		Office office = new Office();
		Major major = new Major();
		//根据专家的专业获取专业id
		major.setId(expert.getMajor());
		//根据专业id获取一整条专业的信息
		major = majorService.findTheData(major);
		//根据专业所属的学院确定专家所属的机构
		office.setName(major.getInstituteName());
		//更新一整条信息
		office = officeService.findTheData(office);
		user.setOffice(office);
		
		//用手机号作为登录名
		user.setLoginName(expert.getPhone());
		//用手机号作为登录密码
		user.setPassword(this.getUserPassword(expert.getPhone()));
		user.setName(expert.getExpertName());
		user.setEmail(expert.getEmail());
		
		user.setUpdateDate(new Date());
		user.setCreateDate(user.getUpdateDate());
		user.setCreateBy(UserUtils.getUser());
		user.setUpdateBy(UserUtils.getUser());
		
		userDao.insert(user);
		
		// 角色数据有效性验证，过滤不在授权内的角色
		List<Role> roleList = Lists.newArrayList();
		Role r = new Role();
		r.setId(Expert_Role);
		roleList.add(r);
				 
		user.setRoleList(roleList);
				
		systemService.saveUser(user);
	}
	
	private String getUserPassword(String phone_num){ 
		if(phone_num != null && phone_num.length() > 6) { 
			return SystemService.entryptPassword(phone_num.substring(phone_num.length()-6));	
		}else{ 
			return SystemService.entryptPassword("123456");
		} 
	}
	//加密单条数据
	public Expert encryption(Expert expert) {
		//对专家姓名、手机号、邮箱、银行卡号进行AES加密，加密前要判空
		if(expert.getExpertName() != null && !"".equals(expert.getExpertName())) {
			expert.setExpertName(Cryptos.aesEncrypt(expert.getExpertName()));
		}
		if(expert.getEmail() != null && !"".equals(expert.getEmail())) {
			expert.setEmail(Cryptos.aesEncrypt(expert.getEmail()));
		}
		if(expert.getPhone() != null && !"".equals(expert.getPhone())) {
			expert.setPhone(Cryptos.aesEncrypt(expert.getPhone()));
		}
		if(expert.getBankCardNumber() != null && !"".equals(expert.getBankCardNumber())) {
			expert.setBankCardNumber(Cryptos.aesEncrypt(expert.getBankCardNumber()));
		}
		return expert;
	}
	
	//解密单条数据
	public Expert decryption(Expert expert) {
		//解密的时候要进行判空
		if(expert.getExpertName() != null && !"".equals(expert.getExpertName())) {
			expert.setExpertName(Cryptos.aesDecrypt(expert.getExpertName()));
		}
		if(expert.getEmail() != null && !"".equals(expert.getEmail())) {
			expert.setEmail(Cryptos.aesDecrypt(expert.getEmail()));
		}
		if(expert.getPhone() != null && !"".equals(expert.getPhone())) {
			expert.setPhone(Cryptos.aesDecrypt(expert.getPhone()));
		}
		if(expert.getBankCardNumber() != null && !"".equals(expert.getBankCardNumber())) {
			expert.setBankCardNumber(Cryptos.aesDecrypt(expert.getBankCardNumber()));
		}
		return expert;
	}
	
	
	//导入数据时的查重
	public boolean importCheckRepeat(Expert expert) {				//这里的expert是导入文件的内容，是未加密的
		boolean checkRepeat = true;									//查重结果，true表示没有重复，false表示重复
		Expert expertOld = new Expert();
		expertOld.setExpertName(expert.getExpertName());
		expertOld.setEmail(expert.getEmail());
		expertOld.setPhone(expert.getPhone());
		expertOld.setBankCardNumber(expert.getBankCardNumber());
		encryption(expertOld);
		List<Expert> expertOldList = findList(expertOld);			//这里的expertOld是数据库已有的内容，是已经加密的
		if(expertOldList != null && expertOldList.size() > 0) {
			//如果数据库中存在一条邮箱、手机号、银行卡号都相同的数据，就说明重复，返回false
			checkRepeat = false;
		}
		return checkRepeat;
	}
}