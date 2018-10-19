package test;

public class replace {
public static void main(String[] args) {
	System.out.println("hello");
	String str = "尊敬的{1}您好\r\n" + 
			"\r\n" + 
			"您所审批的论文账号为{2}，密码为{3}，请尽快点击网址{4}进行审批\r\n" + 
			"\r\n" + 
			"此致\r\n" + 
			"\r\n" + 
			"华中师范大学";
	str = str.replace("{1}", "hhh");
	str = str.replace("{2}", "hhh");
	str = str.replaceFirst("{3}", "kkk");
	System.out.println(str);
}
}
