/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.edu.ccnu.imd.gsmis.service;

import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.security.Cryptos;
import com.thinkgem.jeesite.common.service.CrudService;

import cn.edu.ccnu.imd.gsmis.entity.Account;
import cn.edu.ccnu.imd.gsmis.entity.DistributeResult;
import cn.edu.ccnu.imd.gsmis.entity.Email;
import cn.edu.ccnu.imd.gsmis.dao.EmailDao;

/**
 * 邮件模板管理Service
 * @author cgq
 * @version 2018-08-13
 */
@Service
@Transactional(readOnly = true)
public class EmailService extends CrudService<EmailDao, Email> {
	
	@Autowired
	private AccountService accountService;
	
	public String send(DistributeResult d,String str) throws Exception{
		String studentID = d.getStudentID();
		String account[][] = getAccount(studentID);
		if(account[0][0].equals("-1")) {
			return account[0][1];
		}
		//校内专家发邮件
	
		String text =getText(str);
		text = text.replace("{1}", Cryptos.aesDecrypt(d.getInexpertName()));
		text = text.replace("{4}", d.getUrl()+" ");
		text = text.replace("{2}", account[0][0]);
		text = text.replace("{3}", account[0][1]);
		sendemail(text, Cryptos.aesDecrypt(d.getInemail()));
		//校外专家发邮件
		String text1 = getText(str);
		text1 = text1.replace("{1}", Cryptos.aesDecrypt(d.getOutexpertName()));
		text1 = text1.replace("{4}", d.getUrl()+" ");
		text1 = text1.replace("{2}", account[1][0]);
		text1 = text1.replace("{3}", account[1][1]);
		sendemail(text1, Cryptos.aesDecrypt(d.getOutemail()));
		return "1";
	}
	//获取账号和密码
	public String[][] getAccount(String studentID){
		String str[][] = new String[2][2];
		Account account = new Account();
		account.setStudentid(studentID);
		List<Account> list = accountService.findList(account);
		int i = 0;
		for(Account a : list) {
			if(list.size()!=2) {
				str[0][0] = "-1";
				str[0][1] = studentID;
				return str;
			}
		str[i][0] = a.getUsername();
		str[i][1] = a.getPassword();
		i++;
		}
		return str;
	}
	public void sendemail(String text,String email) throws Exception{
		 Properties props = new Properties();

	        // 开启debug调试
	        props.setProperty("mail.debug", "true");
	        // 发送服务器需要身份验证
	        props.setProperty("mail.smtp.auth", "true");
	        // 设置邮件服务器主机名
	        props.setProperty("mail.host", "smtp.163.com");
	        // 发送邮件协议名称
	        props.setProperty("mail.transport.protocol", "smtp");
	      
	        Session session = Session.getInstance(props);

	        
	        Message msg = new MimeMessage(session);
	        msg.setSubject("论文评审通知");
	        StringBuilder builder = new StringBuilder();
	        builder.append(text);

	        msg.saveChanges();
	        msg.setText(builder.toString());
	        msg.setFrom(new InternetAddress("a2016214291@163.com"));

	        Transport transport = session.getTransport();
	        transport.connect("smtp.163.com", Global.getConfig("emailUsername"), Global.getConfig("emailPassword"));

	        transport.sendMessage(msg, new Address[] { new InternetAddress(
	                email) });
	        transport.close();
	        
	}
	  public static MimeBodyPart createContent(String body, String fileName)
	            throws Exception {
	        // 用于保存最终正文部分
	        MimeBodyPart contentBody = new MimeBodyPart();
	        // 用于组合文本和图片，"related"型的MimeMultipart对象
	        MimeMultipart contentMulti = new MimeMultipart("related");

	        // 正文的文本部分
	        MimeBodyPart textBody = new MimeBodyPart();
	        textBody.setContent(body, "text/html;charset=gbk");
	        contentMulti.addBodyPart(textBody);

	        // 正文的图片部分
	        MimeBodyPart jpgBody = new MimeBodyPart();
	        FileDataSource fds = new FileDataSource(fileName);
	        jpgBody.setDataHandler(new DataHandler(fds));
	        jpgBody.setContentID("logo_jpg");
	        contentMulti.addBodyPart(jpgBody);

	        // 将上面"related"型的 MimeMultipart 对象作为邮件的正文
	        contentBody.setContent(contentMulti);
	        return contentBody;
	        
	    }
	  public int findIsOnly(String str) {
		  int flag=0;
		  for(int i = 0; i<str.length()-2;i++) {
			  
			  if(str.substring(i, i+3).equals("{1}") ||
				 str.substring(i, i+3).equals("{2}") ||
				 str.substring(i, i+3).equals("{3}") ||
				 str.substring(i, i+3).equals("{4}") ) {
				  flag++;
			  }
			 
		  }
		  return flag;
	  }
	  public  String getText(String htmlStr){ 
		  	htmlStr = htmlStr.replaceAll("&lt;", "<");
			htmlStr = htmlStr.replaceAll("&gt;", ">");
			String regEx_script="<script[^>]*?>[\\s\\S]*?<\\/script>";//定义script的正则表达式 
			String regEx_style="<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式 
			String regEx_html="<[^>]+>"; //定义HTML标签的正则表达式 

			Pattern p_script=Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE); 
			Matcher m_script=p_script.matcher(htmlStr); 
			htmlStr=m_script.replaceAll(""); //过滤script标签 

			Pattern p_style=Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE); 
			Matcher m_style=p_style.matcher(htmlStr); 
			htmlStr=m_style.replaceAll(""); //过滤style标签 

			Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE); 
			Matcher m_html=p_html.matcher(htmlStr); 
			htmlStr=m_html.replaceAll(""); //
			return htmlStr.trim();
		}
}

