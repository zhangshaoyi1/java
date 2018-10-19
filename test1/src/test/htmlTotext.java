package test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Document.OutputSettings;
import org.jsoup.safety.Whitelist;

public class htmlTotext {
//	public static String toPlainText(final String html)
//	{
//		if (html == null)
//		{
//			return "";
//		}
// 
//		final Document document = Jsoup.parse(html);
//		final OutputSettings outputSettings = new Document.OutputSettings().prettyPrint(false);
//		document.outputSettings(outputSettings);
//		document.select("br").append("\\n");
//		document.select("p").prepend("\\n");
//		document.select("p").append("\\n");
//		final String newHtml = document.html().replaceAll("\\\\n", "\n");
//		final String plainText = Jsoup.clean(newHtml, "", Whitelist.none(), outputSettings);
//		final String result = StringEscapeUtils.unescapeHtml(plainText.trim());
//		return result;
//	}
public static void main(String[] args) {
	a a = new a();
	String str = "<p>\\n\" + \r\n" + 
			"					\"	尊敬的<strong><span style=\\\"color:#ff0000;\\\">{1}</span></strong>您好</p>\\n\" + \r\n" + 
			"					\"<p style=\\\"text-indent:20pt;\\\">\\n\" + \r\n" + 
			"					\"	您所审批的论文账号为<span style=\\\"color:#ff0000;\\\">{2}</span>，密码为<span style=\\\"color:#ff0000;\\\">{3}</span>，请尽快点击网址<span style=\\\"color:#ff0000;\\\">{4}</span>进行审批</p>\\n\" + \r\n" + 
			"					\"<p style=\\\"text-indent:20pt;\\\">\\n\" + \r\n" + 
			"					\"	此致</p>\\n\" + \r\n" + 
			"					\"<p style=\\\"text-indent:20pt;\\\">\\n\" + \r\n" + 
			"					\"	华中师范大学</p>";
	System.out.println(str);
	System.out.println("**********************************");
	System.out.println(a.delHTMLTag(str));
	System.out.println("**********************************");
	System.out.println(a.delHTMLTag(a.delHTMLTag(str)));
}
	

}
class a{
public String delHTMLTag(String htmlStr){ 
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

