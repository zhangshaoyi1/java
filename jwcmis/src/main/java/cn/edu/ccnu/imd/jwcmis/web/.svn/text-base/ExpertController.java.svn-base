/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.edu.ccnu.imd.jwcmis.web;

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
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;

import cn.edu.ccnu.imd.jwcmis.entity.Expert;
import cn.edu.ccnu.imd.jwcmis.entity.Major;
import cn.edu.ccnu.imd.jwcmis.service.ExpertService;
import cn.edu.ccnu.imd.jwcmis.service.MajorService;

/**
 * 专家信息管理Controller
 * @author cgq
 * @version 2018-08-13
 */
@Controller
@RequestMapping(value = "${adminPath}/jwcmis/expert")
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
	
	@RequiresPermissions("jwcmis:expert:view")
	@RequestMapping(value = {"list", ""})
	public String list(Expert expert, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Expert> page = expertService.findPage(new Page<Expert>(request, response), expert); 
		model.addAttribute("page", page);
		return "imd/jwcmis/expertList";
	}

	@RequiresPermissions("jwcmis:expert:view")
	@RequestMapping(value = "form")
	public String form(Expert expert, Model model) {
		Major major = new Major();
		List<Major> majorname = majorService.findList(major);
		model.addAttribute("majorname", majorname);
		expert.setExpertType("0");
		model.addAttribute("expert", expert);
		return "imd/jwcmis/expertForm";
	}

	@RequiresPermissions("jwcmis:expert:edit")
	@RequestMapping(value = "save")
	public String save(Expert expert, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, expert)){
			return form(expert, model);
		}
		expertService.save(expert);
		expertService.decryption(expert);
		addMessage(redirectAttributes, "已成功保存专家“ " + expert.getExpertName() +" ”的信息！");
		return "redirect:"+Global.getAdminPath()+"/jwcmis/expert/?repage";
	}
	
	@RequiresPermissions("jwcmis:expert:edit")
	@RequestMapping(value = "delete")
	public String delete(Expert expert, RedirectAttributes redirectAttributes) {
		expertService.delete(expert);
		addMessage(redirectAttributes, "删除专家" + expert.getExpertName() + "信息成功!");
		return "redirect:"+Global.getAdminPath()+"/jwcmis/expert/?repage";
	}
	
	//checkRepeat邮箱、手机号、银行卡号查重
	@ResponseBody  
	@RequiresPermissions("jwcmis:expert:edit")
	@RequestMapping(value = "checkRepeat")
	public String checkRepeat(String checkString) {
		Expert expert = new Expert();
		String result = "0";
		//获取expert现有数据
		List<Expert> list = expertService.findList(expert);
		//遍历查重，一旦重复就break跳出循环，直接返回
		for(Expert e:list) {
			if(checkString.equals(e.getEmail())) {						//邮箱查重
				result = "1";
				break;
			}else if(checkString.equals(e.getPhone())) {				//手机号查重
				result = "2";
				break;
			}else if(checkString.equals(e.getBankCardNumber())) {		//银行卡号查重
				result = "3";
				break;
			}
		}
		return result;
	}
	
	/**
	 * 导出专家信息数据
	 * @param jwcmis
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("jwcmis:expert:view")
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
		return "redirect:" + adminPath + "/jwcmis/expert/?repage";
    }
	
    /**
	 * 导入专家信息数据
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("jwcmis:expert:edit")
	@RequestMapping(value = "import", method=RequestMethod.POST)
	public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/jwcmis/expert/?repage";
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
			Major major = new Major();
			//遍历链表中的每一条数据，相当于每一个eNew就是一条expert数据，这条数据是未经加密的
			for (Expert eNew : list){
				try{	
					//把专家的专业转化为专业表对应的id
					major.setName(eNew.getMajor());
					major = majorService.findTheData(major);
					eNew.setMajor(major.getId());
					//将导入的专家类型转换为字典的值
					String expertType = DictUtils.getDictValue(eNew.getExpertType(), "expertType", "");
					eNew.setExpertType(expertType);
					//导入查重，如果重复，就不保存
					if(expertService.importCheckRepeat(eNew)) {
						expertService.save(eNew);
						successNum++;
					}else {
						failureMsg.append("<br/>姓名: " + eNew.getExpertName()+ 
								"<pre style='display:inline; background-color:rgba(0,0,0,0); border:0;'>				</pre>原因： 数据重复，导入失败！");
						failureNum++;
					}
				}catch(ConstraintViolationException ex){
					failureMsg.append("<br/>姓名: " + eNew.getExpertName()+" 导入失败：");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");  
					for (String message : messageList){
						failureMsg.append(message+"; ");
						failureNum++;
					}
				}catch (Exception ex) {
					failureMsg.append("<br/>姓名: " + eNew.getExpertName()+" 导入失败：" + ex.getMessage());
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条信息，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条信息"+failureMsg);
			
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入专家信息失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/jwcmis/expert/list?repage";
	}
	
	/**
	 * 下载导入专家信息模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("jwcmis:expert:view")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "专家信息导入模板.xlsx";
    		List<Expert> list = Lists.newArrayList(); 
    		Expert expert = new Expert();
    		expert.setExpertName("李四");
    		expert.setMajor("电子商务专业");
    		expert.setResearchName("电子商务");
    		expert.setDistributeNum(0);    		
    		expert.setExpertType("校内");
    		expert.setEmail("2854616@qq.com");
    		expert.setPhone("18978395032");
    		expert.setBankCardNumber("6222600810010710887");
    		expert.setPlace("武汉大学");
    		list.add(expert);
    		new ExportExcel("专家信息模板", Expert.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/jwcmis/expert/list?repage";
    }

}