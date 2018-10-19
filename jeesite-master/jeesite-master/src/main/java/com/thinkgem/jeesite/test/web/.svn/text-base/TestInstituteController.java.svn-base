/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.test.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.beanvalidator.BeanValidators;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.test.entity.TestInstitute;
import com.thinkgem.jeesite.test.entity.TestMajor;
import com.thinkgem.jeesite.test.service.TestInstituteService;

/**
 * 学院信息管理Controller
 * @author 张绍燚
 * @version 2018-07-16
 */
@Controller
@RequestMapping(value = "${adminPath}/test/testInstitute")
public class TestInstituteController extends BaseController {

	@Autowired
	private TestInstituteService testInstituteService;
	
	@ModelAttribute
	public TestInstitute get(@RequestParam(required=false) String id) {
		TestInstitute entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = testInstituteService.get(id);
		}
		if (entity == null){
			entity = new TestInstitute();
		}
		return entity;
	}
	
	@RequiresPermissions("test:testInstitute:view")
	@RequestMapping(value = {"list", ""})
	public String list(TestInstitute testInstitute, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TestInstitute> page = testInstituteService.findPage(new Page<TestInstitute>(request, response), testInstitute); 
		model.addAttribute("page", page);
		return "jeesite/test/testInstituteList";
	}

	@RequiresPermissions("test:testInstitute:view")
	@RequestMapping(value = "form")
	public String form(TestInstitute testInstitute, Model model) {
		model.addAttribute("testInstitute", testInstitute);
		return "jeesite/test/testInstituteForm";
	}

	@RequiresPermissions("test:testInstitute:edit")
	@RequestMapping(value = "save")
	public String save(TestInstitute testInstitute, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, testInstitute)){
			return form(testInstitute, model);
		}
		testInstituteService.save(testInstitute);
		addMessage(redirectAttributes, "保存学院管理成功");
		return "redirect:"+Global.getAdminPath()+"/test/testInstitute/?repage";
	}
	
	@RequiresPermissions("test:testInstitute:edit")
	@RequestMapping(value = "delete")
	public String delete(TestInstitute testInstitute, RedirectAttributes redirectAttributes) {
		testInstituteService.delete(testInstitute);
		addMessage(redirectAttributes, "删除学院管理成功");
		return "redirect:"+Global.getAdminPath()+"/test/testInstitute/?repage";
		//Global表示当前对象实例，getAdminPath()获取管理端根路径
	}
	/**
	 * 导出用户数据
	 * @param user
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("test:testInstitute:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(TestInstitute testInstitute, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "学院数据"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            //三个参数的作用：request 传递 repage 参数，来记住页码。response 用于设置 Cookie，记住页码；
            //defaultPageSize 默认分页大小，如果传递 -1 则为不分页，返回所有数据
            Page<TestInstitute> page = testInstituteService.findPage(new Page<TestInstitute>(request, response, -1), testInstitute);
    		new ExportExcel("会议数据", TestInstitute.class).setDataList(page.getList()).write(response, fileName).dispose();
    		//不管他，直接复制代码
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出会议信息失败！失败信息："+e.getMessage());
		}
		//返回list
		return "redirect:" + adminPath + "/test/testInstitute/list?repage";
    }
	/**
	 * 导入用户数据
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("test:testInstitute:edit")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/test/testInstitute/list?repage";
		}
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<TestInstitute> list = ei.getDataList(TestInstitute.class); //从Excel表里加载数据，右边是数据库
			for (TestInstitute testInstitute : list){                //
				try{
					testInstituteService.save(testInstitute);
						successNum++;
				}catch(ConstraintViolationException ex){
					failureMsg.append("<br/>姓名 "+testInstitute.getName()+" 导入失败：");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");  //告诉你出错原因
					for (String message : messageList){
						failureMsg.append(message+"; ");
						failureNum++;
					}
				}catch (Exception ex) {
					failureMsg.append("<br/>登录名 "+testInstitute.getName()+" 导入失败："+ex.getMessage());
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条用户，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条用户"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入用户失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/test/testInstitute/list?repage";
    }
	
	/**
	 * 下载导入用户数据模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("test:testInstitute:view")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "学院信息模板.xlsx";
    		List<TestInstitute> list = Lists.newArrayList(); 
    		TestInstitute testInstitute = new TestInstitute();
    		testInstitute.setName("信息管理学院");
    		testInstitute.setXydm("1");
    		testInstitute.setMemo("1");
    		list.add(testInstitute);
    		new ExportExcel("学院信息", TestInstitute.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/test/testInstitute/list?repage";
    }


}