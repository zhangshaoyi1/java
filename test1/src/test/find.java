package test;

public class find {
public static void main(String[] args) {
	String str = "尊敬的{1}您好\r\n" + 
			"\r\n" + 
			"您所审批的论文账号为{2}，密码为{3}，请尽快点击网址{4}进行审批\r\n" + 
			"\r\n" + 
			"此致\r\n" + 
			"\r\n" + 
			"华中师范大学";
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
	 System.out.println("不ok");
	  }
}
}
