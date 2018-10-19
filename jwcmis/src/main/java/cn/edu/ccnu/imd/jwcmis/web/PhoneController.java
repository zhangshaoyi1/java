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
import cn.edu.ccnu.imd.jwcmis.entity.Phone;
import cn.edu.ccnu.imd.jwcmis.service.PhoneService;

/**
 * 短信模板管理Controller
 * @author cgq
 * @version 2018-08-13
 */
@Controller
@RequestMapping(value = "${adminPath}/jwcmis/phone")
public class PhoneController extends BaseController {

	@Autowired
	private PhoneService phoneService;
	
	@ModelAttribute
	public Phone get(@RequestParam(required=false) String id) {
		Phone entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = phoneService.get(id);
		}
		if (entity == null){
			entity = new Phone();
		}
		return entity;
	}
	
	@RequiresPermissions("jwcmis:phone:view")
	@RequestMapping(value = {"list", ""})
	public String list(Phone phone, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Phone> page = phoneService.findPage(new Page<Phone>(request, response), phone); 
		model.addAttribute("page", page);
		return "imd/jwcmis/phoneList";
	}

	@RequiresPermissions("jwcmis:phone:view")
	@RequestMapping(value = "form")
	public String form(Phone phone, Model model) {
		model.addAttribute("phone", phone);
		if(phone.getText()==null || "".equals(phone.getText())) {
			phone.setText("<p>\n" + 
					"	【论文审批提醒】尊敬的<strong><span style=\"color:#ff0000;\">{1}</span></strong>您好，<span style=\"text-indent: 20pt;\">您所审批的论文z账号为</span><span style=\"text-indent: 20pt; color: rgb(255, 0, 0);\">{2}</span><span style=\"text-indent: 20pt;\">，密码为</span><span style=\"text-indent: 20pt; color: rgb(255, 0, 0);\">{3}</span><span style=\"text-indent: 20pt;\">，请尽快点击网址</span><span style=\"text-indent: 20pt; color: rgb(255, 0, 0);\">{4}</span><span style=\"text-indent: 20pt;\">进行审批。【华中师范大学】</span></p>\n" + 
					"");
		}
		
		return "imd/jwcmis/phoneForm";
	}

	@RequiresPermissions("jwcmis:phone:edit")
	@RequestMapping(value = "save")
	public String save(Phone phone, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, phone)){
			return form(phone, model);
		}
		phoneService.save(phone);
		addMessage(redirectAttributes, "保存短信模板成功");
		return "redirect:"+Global.getAdminPath()+"/jwcmis/phone/?repage";
	}
	
	@RequiresPermissions("jwcmis:phone:edit")
	@RequestMapping(value = "delete")
	public String delete(Phone phone, RedirectAttributes redirectAttributes) {
		phoneService.delete(phone);
		addMessage(redirectAttributes, "删除短信模板成功");
		return "redirect:"+Global.getAdminPath()+"/jwcmis/phone/?repage";
	}

}