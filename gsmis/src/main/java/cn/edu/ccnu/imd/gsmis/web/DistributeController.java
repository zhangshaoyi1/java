/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.edu.ccnu.imd.gsmis.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;

import cn.edu.ccnu.imd.gsmis.entity.Distribute;
import cn.edu.ccnu.imd.gsmis.entity.DistributeResult;
import cn.edu.ccnu.imd.gsmis.entity.Expert;
import cn.edu.ccnu.imd.gsmis.entity.Major;
import cn.edu.ccnu.imd.gsmis.entity.Thesis;
import cn.edu.ccnu.imd.gsmis.service.DistributeResultService;
import cn.edu.ccnu.imd.gsmis.service.DistributeService;
import cn.edu.ccnu.imd.gsmis.service.ExpertService;
import cn.edu.ccnu.imd.gsmis.service.MajorService;
import cn.edu.ccnu.imd.gsmis.service.ThesisService;

/**
 * 论文分配管理Controller
 * @author zjy
 * @version 2018-08-14
 */
@Controller
@RequestMapping(value = "${adminPath}/gsmis/distribute")
public class DistributeController extends BaseController {

	@Autowired
	private DistributeService distributeService;
	@Autowired
	private DistributeResultService distributeResultService;
	@Autowired
	private ThesisService thesisService;
	@Autowired
	private ExpertService expertService;
	@Autowired
	private MajorService majorService;
	
