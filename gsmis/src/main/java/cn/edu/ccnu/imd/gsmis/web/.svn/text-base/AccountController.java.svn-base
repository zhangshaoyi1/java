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
import cn.edu.ccnu.imd.gsmis.entity.Account;
import cn.edu.ccnu.imd.gsmis.service.AccountService;

/**
 * 论文信息管理Controller
 * @author hyf
 * @version 2018-08-16
 */
@Controller
@RequestMapping(value = "${adminPath}/gsmis/account")
public class AccountController extends BaseController {

	@Autowired
	private AccountService accountService;
	
	@ModelAttribute
	public Account get(@RequestParam(required=false) String id) {
		Account entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = accountService.get(id);
		}
		if (entity == null){
			entity = new Account();
		}
		return entity;
	}
	
	@RequiresPermissions("gsmis:account:view")
	@RequestMapping(value = {"list", ""})
	public String list(Account account, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Account> page = accountService.findPage(new Page<Account>(request, response), account); 
		model.addAttribute("page", page);
		return "imd/gsmis/accountList";
	}

	@RequiresPermissions("gsmis:account:view")
	@RequestMapping(value = "form")
	public String form(Account account, Model model) {
		model.addAttribute("account", account);
		return "imd/gsmis/accountForm";
	}

	@RequiresPermissions("gsmis:account:edit")
	@RequestMapping(value = "save")
	public String save(Account account, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, account)){
			return form(account, model);
		}
		accountService.save(account);
		addMessage(redirectAttributes, "保存论文信息成功");
		return "redirect:"+Global.getAdminPath()+"/gsmis/account/?repage";
	}
	
	@RequiresPermissions("gsmis:account:edit")
	@RequestMapping(value = "delete")
	public String delete(Account account, RedirectAttributes redirectAttributes) {
		accountService.delete(account);
		addMessage(redirectAttributes, "删除论文信息成功");
		return "redirect:"+Global.getAdminPath()+"/gsmis/account/?repage";
	}

}