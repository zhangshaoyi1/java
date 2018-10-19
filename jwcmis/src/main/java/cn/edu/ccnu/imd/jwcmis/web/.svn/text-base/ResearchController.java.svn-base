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
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;

import cn.edu.ccnu.imd.jwcmis.entity.Research;
import cn.edu.ccnu.imd.jwcmis.service.ResearchService;

/**
 * 研究方向管理Controller
 * @author cgq
 * @version 2018-08-23
 */
@Controller
@RequestMapping(value = "${adminPath}/jwcmis/research")
public class ResearchController extends BaseController {

	@Autowired
	private ResearchService researchService;
	
	@ModelAttribute
	public Research get(@RequestParam(required=false) String id) {
		Research entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = researchService.get(id);
		}
		if (entity == null){
			entity = new Research();
		}
		return entity;
	}
	
	@RequiresPermissions("jwcmis:research:view")
	@RequestMapping(value = {"list", ""})
	public String list(Research research, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Research> page = researchService.findPage(new Page<Research>(request, response), research); 
		model.addAttribute("page", page);
		return "imd/jwcmis/researchList";
	}

	@RequiresPermissions("jwcmis:research:view")
	@RequestMapping(value = "form")
	public String form(Research research, Model model) {
		model.addAttribute("research", research);
		return "imd/jwcmis/researchForm";
	}

	@RequiresPermissions("jwcmis:research:edit")
	@RequestMapping(value = "save")
	public String save(Research research, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, research)){
			return form(research, model);
		}
		researchService.save(research);
		addMessage(redirectAttributes, "已成功保存研究方向“ " + research.getName() + " ”的信息！");
		return "redirect:"+Global.getAdminPath()+"/jwcmis/research/?repage";
	}
	
	//手动添加research查重
	@ResponseBody
	@RequiresPermissions("jwcmis:research:edit")
	@RequestMapping(value = "checkResearchName")
	public String checkResearchName(String researchName) throws UnsupportedEncodingException{
		String result = "0";
		researchName = URLDecoder.decode(researchName,"UTF-8"); 
		Research researchMatch = new Research();
		//---由于数据中对research的xml中对name是模糊匹配的，为防止列表数据过多，先进行筛选，经过模糊匹配后再进行查重
		researchMatch.setName(researchName);
		List<Research> list = researchService.findList(researchMatch);
		for(Research r:list) {
			if(researchName.equals(r.getName())) {
				result = "1";
				break;
			}
		}
		return result;
	}
	
	@RequiresPermissions("jwcmis:research:edit")
	@RequestMapping(value = "delete")
	public String delete(Research research, RedirectAttributes redirectAttributes) {
		researchService.delete(research);
		addMessage(redirectAttributes, "删除研究方向信息成功");
		return "redirect:"+Global.getAdminPath()+"/jwcmis/research/?repage";
	}
	
	/**
	 * 研究方向选择列表
	 */
	@RequiresPermissions("jwcmis:research:view")
	@RequestMapping(value = "selectList")
	public String selectList(Research research, HttpServletRequest request, HttpServletResponse response, Model model) {
        list(research, request, response, model);
		return "imd/jwcmis/researchSelectList";
	}
		
	/**
	 * 通过编号获取研究方向标题
	 */
	@RequiresPermissions("jwcmis:research:view")
	@ResponseBody
	@RequestMapping(value = "findByIds")
	public String findByIds(String ids) {
		List<Object[]> list = researchService.findByIds(ids);
		return JsonMapper.nonDefaultMapper().toJson(list);
	}	
	/**
	 * 导出研究方向信息数据
	 * @param test
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("jwcmis:research:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(Research research, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "研究方向信息数据"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<Research> page = researchService.findPage(new Page<Research>(request, response, -1), research);
    		new ExportExcel("研究方向信息", Research.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出研究方向信息失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/jwcmis/research/?repage";
	}
	
	/**
	 * 导入研究方向信息数据
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("jwcmis:research:edit")
	@RequestMapping(value = "import", method=RequestMethod.POST)
	public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/jwcmis/research/?repage";
		}
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<Research> list = ei.getDataList(Research.class);
			for (Research rNew : list){                
				try{
					if(researchService.importCheckRepeat(rNew)) {
						researchService.save(rNew);
						successNum++;
					}else {
						failureMsg.append("<br/>研究方向: " + rNew.getName()+
								"<pre style='display:inline; background-color:rgba(0,0,0,0); border:0;'>			</pre>原因： 数据重复，导入失败！");
						failureNum++;
					}
				}catch(ConstraintViolationException ex){
					failureMsg.append("<br/>研究方向: " + rNew.getName()+" 导入失败：");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");  //告诉你出错原因
					for (String message : messageList){
						failureMsg.append(message+"; ");
						failureNum++;
					}
				}catch (Exception ex) {
					failureMsg.append("<br/>研究方向: "+rNew.getName()+" 导入失败："+ex.getMessage());
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条信息，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条信息"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入研究方向信息失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/jwcmis/research/list?repage";
	}

/**
	 * 下载导入研究方向数据模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("jwcmis:research:view")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "研究方向信息导入模板.xlsx";
            List<Research> list= Lists.newArrayList(); 
    		Research research = new Research();
    		research.setName("信息组织与信息检索");
    		list.add(research);
    		new ExportExcel("研究方向信息", Research.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/jwcmis/research/list?repage";
	}
}