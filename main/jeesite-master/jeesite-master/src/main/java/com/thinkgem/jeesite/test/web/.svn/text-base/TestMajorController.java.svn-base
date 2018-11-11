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
import com.thinkgem.jeesite.test.service.TestMajorService;

/**
 * 专业信息管理Controller
 * @author 张绍燚
 * @version 2018-07-16
 */
@Controller
@RequestMapping(value = "${adminPath}/test/testMajor")
public class TestMajorController extends BaseController {

	@Autowired
	private TestMajorService testMajorService;
	@Autowired
	private TestInstituteService testInstituteService;
	
	@ModelAttribute
	public TestMajor get(@RequestParam(required=false) String id) {
		TestMajor entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = testMajorService.get(id);
		}
		if (entity == null){
			entity = new TestMajor();
		}
		return entity;
	}
	
	@RequiresPermissions("test:testMajor:view")
	@RequestMapping(value = {"list", ""})
	public String list(TestMajor testMajor, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TestMajor> page = testMajorService.findPage(new Page<TestMajor>(request, response), testMajor); 
		model.addAttribute("page", page);
		return "jeesite/test/testMajorList";
	}

	@RequiresPermissions("test:testMajor:view")
	@RequestMapping(value = "form")
	public String form(TestMajor testMajor, Model model) {
		TestInstitute testInstitute = new TestInstitute();
		List<TestInstitute> Institute=testInstituteService.findList(testInstitute);
		model.addAttribute("Institute", Institute);
		model.addAttribute("testMajor", testMajor);
		return "jeesite/test/testMajorForm";
	}

	@RequiresPermissions("test:testMajor:edit")
	@RequestMapping(value = "save")
	public String save(TestMajor testMajor, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, testMajor)){
			return form(testMajor, model);
		}
		testMajorService.save(testMajor);
		addMessage(redirectAttributes, "保存专业管理成功");
		return "redirect:"+Global.getAdminPath()+"/test/testMajor/?repage";
	}
	
	@RequiresPermissions("test:testMajor:edit")
	@RequestMapping(value = "delete")
	public String delete(TestMajor testMajor, RedirectAttributes redirectAttributes) {
		testMajorService.delete(testMajor);
		addMessage(redirectAttributes, "删除专业管理成功");
		return "redirect:"+Global.getAdminPath()+"/test/testMajor/?repage";
	}
	
	/**
	 * 导出用户数据
	 * @param user
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("test:testMajor:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(TestMajor testMajor, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "专业数据"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            //三个参数的作用：request 传递 repage 参数，来记住页码。response 用于设置 Cookie，记住页码；
            //defaultPageSize 默认分页大小，如果传递 -1 则为不分页，返回所有数据
            Page<TestMajor> page = testMajorService.findPage(new Page<TestMajor>(request, response, -1), testMajor);
    		new ExportExcel("会议数据", TestMajor.class).setDataList(page.getList()).write(response, fileName).dispose();
    		//不管他，直接复制代码
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出会议信息失败！失败信息："+e.getMessage());
		}
		//返回list
		return "redirect:" + adminPath + "/test/testMajor/list?repage";
    }
	/**
	 * 导入用户数据
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("test:testMajor:edit")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/test/testMajor/list?repage";
		}
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<TestMajor> list = ei.getDataList(TestMajor.class); //从Excel表里加载数据，右边是数据库
			for (TestMajor testMajor : list){                //
				try{
						testMajorService.save(testMajor);
						successNum++;
				}catch(ConstraintViolationException ex){
					failureMsg.append("<br/>姓名 "+testMajor.getName()+" 导入失败：");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");  //告诉你出错原因
					for (String message : messageList){
						failureMsg.append(message+"; ");
						failureNum++;
					}
				}catch (Exception ex) {
					failureMsg.append("<br/>登录名 "+testMajor.getName()+" 导入失败："+ex.getMessage());
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条用户，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条用户"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入用户失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/test/testMajor/list?repage";
    }
	
	/**
	 * 下载导入用户数据模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("test:testMajor:view")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "专业信息模板.xlsx";
    		List<TestMajor> list = Lists.newArrayList(); 
    		TestMajor testMajor = new TestMajor();
    		testMajor.setSchoolname("信息管理学院");
    		testMajor.setName("信息管理与信息系统");
    		testMajor.setZydm("1");
    		testMajor.setSchoolXydm("1");
    		testMajor.setZyxz("1");
    		testMajor.setSfxz("2");
    		testMajor.setStatus("1");
    		list.add(testMajor);
    		new ExportExcel("专业信息", TestMajor.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/test/testMajor/list?repage";
    }


}