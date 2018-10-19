/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.edu.ccnu.imd.gsmis.web;

import java.util.Date;
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
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import cn.edu.ccnu.imd.gsmis.entity.Account;
import cn.edu.ccnu.imd.gsmis.entity.Thesis;
import cn.edu.ccnu.imd.gsmis.service.AccountService;
import cn.edu.ccnu.imd.gsmis.service.ThesisService;

/**
 * 论文信息管理Controller
 * 
 * @author hyf
 * @version 2018-08-16
 */
@Controller
@RequestMapping(value = "${adminPath}/gsmis/thesis")
public class ThesisController extends BaseController {

	@Autowired
	private ThesisService thesisService;
	@Autowired
	private AccountService accountService;
	@ModelAttribute
	public Thesis get(@RequestParam(required = false) String id) {
		Thesis entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = thesisService.get(id);
		}
		if (entity == null) {
			entity = new Thesis();
		}
		return entity;
	}

	@RequiresPermissions("gsmis:thesis:view")
	@RequestMapping(value = { "list", "" })
	public String list(Thesis thesis, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Thesis> page = thesisService.findPage(new Page<Thesis>(request, response), thesis);
		model.addAttribute("page", page);
		return "imd/gsmis/thesisList";
	}
	@RequiresPermissions("gsmis:thesis:view")
	@RequestMapping(value = "form")
	public String form(Thesis thesis, Model model) {
		
		if(thesis.getId()==null) {
			model.addAttribute("thesis", thesis);
			return "imd/gsmis/thesisForm";
		}
		List<Account> accountList =Lists.newArrayList();
		for(Account t:accountService.findList(new Account())) {
			if("0".equals(t.getDelFlag())) {
				if(thesis.getStudentid().equals(t.getStudentid())) {
					accountList.add(t);
				}
			}
		}  
		thesis.setAccountList(accountList);
		model.addAttribute("thesis", thesis);
		return "imd/gsmis/thesisForm";

	}

	@RequiresPermissions("gsmis:thesis:edit")
	@RequestMapping(value = "save")
	public String save(Thesis thesis, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, thesis)) {
			return form(thesis, model);
		}
		String flag=thesisService.save1(thesis);
		if("".equals(flag)) {
		addMessage(redirectAttributes, "保存论文信息成功");
		return "redirect:" + Global.getAdminPath() + "/gsmis/thesis/?repage";
		} else {
			addMessage(redirectAttributes, "插入论文信息失败！失败信息："+flag);
			return "redirect:" + Global.getAdminPath() + "/gsmis/thesis/?repage";
		}
	}
	@RequiresPermissions("gsmis:thesis:edit")
	@RequestMapping(value = "delete")
	public String delete(Thesis thesis, RedirectAttributes redirectAttributes) {
		thesisService.delete(thesis);
		addMessage(redirectAttributes, "删除论文信息成功");
		return "redirect:" + Global.getAdminPath() + "/gsmis/thesis/?repage";
	}

	// 添加评审
	@RequiresPermissions("gsmis:thesis:edit")
	@RequestMapping(value = "pingshen")
	public String pingshen(Thesis thesis, RedirectAttributes redirectAttributes) {
		thesis.setStatus("2");
		thesisService.save(thesis);
		addMessage(redirectAttributes, "修改论文状态成功");
		return "redirect:" + Global.getAdminPath() + "/gsmis/thesis/?repage";
	}

	// 添加一键评审
	@RequiresPermissions("gsmis:thesis:edit")
	@RequestMapping(value = "pingshenAll")
	public String pingshenAll(Thesis thesis, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		String masterIds = request.getParameter("documentIds");
		if (masterIds != null && !"".equals(masterIds)) {
			String[] _masterIds = masterIds.split(",");
			if (_masterIds != null && _masterIds.length > 0) {
				for (int i = 0; i < _masterIds.length; i++) {
					thesis = thesisService.get(_masterIds[i]);
					thesis.setStatus("2");
					thesisService.save(thesis);
				}
				addMessage(redirectAttributes, "修改论文状态成功");
				return "redirect:" + Global.getAdminPath() + "/gsmis/thesis/?repage";
			}
		}
		return "redirect:" + Global.getAdminPath() + "/gsmis/thesis/?repage";
	}

	/**
	 * 导入论文信息数据
	 * 
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RequiresPermissions("gsmis:thesis:view")
	@RequestMapping(value = "import", method = RequestMethod.POST)
	public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		if (Global.isDemoMode()) {
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/gsmis/thesis/?repage";
		}
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<Thesis> list = ei.getDataList(Thesis.class);
			 //导入到thesis开始
			for (Thesis thesis : list) {
				try {
					if(thesis.getStudentid()== null||"".equals(thesis.getStudentid())) continue;
					Account account=new Account(thesis.getStudentid(),thesis.getUsername(),thesis.getPassword());
					String status =DictUtils.getDictValue(thesis.getStatus(), "thesisStatus", "");//将导入的论文状态转换为字典的值
					thesis.setStatus(status);//设置论文的状态值
					Date dateget=thesis.getReviewdeadline();
					dateget.setHours(0);
					dateget.setMinutes(0);
					dateget.setSeconds(0);
					accountService.save1(account);
					thesisService.save1(thesis);
					successNum++;
				} catch (ConstraintViolationException ex) {
					failureMsg.append("<br/>姓名 " + thesis.getMastername() + " 导入失败：");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": "); // 告诉你出错原因
					for (String message : messageList) {
						failureMsg.append(message + "; ");
						failureNum++;
					}
				} catch (Exception ex) {
					failureMsg.append("<br/>姓名 " + thesis.getMastername() + " 导入失败：" + ex.getMessage());
				}
			}
			// 导入到thesis结束
			if (failureNum > 0) {
				failureMsg.insert(0, "，失败 " + failureNum + " 条信息，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 " + successNum + " 条信息" + failureMsg);
		} catch (Exception e) {
			//addMessage(redirectAttributes, "导入论文信息失败！失败信息：" + e.getMessage());
			addMessage(redirectAttributes, "导入论文信息失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/gsmis/thesis/list?repage";
	}
	/**
	 * 下载导入用户数据模板
	 * 
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("gsmis:thesis:view")
	@RequestMapping(value = "import/template")
	public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "论文信息数据导入模板.xlsx";
			List<Thesis> list = Lists.newArrayList();
			new ExportExcel("论文信息数据", Thesis.class, 2).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/gsmis/thesis/list?repage";
	}
}