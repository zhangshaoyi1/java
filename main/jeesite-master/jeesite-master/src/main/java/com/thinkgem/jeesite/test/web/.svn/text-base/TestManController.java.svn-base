/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.test.web;

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
import com.thinkgem.jeesite.test.entity.TestMan;
import com.thinkgem.jeesite.test.service.TestManService;

/**
 * 人员信息管理Controller
 * @author 张绍燚
 * @version 2018-07-16
 */
@Controller
@RequestMapping(value = "${adminPath}/test/testMan")
public class TestManController extends BaseController {

	@Autowired
	private TestManService testManService;
	
	@ModelAttribute
	public TestMan get(@RequestParam(required=false) String id) {
		TestMan entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = testManService.get(id);
		}
		if (entity == null){
			entity = new TestMan();
		}
		return entity;
	}
	
	@RequiresPermissions("test:testMan:view")
	@RequestMapping(value = {"list", ""})
	public String list(TestMan testMan, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TestMan> page = testManService.findPage(new Page<TestMan>(request, response), testMan); 
		model.addAttribute("page", page);
		return "jeesite/test/testManList";
	}

	@RequiresPermissions("test:testMan:view")
	@RequestMapping(value = "form")
	public String form(TestMan testMan, Model model) {
		model.addAttribute("testMan", testMan);
		return "jeesite/test/testManForm";
	}

	@RequiresPermissions("test:testMan:edit")
	@RequestMapping(value = "save")
	public String save(TestMan testMan, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, testMan)){
			return form(testMan, model);
		}
		testManService.save(testMan);
		addMessage(redirectAttributes, "保存人员信息成功");
		return "redirect:"+Global.getAdminPath()+"/test/testMan/?repage";
	}
	
	@RequiresPermissions("test:testMan:edit")
	@RequestMapping(value = "delete")
	public String delete(TestMan testMan, RedirectAttributes redirectAttributes) {
		testManService.delete(testMan);
		addMessage(redirectAttributes, "删除人员信息成功");
		return "redirect:"+Global.getAdminPath()+"/test/testMan/?repage";
	}

}