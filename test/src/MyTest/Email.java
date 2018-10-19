package MyTest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
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
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;

public class Email{
	
		public static void main(String[] args) throws Exception {
			String str = "&lt;p&gt;\r\n" + 
					"尊敬的&lt;strong&gt;&lt;span style=&quot;color:#ff0000;&quot;&gt;张三赛&lt;/span&gt;&lt;/strong&gt;您好&lt;/p&gt;\r\n" + 
					"&lt;p style=&quot;text-indent:20pt;&quot;&gt;\r\n" + 
					"您所审批的论文账号为&lt;span style=&quot;color:#ff0000;&quot;&gt;ddd&lt;/span&gt;，密码为&lt;span style=&quot;color:#ff0000;&quot;&gt;www&lt;/span&gt;，请尽快点击网址&lt;span style=&quot;color:#ff0000;&quot;&gt;www.baidu.com&lt;/span&gt;进行审批&lt;/p&gt;\r\n" + 
					"&lt;p style=&quot;text-indent:20pt;&quot;&gt;\r\n" + 
					"此致&lt;/p&gt;\r\n" + 
					"&lt;p style=&quot;text-indent:20pt;&quot;&gt;\r\n" + 
					"华中师范大学&lt;/p&gt;";
			
			a a = new a();

			System.out.println(str);
			str = a.delHTMLTag(str);
			a.send(str, "2487112602@qq.com");
		}
	
}
class a{
public String delHTMLTag(String htmlStr){
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
public void send(String text,String email) throws Exception{
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
       msg.setSubject("张绍燚调试邮箱");
       StringBuilder builder = new StringBuilder();
       builder.append(text);

       msg.saveChanges();
       msg.setText(builder.toString());
       msg.setFrom(new InternetAddress("a2016214291@163.com"));

       Transport transport = session.getTransport();
       transport.connect("smtp.163.com", "a2016214291@163.com", "a2016214291");

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
}
