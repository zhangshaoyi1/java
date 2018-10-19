/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.edu.ccnu.imd.jwcmis.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
import org.springframework.web.bind.annotation.ResponseBody;
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

import cn.edu.ccnu.imd.jwcmis.entity.Major;
import cn.edu.ccnu.imd.jwcmis.service.MajorService;

/**
 * 专业信息管理Controller
 * @author cgq
 * @version 2018-08-13
 */
@Controller
@RequestMapping(value = "${adminPath}/jwcmis/major")
public class MajorController extends BaseController {

	@Autowired
	private MajorService majorService;
	
	@ModelAttribute
	public Major get(@RequestParam(required=false) String id) {
		Major entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = majorService.get(id);
		}
		if (entity == null){
			entity = new Major();
		}
		return entity;
	}
	
	@RequiresPermissions("jwcmis:major:view")
	@RequestMapping(value = {"list", ""})
	public String list(Major major, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Major> page = majorService.findPage(new Page<Major>(request, response), major); 
		model.addAttribute("page", page);
		return "imd/jwcmis/majorList";
	}

	@RequiresPermissions("jwcmis:major:view")
	@RequestMapping(value = "form")
	public String form(Major major, Model model) {
		model.addAttribute("major", major);
		return "imd/jwcmis/majorForm";
	}

	@RequiresPermissions("jwcmis:major:edit")
	@RequestMapping(value = "save")
	public String save(Major major, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, major)){
			return form(major, model);
		}
		majorService.save(major);
		addMessage(redirectAttributes, "已成功保存专业“ " + major.getName() +  " ”的信息！");
		return "redirect:"+Global.getAdminPath()+"/jwcmis/major/?repage";
	}
	
	//手动添加major查重
	@ResponseBody
	@RequiresPermissions("jwcmis:major:edit")
	@RequestMapping(value = "checkMajorName")
	public String checkMajorName(String majorName) throws UnsupportedEncodingException{
		String result = "0";
		majorName = URLDecoder.decode(majorName,"UTF-8"); 
		//---由于数据中对major的xml中对name是模糊匹配的，为防止列表数据过多，先进行筛选，经过模糊匹配后再进行查重
		Major majorMatch = new Major();
		majorMatch.setName(majorName);
		List<Major> list = majorService.findList(majorMatch);
		for(Major m:list) {
			if(majorName.equals(m.getName())) {
				result = "1";
				break;
			}
		}
		return result;
	}
	
	@RequiresPermissions("jwcmis:major:edit")
	@RequestMapping(value = "delete")
	public String delete(Major major, RedirectAttributes redirectAttributes) {
		majorService.delete(major);
		addMessage(redirectAttributes, "删除专业信息成功");
		return "redirect:"+Global.getAdminPath()+"/jwcmis/major/?repage";
	}
	
	/**
	 * 导出专业信息数据
	 * @param test
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("jwcmis:major:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(Major major, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "专业信息数据"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<Major> page = majorService.findPage(new Page<Major>(request, response, -1), major);
    		new ExportExcel("专业信息", Major.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出专业信息失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/jwcmis/major/?repage";
    }
	
	 /**
	 * 导入专业信息数据
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("jwcmis:major:edit")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/jwcmis/major/?repage";
		}
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<Major> list = ei.getDataList(Major.class);
			for (Major mNew : list){                
				try{
					if(majorService.importCheckRepeat(mNew)) {
						majorService.save(mNew);
						successNum++;
					}else {
						failureMsg.append("<br/>专业名称: "+mNew.getName()+
								"<pre style='display:inline; background-color:rgba(0,0,0,0); border:0;'>				</pre>原因： 数据重复，导入失败！");
						failureNum++;
					}
				}catch(ConstraintViolationException ex){
					failureMsg.append("<br/>专业名称: "+mNew.getName()+" 导入失败：");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");  //告诉你出错原因
					for (String message : messageList){
						failureMsg.append(message+"; ");
						failureNum++;
					}
				}catch (Exception ex) {
					failureMsg.append("<br/>专业名称: "+mNew.getName()+" 导入失败："+ex.getMessage());
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条信息，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条信息"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入专业信息失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/jwcmis/major/list?repage";
    }
	
	/**
	 * 下载导入专业数据模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("jwcmis:major:view")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "专业信息导入模板.xlsx";
    		List<Major> list = Lists.newArrayList(); 
    		Major major = new Major();
    		major.setName("电子商务专业");
    		list.add(major);
    		new ExportExcel("专业信息", Major.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/jwcmis/major/list?repage";
    }
}