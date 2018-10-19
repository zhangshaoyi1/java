/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.edu.ccnu.imd.gsmis.web;

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
import cn.edu.ccnu.imd.gsmis.entity.Email;
import cn.edu.ccnu.imd.gsmis.service.EmailService;

/**
 * 邮件模板管理Controller
 * @author cgq
 * @version 2018-08-14
 */
@Controller
@RequestMapping(value = "${adminPath}/gsmis/email")
public class EmailController extends BaseController {

	@Autowired
	private EmailService emailService;
	
	@ModelAttribute
	public Email get(@RequestParam(required=false) String id) {
		Email entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = emailService.get(id);
		}
		if (entity == null){
			entity = new Email();
		}
		return entity;
	}
	
	@RequiresPermissions("gsmis:email:view")
	@RequestMapping(value = {"list", ""})
	public String list(Email email, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Email> page = emailService.findPage(new Page<Email>(request, response), email); 
		model.addAttribute("page", page);
		return "imd/gsmis/emailList";
	}

	@RequiresPermissions("gsmis:email:view")
	@RequestMapping(value = "form")
	public String form(Email email, Model model) {
		model.addAttribute("email", email);
		return "imd/gsmis/emailForm";
	}

	@RequiresPermissions("gsmis:email:edit")
	@RequestMapping(value = "save")
	public String save(Email email, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, email)){
			return form(email, model);
		}
		emailService.save(email);
		addMessage(redirectAttributes, "保存邮件模板成功");
		return "redirect:"+Global.getAdminPath()+"/gsmis/email/?repage";
	}
	
	@RequiresPermissions("gsmis:email:edit")
	@RequestMapping(value = "delete")
	public String delete(Email email, RedirectAttributes redirectAttributes) {
		emailService.delete(email);
		addMessage(redirectAttributes, "删除邮件模板成功");
		return "redirect:"+Global.getAdminPath()+"/gsmis/email/?repage";
	}

}