/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.edu.ccnu.imd.jwcmis.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;

import cn.edu.ccnu.imd.jwcmis.entity.Research;
import cn.edu.ccnu.imd.jwcmis.dao.ResearchDao;

/**
 * 研究方向管理Service
 * @author cgq
 * @version 2018-08-23
 */
@Service
@Transactional(readOnly = true)
public class ResearchService extends CrudService<ResearchDao, Research> {

	public Research get(String id) {
		return super.get(id);
	}
	
	public List<Research> findList(Research research) {
		return super.findList(research);
	}
	
	public Page<Research> findPage(Page<Research> page, Research research) {
		return super.findPage(page, research);
	}
	
	@Transactional(readOnly = false)
	public void save(Research research) {
		super.save(research);
	}
	
	@Transactional(readOnly = false)
	public void delete(Research research) {
		super.delete(research);
	}
	
	//导入数据时的查重
	public boolean importCheckRepeat(Research research) {
		boolean checkRepeat = true;									//查重结果，true表示没有重复，false表示重复
		//---由于数据中对research的xml中对name是模糊匹配的，为防止列表数据过多，先进行筛选，经过模糊匹配后再进行查重
		Research researchMatch = new Research();
		researchMatch.setName(research.getName());
		List<Research> researchMatchList = findList(researchMatch);
		if(researchMatchList != null && researchMatchList.size() > 0) {
			for(Research rOld : researchMatchList) {				//先遍历researchOldList数据库已有的数据
				if(research.getName().equals(rOld.getName())) {
					checkRepeat = false;
					return checkRepeat;								//一旦找到重复的，就返回false
				}
			}
		}			
		return checkRepeat;
	}
	
	/**
	 * 通过编号获取研究方向标题
	 * @return new Object[]{Id,标题}
	 */
	public List<Object[]> findByIds(String ids) {
		if(ids == null){
			return new ArrayList<Object[]>();
		}
		List<Object[]> list = Lists.newArrayList();
		String[] idss = StringUtils.split(ids,",");
		Research e = null;
		for(int i=0;(idss.length-i)>0;i++){
			e = dao.get(idss[i]);
			list.add(new Object[]{e.getId(),StringUtils.abbr(e.getName(),50)});
		}
		return list;
	}
	
}