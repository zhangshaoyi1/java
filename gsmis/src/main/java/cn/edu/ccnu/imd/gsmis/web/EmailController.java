/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.edu.ccnu.imd.gsmis.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.parser.ParserDelegator;

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

import cn.edu.ccnu.imd.gsmis.entity.DistributeResult;
import cn.edu.ccnu.imd.gsmis.entity.Email;
import cn.edu.ccnu.imd.gsmis.service.DistributeResultService;
import cn.edu.ccnu.imd.gsmis.service.EmailService;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.swing.text.html.HTMLEditorKit;

/**
 * 邮件模板管理Controller
 * @author cgq
 * @version 2018-08-13
 */
@Controller
@RequestMapping(value = "${adminPath}/gsmis/email")
public class EmailController extends BaseController {

	@Autowired
	private EmailService emailService;
	@Autowired
	private DistributeResultService distributeResultService;	
	@ModelAttribute
	public Email get(@RequestParam(required=false) String id) {
		Email entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = emailService.get(id);
		}
		if (entity == null){
			entity = new Email();
		}
		return entity;
	}
	
	@RequiresPermissions("gsmis:email:view")
	@RequestMapping(value = {"list", ""})
	public String list(Email email, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Email> page = emailService.findPage(new Page<Email>(request, response), email); 
		model.addAttribute("page", page);
		return "imd/gsmis/emailList";
	}

	@RequiresPermissions("gsmis:email:view")
	@RequestMapping(value = "form")
	public String form(Email email, Model model) {
		model.addAttribute("email", email);
		if(email.getText()==null || "".equals(email.getText())) {
			email.setText("<p>\n" + 
					"	尊敬的<strong><span style=\"color:#ff0000;\">{1}</span></strong>您好</p>\n" + 
					"<p style=\"text-indent:20pt;\">\n" + 
					"	您所审批的论文账号为<span style=\"color:#ff0000;\">{2}</span>，密码为<span style=\"color:#ff0000;\">{3}</span>，请尽快点击网址<span style=\"color:#ff0000;\">{4}</span>进行审批</p>\n" + 
					"<p style=\"text-indent:20pt;\">\n" + 
					"	此致</p>\n" + 
					"<p style=\"text-indent:20pt;\">\n" + 
					"	华中师范大学</p>");
		}
		
		return "imd/gsmis/emailForm";
	}

	@RequiresPermissions("gsmis:email:edit")
	@RequestMapping(value = "save")
	public String save(Email email, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, email)){
			return form(email, model);
		}
		String str = email.getText();
		String text = Html2Text.getContent(Html2Text.getContent(str));
		if(text.contains("{1}")&&text.contains("{2}")&&text.contains("{3}")&&text.contains("{4}")&&emailService.findIsOnly(text)==4) {
			emailService.save(email);
			addMessage(redirectAttributes, "保存邮件模板成功");
			return "redirect:"+Global.getAdminPath()+"/gsmis/email/?repage";
		}
		else{
			addMessage(redirectAttributes, "{1}、{2}、{3}、{4}为关键字，请不要增加或者删除");
			email.setText("<p>\n" + 
					"	尊敬的<strong><span style=\"color:#ff0000;\">{1}</span></strong>您好</p>\n" + 
					"<p style=\"text-indent:20pt;\">\n" + 
					"	您所审批的论文账号为<span style=\"color:#ff0000;\">{2}</span>，密码为<span style=\"color:#ff0000;\">{3}</span>，请尽快点击网址<span style=\"color:#ff0000;\">{4}</span>进行审批</p>\n" + 
					"<p style=\"text-indent:20pt;\">\n" + 
					"	此致</p>\n" + 
					"<p style=\"text-indent:20pt;\">\n" + 
					"	华中师范大学</p>");
			return form(email, model);
			}
		
	}
	
	@RequiresPermissions("gsmis:email:edit")
	@RequestMapping(value = "delete")
	public String delete(Email email, RedirectAttributes redirectAttributes) {
		emailService.delete(email);
		addMessage(redirectAttributes, "删除邮件模板成功");
		return "redirect:"+Global.getAdminPath()+"/gsmis/email/?repage";
	}
	/**
	 * 发送邮件
	 * @param email
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("gsmis:email:edit")
	@RequestMapping(value = {"send"})
	public String send( RedirectAttributes redirectAttributes)throws Exception {
		 	Email email = new Email();
		 	int num = 0;
		 	for(Email e : emailService.findList(email)) {
		 		if(e.getStatus().equals("1")) {
		 			num++;
		 			email = e;
		 		}
		 	}
		 	if(num!=1) {
		 		addMessage(redirectAttributes, "只能使用一个模板，请将其他模板禁用，将需要使用的模板使用");
		 		return "redirect:"+Global.getAdminPath()+"/gsmis/email/?repage";
		 	}
			DistributeResult distributeResult = new DistributeResult();
			String str = email.getText();
			int success = 0,failure = 0;
			int reason = 0;
			if( distributeResultService.findList(distributeResult).size()==0) {
				addMessage(redirectAttributes, "论文未分配");
				return "redirect:"+Global.getAdminPath()+"/gsmis/distributeResult/?repage";
				
			}
			for(DistributeResult d : distributeResultService.findList(distributeResult)) {
				if(d.getEmailstatus().equals("0")) {
				if(emailService.send(d, str).equals("1")) {
					d.setEmailstatus("1");
					distributeResultService.save(d);
					success+=2;
				}
				else
				{
					failure+=2;
				}
				}
				else {
					failure+=2;
					reason+=2;
				}
			
				
			}
			addMessage(redirectAttributes, success+"封邮件已经发送成功,"+failure+"封邮件发送失败，学号为"+reason+"封邮件已经发送，不能继续发送");
			return "redirect:"+Global.getAdminPath()+"/gsmis/distributeResult/?repage";
	}
	/**
	 *批量发送邮件
	 */
	@RequiresPermissions("gsmis:email:edit")
	@RequestMapping(value = {"sendSelectEmail"})
	public String sendSelectEmail(DistributeResult distributeResult,HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)throws Exception {
		Email email = new Email();
	 	int num = 0;
	 	for(Email e : emailService.findList(email)) {
	 		if(e.getStatus().equals("1")) {
	 			num++;
	 			email = e;
	 		}
	 	}
	 	if(num!=1) {
	 		addMessage(redirectAttributes, "只能使用一个模板，请将其他模板禁用，将需要使用的模板使用");
	 		return "redirect:"+Global.getAdminPath()+"/gsmis/email/?repage";
	 	}
	 	String str = email.getText();
		int success = 0,failure = 0;
		int reason = 0;
		String distributeResultIds = request.getParameter("distributeResultIds");
		if (distributeResultIds != null && !"".equals(distributeResultIds)) {
			String[] ids = distributeResultIds.split(",");
			for (int i = 0; i < ids.length; i++) {
				distributeResult = distributeResultService.get(ids[i]);
				//邮件未发送，可以发送
				if(distributeResult.getEmailstatus().equals("0")) {
				if(emailService.send(distributeResult, str).equals("1")) {
					distributeResult.setEmailstatus("1");
					distributeResultService.save(distributeResult);
					success+=2;
				}
				else
				{
					failure+=2;
				}
			}
				else
				{
					failure+=2;
					reason +=2;
				}
			}
			
		}
		addMessage(redirectAttributes, success+"封邮件已经发送成功,"+failure+"封邮件发送失败，"+reason+"封邮件已经发送，不能继续发送");
		return "redirect:"+Global.getAdminPath()+"/gsmis/distributeResult/?repage";
	}
	
	
}
class Html2Text extends HTMLEditorKit.ParserCallback {
	

	    private static Html2Text html2Text = new Html2Text();

	    StringBuffer s;

	    public Html2Text() {
	    	

	    }

	    public void parse(String str) throws IOException {

	        InputStream iin = new ByteArrayInputStream(str.getBytes());
	        Reader in = new InputStreamReader(iin);
	        s = new StringBuffer();
	        ParserDelegator delegator = new ParserDelegator();
	        // the third parameter is TRUE to ignore charset directive
	        delegator.parse(in, this, Boolean.TRUE);
	        iin.close();
	        in.close();
	    }

	    public void handleText(char[] text, int pos) {
	        s.append(text);
	    }

	    public String getText() {
	        return s.toString();
	    }

	    public static String getContent(String str) {
	        try {
	            html2Text.parse(str);
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        return html2Text.getText();
	    }
	}

	






