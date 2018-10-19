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
			"					\"	�𾴵�<strong><span style=\\\"color:#ff0000;\\\">{1}</span></strong>����</p>\\n\" + \r\n" + 
			"					\"<p style=\\\"text-indent:20pt;\\\">\\n\" + \r\n" + 
			"					\"	���������������˺�Ϊ<span style=\\\"color:#ff0000;\\\">{2}</span>������Ϊ<span style=\\\"color:#ff0000;\\\">{3}</span>���뾡������ַ<span style=\\\"color:#ff0000;\\\">{4}</span>��������</p>\\n\" + \r\n" + 
			"					\"<p style=\\\"text-indent:20pt;\\\">\\n\" + \r\n" + 
			"					\"	����</p>\\n\" + \r\n" + 
			"					\"<p style=\\\"text-indent:20pt;\\\">\\n\" + \r\n" + 
			"					\"	����ʦ����ѧ</p>";
	System.out.println(str);
	System.out.println("**********************************");
	System.out.println(a.delHTMLTag(str));
	System.out.println("**********************************");
	System.out.println(a.delHTMLTag(a.delHTMLTag(str)));
}
	

}
class a{
public String delHTMLTag(String htmlStr){ 
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
}

