package test;

public class find {
public static void main(String[] args) {
	String str = "�𾴵�{1}����\r\n" + 
			"\r\n" + 
			"���������������˺�Ϊ{2}������Ϊ{3}���뾡������ַ{4}��������\r\n" + 
			"\r\n" + 
			"����\r\n" + 
			"\r\n" + 
			"����ʦ����ѧ";
	 int flag1=0;
	  for(int i = 0; i<str.length()-2;i++) {
		  
		  if(str.substring(i, i+3).equals("{1}") ||
			 str.substring(i, i+3).equals("{2}") ||
			 str.substring(i, i+3).equals("{3}") ||
			 str.substring(i, i+3).equals("{4}") ) {
			  flag1++;
		  }
		 
	  }
	  if(flag1==4) {
		  System.out.println("ok");
	  }
	  else
	  {
	 System.out.println("��ok");
	  }
}
}