	@ModelAttribute
	public Distribute get(@RequestParam(required=false) String id) {
		Distribute entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = distributeService.get(id);
		}
		if (entity == null){
			entity = new Distribute();
		}
		return entity;
	}
	
	@RequiresPermissions("gsmis:distribute:view")
	@RequestMapping(value = {"list", ""})
	public String list(Distribute distribute, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		List<Distribute> distributeList = new ArrayList<Distribute>();
		distributeList = distributeService.findList(distribute);
		model.addAttribute("distributeList", distributeList);
		
		
		
		Page<Distribute> page = distributeService.findPage(new Page<Distribute>(request, response), distribute);
		model.addAttribute("page", page);
		return "imd/gsmis/distributeList";
	}

	@RequiresPermissions("gsmis:distribute:view")
	@RequestMapping(value = "form")
	public String form(Distribute distribute, Model model) {
		model.addAttribute("distribute", distribute);
		return "imd/gsmis/distributeForm";
	}

	@RequiresPermissions("gsmis:distribute:edit")
	@RequestMapping(value = "save")
	public String save(Distribute distribute, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, distribute)){
			return form(distribute, model);
		}
		distributeService.save(distribute);
		addMessage(redirectAttributes, "保存分配管理成功");
		return "redirect:"+Global.getAdminPath()+"/gsmis/distribute/?repage";
	}
	
	@RequiresPermissions("gsmis:distribute:edit")
	@RequestMapping(value = "delete")
	public String delete(Distribute distribute, RedirectAttributes redirectAttributes) {
		distributeService.delete(distribute);
		addMessage(redirectAttributes, "删除分配管理成功");
		return "redirect:"+Global.getAdminPath()+"/gsmis/distribute/?repage";
	}
	
	
	 /*
	  * 思路：
	  * 1.根据专家类型expertType调取专家研究方向research
	  * 2.调用expert的research和keywords进行相似度匹配
	  * 3.当专家论文数达到上限时，不再给其分配论文
	  * 4.匹配成功时，将校内外专家id和论文id进行匹配保存到数据库中，同时对应专家已分配论文数distributeNum+1，论文状态更改为已分配
	  */

	@RequiresPermissions("gsmis:distribute:edit")
	@RequestMapping(value = "fenpei")
	//分配单条数据
	public String fenpei(Distribute distribute, Model model, RedirectAttributes redirectAttributes) {
		distribute = distributeService.get(distribute.getId());//获取单条数据
		if(distribute.getStatus().equals("0")){//如果论文未分配
			
			String[] str = distributeService.fenpei(distribute);//保存分配方法返回的数组
			if(str[0].equals("-1")) {//-1表示未分配成功
				addMessage(redirectAttributes, str[1]);
			}else {//分配成功
				//保存分配结果
				DistributeResult distributeResult = new DistributeResult();
				distributeResult.setThesisId(str[0]);
				distributeResult.setInExpertId(str[1]);
				distributeResult.setOutExpertId(str[2]);
				distributeResult.setEmailstatus("0");
				distributeResultService.save(distributeResult);
				//修改论文状态				
				Thesis thesis = thesisService.get(str[0]);	
				thesis.setStatus("1");
				thesisService.save(thesis);
				//修改专家状态
				for(int i = 1;i<3;i++) {
					Expert expertSave = new Expert();
					expertSave = expertService.get(str[i]);
					expertSave.setDistributeNum(expertSave.getDistributeNum()+1);
					expertSave.setExpertType((i-1)+"");
					
					Major major = new Major();					
					major.setName(expertSave.getMajor());
					major = majorService.findByName(major);
					
					expertSave.setMajor(major.getId());
					expertService.save(expertSave);
				}
								
				addMessage(redirectAttributes, "分配成功");
			}						
			return "redirect:" + Global.getAdminPath() + "/gsmis/distribute/?repage";
			
		}else{			
			addMessage(redirectAttributes, "论文已分配，无法再次分配");
			return "redirect:" + Global.getAdminPath() + "/gsmis/distribute/?repage";
		}
					
	}

	// 添加批量分配
	@RequiresPermissions("gsmis:distribute:edit")
	@RequestMapping(value = "fenpeiAll")
	public String fenpeiAll(Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		String str = request.getParameter("thesisIds");
		if(str != null && !"".equals(str)) {
			String[] arr = str.split(",");
			int success = 0,failure = 0;
			for(int i = 0;i < arr.length; i++) {								
				Distribute distribute = new Distribute();
				distribute = distributeService.get(arr[i]);
				if(distribute.getStatus().equals("0")){//如果论文未分配
					
					String[] strAll = distributeService.fenpei(distribute);//保存分配方法返回的数组
					if(strAll[0].equals("-1")) {//-1表示未分配成功
						failure++;
					}else {//分配成功
						//保存分配结果
						DistributeResult distributeResult = new DistributeResult();
						distributeResult.setThesisId(strAll[0]);
						distributeResult.setInExpertId(strAll[1]);
						distributeResult.setOutExpertId(strAll[2]);
						distributeResult.setEmailstatus("0");
						distributeResultService.save(distributeResult);
						//修改论文状态				
						Thesis thesis = thesisService.get(strAll[0]);	
						thesis.setStatus("1");
						thesisService.save(thesis);
						//修改专家状态
						for(int j = 1;j<3;j++) {
							Expert expertSave = new Expert();
							expertSave = expertService.get(strAll[j]);
							expertSave.setDistributeNum(expertSave.getDistributeNum()+1);
							expertSave.setExpertType((j-1)+"");
							Major major = new Major();					
							major.setName(expertSave.getMajor());
							major = majorService.findByName(major);
							expertSave.setMajor(major.getId());
							expertService.save(expertSave);
						}
										
						success++;
					}						
					
				}else{			
					failure++;
				}
				
			}
			addMessage(redirectAttributes, success+"篇论文分配完成,"+failure+"篇论文分配失败！");
		}else {
			addMessage(redirectAttributes,"请选择要分配的论文！");
		}
		return "redirect:" + Global.getAdminPath() + "/gsmis/distribute/?repage";		
	}
	
	//添加全部分配
	@RequiresPermissions("gsmis:distribute:edit")
	@RequestMapping(value = "allFenpei")
	public String allFenpei(Model model, RedirectAttributes redirectAttributes) {
		Distribute distribute = new Distribute();
		List<Distribute> list= distributeService.findList(distribute);
		int success = 0,failure = 0;
		for(Distribute d:list) {
			if(d.getStatus().equals("0")){//如果论文未分配				
				String[] strAll = distributeService.fenpei(d);//保存分配方法返回的数组
				if(strAll[0].equals("-1")) {//-1表示未分配成功
					failure++;
				}else {//分配成功
					//保存分配结果
					DistributeResult distributeResult = new DistributeResult();
					distributeResult.setThesisId(strAll[0]);
					distributeResult.setInExpertId(strAll[1]);
					distributeResult.setOutExpertId(strAll[2]);
					distributeResult.setEmailstatus("0");
					distributeResultService.save(distributeResult);
					//修改论文状态				
					Thesis thesis = thesisService.get(strAll[0]);	
					thesis.setStatus("1");
					thesisService.save(thesis);
					//修改专家状态
					for(int j = 1;j<3;j++) {
						Expert expertSave = new Expert();
						expertSave = expertService.get(strAll[j]);
						expertSave.setDistributeNum(expertSave.getDistributeNum()+1);
						expertSave.setExpertType((j-1)+"");
						Major major = new Major();					
						major.setName(expertSave.getMajor());
						major = majorService.findByName(major);
						expertSave.setMajor(major.getId());
						expertService.save(expertSave);
					}
									
					success++;
				}						
				
			}else{			
				failure++;
			}
			
		}
		addMessage(redirectAttributes, success+"篇论文分配完成,"+failure+"篇论文分配失败！");
		return "redirect:" + Global.getAdminPath() + "/gsmis/distribute/?repage";
	}
	
	
	
}