package test;

public class replace {
public static void main(String[] args) {
	System.out.println("hello");
	String str = "�𾴵�{1}����\r\n" + 
			"\r\n" + 
			"���������������˺�Ϊ{2}������Ϊ{3}���뾡������ַ{4}��������\r\n" + 
			"\r\n" + 
			"����\r\n" + 
			"\r\n" + 
			"����ʦ����ѧ";
	str = str.replace("{1}", "hhh");
	str = str.replace("{2}", "hhh");
	str = str.replaceFirst("{3}", "kkk");
	System.out.println(str);
}
}
