package MyTest;

import java.util.regex.Pattern;

//��html����ȡ���ı�
public class test{
	public static void main(String[] args) {
		String str = Html1Text("<p>\r\n" + 
				"	�𾴵�<strong><span style=\"color:#ff0000;\">{1}</span></strong>����</p>\r\n" + 
				"<p style=\"text-indent:20pt;\">\r\n" + 
				"	��������������z�˺�Ϊ<span style=\"color:#ff0000;\">{2}</span>������Ϊ<span style=\"color:#ff0000;\">{3}</span>���뾡������ַ<span style=\"color:#ff0000;\">{4}</span>��������</p>\r\n" + 
				"<p style=\"text-indent:20pt;\">\r\n" + 
				"	����</p>\r\n" + 
				"<p style=\"text-indent:20pt;\">\r\n" + 
				"	����ʦ����ѧ</p>");
		System.out.println(str);
	}
	public static String Html1Text(String inputString) {
		String htmlStr = inputString; // ��html��ǩ���ַ���
		String textStr = "";
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
		java.util.regex.Pattern p_style;
		java.util.regex.Matcher m_style;
		java.util.regex.Pattern p_html;
		java.util.regex.Matcher m_html;
		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // ����script��������ʽ{��<script[^>]*?>[\\s\\S]*?<\\/script>
	        String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // ����style��������ʽ{��<style[^>]*?>[\\s\\S]*?<\\/style>
	        String regEx_html = "<[^>]+>"; // ����HTML��ǩ��������ʽ
	        p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
	        m_script = p_script.matcher(htmlStr);
	        htmlStr = m_script.replaceAll(""); // ����script��ǩ
	        p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
	        m_style = p_style.matcher(htmlStr);
	        htmlStr = m_style.replaceAll(""); // ����style��ǩ
	        p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
	        m_html = p_html.matcher(htmlStr);
	        htmlStr = m_html.replaceAll(""); // ����html��ǩ
	        textStr = htmlStr;
	    } catch (Exception e) {System.err.println("Html2Text: " + e.getMessage()); }
		//�޳��ո���
		textStr=textStr.replaceAll("[ ]+", " ");
		textStr=textStr.replaceAll("(?m)^\\s*$(\\n|\\r\\n)", "");
		return textStr;// �����ı��ַ���
	}
}
