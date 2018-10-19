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
import cn.edu.ccnu.imd.jwcmis.entity.Major;
import cn.edu.ccnu.imd.jwcmis.dao.MajorDao;

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

	public Major findTheData(Major major) {
		return majorDao.findTheData(major);
	}
	
	//导入数据时的查重
	public boolean importCheckRepeat(Major major) {
		boolean checkRepeat = true;									//查重结果，true表示没有重复，false表示重复
		//---由于数据中对major的xml中对name是模糊匹配的，为防止列表数据过多，先进行筛选，经过模糊匹配后再进行查重
		Major majorMatch = new Major();
		majorMatch.setName(major.getName());
		List<Major> majorMatchList = findList(majorMatch);			//这里的majorMatch是数据库模糊匹配后的结果
		if(majorMatchList != null && majorMatchList.size() > 0)	{
			for(Major mOld : majorMatchList) {						//先遍历匹配后的链表
				if(major.getName().equals(mOld.getName())) {
					checkRepeat = false;
					return checkRepeat;								//一旦找到重复的，就返回false
				}
			}
		}
		return checkRepeat;
	}

}