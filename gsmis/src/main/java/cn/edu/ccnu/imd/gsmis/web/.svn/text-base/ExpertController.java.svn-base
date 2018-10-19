/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.edu.ccnu.imd.gsmis.web;

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
import com.thinkgem.jeesite.common.security.Cryptos;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;

import cn.edu.ccnu.imd.gsmis.entity.Expert;
import cn.edu.ccnu.imd.gsmis.entity.Major;
import cn.edu.ccnu.imd.gsmis.service.ExpertService;
import cn.edu.ccnu.imd.gsmis.service.MajorService;

/**
 * 专家信息管理Controller
 * @author cgq
 * @version 2018-08-13
 */
@Controller
@RequestMapping(value = "${adminPath}/gsmis/expert")
public class ExpertController extends BaseController {

	@Autowired
	private MajorService majorService;
	
	@Autowired
	private ExpertService expertService;
	
	@ModelAttribute
	public Expert get(@RequestParam(required=false) String id) {
		Expert entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = expertService.get(id);
		}
		if (entity == null){
			entity = new Expert();
		}
		return entity;
	}
	
	@RequiresPermissions("gsmis:expert:view")
	@RequestMapping(value = {"list", ""})
	public String list(Expert expert, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Expert> page = expertService.findPage(new Page<Expert>(request, response), expert); 
		model.addAttribute("page", page);
		return "imd/gsmis/expertList";
	}

	@RequiresPermissions("gsmis:expert:view")
	@RequestMapping(value = "form")
	public String form(Expert expert, Model model) {
		Major major = new Major();
		List<Major> majorname = majorService.findList(major);
		model.addAttribute("majorname", majorname);
		model.addAttribute("expert", expert);
		return "imd/gsmis/expertForm";
	}

	@RequiresPermissions("gsmis:expert:edit")
	@RequestMapping(value = "save")
	public String save(Expert expert, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, expert)){
			return form(expert, model);
		}
		
		expertService.save(expert);
		addMessage(redirectAttributes, "保存专家信息成功");
		return "redirect:"+Global.getAdminPath()+"/gsmis/expert/?repage";
	}
	
	@RequiresPermissions("gsmis:expert:edit")
	@RequestMapping(value = "delete")
	public String delete(Expert expert, RedirectAttributes redirectAttributes) {
		expertService.delete(expert);
		addMessage(redirectAttributes, "删除专家信息成功");
		return "redirect:"+Global.getAdminPath()+"/gsmis/expert/?repage";
	}
	
	/**
	 * 导出专家信息数据
	 * @param gsmis
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("gsmis:expert:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(Expert expert, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "专家信息数据"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<Expert> page = expertService.findPage(new Page<Expert>(request, response, -1), expert);
    		new ExportExcel("专家信息数据", Expert.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出专家信息失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/gsmis/expert/?repage";
    }
	
    /**
	 * 导入专家信息数据
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("gsmis:expert:edit")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/gsmis/expert/?repage";
		}
		try {
			int successNum = 0;
			int failureNum = 0;
			//定义failureMsg失败信息的字符串
			StringBuilder failureMsg = new StringBuilder();
			//定义文件接收对象，存储导入的文件信息
			ImportExcel ei = new ImportExcel(file, 1, 0);
			//将导入的文件的每一条数据取出放入链表
			List<Expert> list = ei.getDataList(Expert.class);
			//读取专业表，匹配专业名，把相应的id存入数据库
			List<Major> majors = Lists.newArrayList();
			//Major major;			
			for (Expert expert : list){
				try{	
					//遍历专业表，把相应的id存入数据库专家表
					for(Major m : majors) {
						if(m.getName()==expert.getMajor())
							expert.setMajor(m.getId());
					}
					expert.setExpertname(Cryptos.aesEncrypt(expert.getExpertname()));
					expert.setEmail(Cryptos.aesEncrypt(expert.getEmail()));
					expert.setPhone(Cryptos.aesEncrypt(expert.getPhone()));
					expert.setBankcardnumber(Cryptos.aesEncrypt(expert.getBankcardnumber()));
					//将导入的专家类型转换为字典的值
					@SuppressWarnings("unused")
					String expertType = DictUtils.getDictValue(expert.getExpertType(), "expertType", "");
					expertService.save(expert);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureMsg.append("<br/>姓名 "+expert.getExpertname()+" 导入失败：");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");  //告诉你出错原因
					for (String message : messageList){
						failureMsg.append(message+"; ");
						failureNum++;
					}
				}catch (Exception ex) {
					failureMsg.append("<br/>姓名 "+expert.getExpertname()+" 导入失败："+ex.getMessage());
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条信息，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条信息"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入专家信息失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/gsmis/expert/list?repage";
    }
	
	/**
	 * 下载导入专家信息模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("gsmis:expert:view")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "专家信息导入模板.xlsx";
    		List<Expert> list = Lists.newArrayList(); 
    		Expert expert = new Expert();
    		expert.setExpertname("李四");
    		expert.setMajor("电子商务");
    		expert.setResearchname("电子商务");
    		expert.setDistributeNum(0);    		
    		expert.setExpertType("校内");
    		expert.setEmail("2854616@qq.com");
    		expert.setPhone("18978395032");
    		expert.setBankcardnumber("6222600810010710887");
    		expert.setPlace("武汉大学");
    		list.add(expert);
    		new ExportExcel("专家信息模板", Expert.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/gsmis/expert/list?repage";
    }

}