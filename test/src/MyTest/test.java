package MyTest;

import java.util.regex.Pattern;

//从html中提取纯文本
public class test{
	public static void main(String[] args) {
		String str = Html1Text("<p>\r\n" + 
				"	尊敬的<strong><span style=\"color:#ff0000;\">{1}</span></strong>您好</p>\r\n" + 
				"<p style=\"text-indent:20pt;\">\r\n" + 
				"	您所审批的论文z账号为<span style=\"color:#ff0000;\">{2}</span>，密码为<span style=\"color:#ff0000;\">{3}</span>，请尽快点击网址<span style=\"color:#ff0000;\">{4}</span>进行审批</p>\r\n" + 
				"<p style=\"text-indent:20pt;\">\r\n" + 
				"	此致</p>\r\n" + 
				"<p style=\"text-indent:20pt;\">\r\n" + 
				"	华中师范大学</p>");
		System.out.println(str);
	}
	public static String Html1Text(String inputString) {
		String htmlStr = inputString; // 含html标签的字符串
		String textStr = "";
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
		java.util.regex.Pattern p_style;
		java.util.regex.Matcher m_style;
		java.util.regex.Pattern p_html;
		java.util.regex.Matcher m_html;
		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
	        String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
	        String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
	        p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
	        m_script = p_script.matcher(htmlStr);
	        htmlStr = m_script.replaceAll(""); // 过滤script标签
	        p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
	        m_style = p_style.matcher(htmlStr);
	        htmlStr = m_style.replaceAll(""); // 过滤style标签
	        p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
	        m_html = p_html.matcher(htmlStr);
	        htmlStr = m_html.replaceAll(""); // 过滤html标签
	        textStr = htmlStr;
	    } catch (Exception e) {System.err.println("Html2Text: " + e.getMessage()); }
		//剔除空格行
		textStr=textStr.replaceAll("[ ]+", " ");
		textStr=textStr.replaceAll("(?m)^\\s*$(\\n|\\r\\n)", "");
		return textStr;// 返回文本字符串
	}
}
