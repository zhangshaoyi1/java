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
					"�𾴵�&lt;strong&gt;&lt;span style=&quot;color:#ff0000;&quot;&gt;������&lt;/span&gt;&lt;/strong&gt;����&lt;/p&gt;\r\n" + 
					"&lt;p style=&quot;text-indent:20pt;&quot;&gt;\r\n" + 
					"���������������˺�Ϊ&lt;span style=&quot;color:#ff0000;&quot;&gt;ddd&lt;/span&gt;������Ϊ&lt;span style=&quot;color:#ff0000;&quot;&gt;www&lt;/span&gt;���뾡������ַ&lt;span style=&quot;color:#ff0000;&quot;&gt;www.baidu.com&lt;/span&gt;��������&lt;/p&gt;\r\n" + 
					"&lt;p style=&quot;text-indent:20pt;&quot;&gt;\r\n" + 
					"����&lt;/p&gt;\r\n" + 
					"&lt;p style=&quot;text-indent:20pt;&quot;&gt;\r\n" + 
					"����ʦ����ѧ&lt;/p&gt;";
			
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
	String regEx_script="<script[^>]*?>[\\s\\S]*?<\\/script>";//����script��������ʽ 
	String regEx_style="<style[^>]*?>[\\s\\S]*?<\\/style>"; //����style��������ʽ 
	String regEx_html="<[^>]+>"; //����HTML��ǩ��������ʽ 

	Pattern p_script=Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE); 
	Matcher m_script=p_script.matcher(htmlStr); 
	htmlStr=m_script.replaceAll(""); //����script��ǩ 

	Pattern p_style=Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE); 
	Matcher m_style=p_style.matcher(htmlStr); 
	htmlStr=m_style.replaceAll(""); //����style��ǩ 

	Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE); 
	Matcher m_html=p_html.matcher(htmlStr); 
	htmlStr=m_html.replaceAll(""); //
	return htmlStr.trim();
}
public void send(String text,String email) throws Exception{
	 Properties props = new Properties();

       // ����debug����
       props.setProperty("mail.debug", "true");
       // ���ͷ�������Ҫ�����֤
       props.setProperty("mail.smtp.auth", "true");
       // �����ʼ�������������
       props.setProperty("mail.host", "smtp.163.com");
       // �����ʼ�Э������
       props.setProperty("mail.transport.protocol", "smtp");
     
       Session session = Session.getInstance(props);

       
       Message msg = new MimeMessage(session);
       msg.setSubject("���ܠD��������");
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
       // ���ڱ����������Ĳ���
       MimeBodyPart contentBody = new MimeBodyPart();
       // ��������ı���ͼƬ��"related"�͵�MimeMultipart����
       MimeMultipart contentMulti = new MimeMultipart("related");

       // ���ĵ��ı�����
       MimeBodyPart textBody = new MimeBodyPart();
       textBody.setContent(body, "text/html;charset=gbk");
       contentMulti.addBodyPart(textBody);

       // ���ĵ�ͼƬ����
       MimeBodyPart jpgBody = new MimeBodyPart();
       FileDataSource fds = new FileDataSource(fileName);
       jpgBody.setDataHandler(new DataHandler(fds));
       jpgBody.setContentID("logo_jpg");
       contentMulti.addBodyPart(jpgBody);

       // ������"related"�͵� MimeMultipart ������Ϊ�ʼ�������
       contentBody.setContent(contentMulti);
       return contentBody;
       
   }	
}
