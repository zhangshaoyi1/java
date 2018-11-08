package ioTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * 文件字符输出�?
 *1、创建源
 *2、�?�择�?
 *3、操�?(写出内容)
 *4、释放资�?
 *  @author 裴新
 *
 */
public class IOTest06 {

	public static void main(String[] args) {
		//1、创建源
		File dest = new File("dest.txt");
		//2、�?�择�?
		Writer writer =null;
		try {
			writer = new FileWriter(dest);
			//3、操�?(写出)
			//写法�?
//			String msg ="IO is so easy\r\n尚学堂欢迎你";
//			char[] datas =msg.toCharArray(); // 字符�?-->字符数组
//			writer.write(datas,0,datas.length);
			//写法�?
			/*String msg ="IO is so easy\r\n尚学堂欢迎你";
			writer.write(msg);	
			writer.write("add");		
			writer.flush();*/
			
			//写法�?
			writer.append("IO is so easy\r\n").append("尚学堂欢迎你");
			writer.flush();
		}catch(FileNotFoundException e) {		
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}finally{
			//4、释放资�?
			try {
				if (null != writer) {
					writer.close();
				} 
			} catch (Exception e) {
			}
		}
	}

}
