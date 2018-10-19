/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.edu.ccnu.imd.jwcmis.web;

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
import cn.edu.ccnu.imd.jwcmis.entity.DistributeResult;
import cn.edu.ccnu.imd.jwcmis.entity.Expert;
import cn.edu.ccnu.imd.jwcmis.entity.Major;
import cn.edu.ccnu.imd.jwcmis.entity.Thesis;
import cn.edu.ccnu.imd.jwcmis.service.DistributeResultService;
import cn.edu.ccnu.imd.jwcmis.service.ExpertService;
import cn.edu.ccnu.imd.jwcmis.service.MajorService;
import cn.edu.ccnu.imd.jwcmis.service.ThesisService;

/**
 * 分配结果管理Controller
 * @author zjy
 * @version 2018-08-16
 */
@Controller
@RequestMapping(value = "${adminPath}/jwcmis/distributeResult")
public class DistributeResultController extends BaseController {

	@Autowired
	private DistributeResultService distributeResultService;
	@Autowired
	private ThesisService thesisService ;
	@Autowired
	private ExpertService expertService;
	@Autowired
	private MajorService majorService;
	
	@ModelAttribute
	public DistributeResult get(@RequestParam(required=false) String id) {
		DistributeResult entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = distributeResultService.get(id);
		}
		if (entity == null){
			entity = new DistributeResult();
		}
		return entity;
	}

	
	@RequiresPermissions("jwcmis:distributeResult:view")
	@RequestMapping(value = {"list", ""})
	public String list(DistributeResult distributeResult, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DistributeResult> page = distributeResultService.findPage(new Page<DistributeResult>(request, response), distributeResult); 
		model.addAttribute("page", page);
		List<DistributeResult> distributeResultList = new ArrayList<DistributeResult>();
		distributeResultList = distributeResultService.findList(distributeResult);
		model.addAttribute("distributeResultList", distributeResultList);
		return "imd/jwcmis/distributeResultList";
	}

	@RequiresPermissions("jwcmis:distributeResult:view")
	@RequestMapping(value = "form")
	public String form(DistributeResult distributeResult, Model model) {
		model.addAttribute("distributeResult", distributeResult);
		return "imd/jwcmis/distributeResultForm";
	}

	@RequiresPermissions("jwcmis:distributeResult:edit")
	@RequestMapping(value = "save")
	public String save(DistributeResult distributeResult, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, distributeResult)){
			return form(distributeResult, model);
		}
		distributeResultService.save(distributeResult);
		addMessage(redirectAttributes, "保存分配结果成功");
		return "redirect:"+Global.getAdminPath()+"/jwcmis/distributeResult/?repage";
	}
	
	@RequiresPermissions("jwcmis:distributeResult:edit")
	@RequestMapping(value = "delete")
	//删除论文分配结果时，论文状态更改为未分配，对应两位专家论文数-1
	public String delete(DistributeResult distributeResult, RedirectAttributes redirectAttributes){				
		//如果邮件已经发送，就不能删除
		if(distributeResult.getEmailstatus().equals("1")) {
			addMessage(redirectAttributes, "邮件已经发送，不能删除");
			return "redirect:"+Global.getAdminPath()+"/jwcmis/distributeResult/?repage";
		}
		//修改论文状态			
		
		Thesis thesis = thesisService.get(distributeResult.getThesisId());	
		thesis.setStatus("0");
		thesisService.save(thesis);
		//修改专家状态
		//校内专家
		Expert expertChange = new Expert();
		expertChange = expertService.get(distributeResult.getInExpertId());
		expertChange.setDistributeNum(expertChange.getDistributeNum()-1);
		expertChange.setExpertType("0");
		Major major = new Major();					
		major.setName(expertChange.getMajor());
		for(Major m : majorService.findList(major)){ 
			expertChange.setMajor(m.getId());
		}			
		expertService.save(expertChange);
		//校外专家
		expertChange = expertService.get(distributeResult.getOutExpertId());
		expertChange.setDistributeNum(expertChange.getDistributeNum()-1);
		expertChange.setExpertType("1");				
		major.setName(expertChange.getMajor());
		for(Major m : majorService.findList(major)){
			expertChange.setMajor(m.getId());
		}			
		expertService.save(expertChange);
		//删除分配消息		
		distributeResultService.delete(distributeResult);
		addMessage(redirectAttributes, "删除分配结果成功");
		return "redirect:"+Global.getAdminPath()+"/jwcmis/distributeResult/?repage";
	}
	
}