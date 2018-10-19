/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.edu.ccnu.imd.jwcmis.web;

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
import cn.edu.ccnu.imd.jwcmis.entity.ReviewResult;
import cn.edu.ccnu.imd.jwcmis.service.ReviewResultService;

/**
 * 评审结果管理Controller
 * @author 黑白
 * @version 2018-09-23
 */
@Controller
@RequestMapping(value = "${adminPath}/jwcmis/reviewResult")
public class ReviewResultController extends BaseController {

	@Autowired
	private ReviewResultService reviewResultService;
	
	@ModelAttribute
	public ReviewResult get(@RequestParam(required=false) String id) {
		ReviewResult entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = reviewResultService.get(id);
		}
		if (entity == null){
			entity = new ReviewResult();
		}
		return entity;
	}
	
	@RequiresPermissions("jwcmis:reviewResult:view")
	@RequestMapping(value = {"list", ""})
	public String list(ReviewResult reviewResult, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ReviewResult> page = reviewResultService.findPage(new Page<ReviewResult>(request, response), reviewResult); 
		model.addAttribute("page", page);
		return "imd/jwcmis/reviewResultList";
	}

	@RequiresPermissions("jwcmis:reviewResult:view")
	@RequestMapping(value = "form")
	public String form(ReviewResult reviewResult, Model model) {
		model.addAttribute("reviewResult", reviewResult);
		return "imd/jwcmis/reviewResultForm";
	}

	@RequiresPermissions("jwcmis:reviewResult:edit")
	@RequestMapping(value = "save")
	public String save(ReviewResult reviewResult, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, reviewResult)){
			return form(reviewResult, model);
		}
		reviewResultService.save(reviewResult);
		addMessage(redirectAttributes, "保存评审结果成功");
		return "redirect:"+Global.getAdminPath()+"/jwcmis/reviewResult/?repage";
	}
	
	@RequiresPermissions("jwcmis:reviewResult:edit")
	@RequestMapping(value = "delete")
	public String delete(ReviewResult reviewResult, RedirectAttributes redirectAttributes) {
		reviewResultService.delete(reviewResult);
		addMessage(redirectAttributes, "删除评审结果成功");
		return "redirect:"+Global.getAdminPath()+"/jwcmis/reviewResult/?repage";
	}

}