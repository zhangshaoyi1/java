package ioTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 文件字节输出�?
 *1、创建源
 *2、�?�择�?
 *3、操�?(写出内容)
 *4、释放资�?
 *  @author 裴新
 *
 */
public class IOTest04 {

	public static void main(String[] args) {
		//1、创建源
		File dest = new File("dest.txt");
		//2、�?�择�?
		OutputStream os =null;
		try {
			os = new FileOutputStream(dest,true);
			//3、操�?(写出)
			String msg ="IO is so easy\r\n";
			byte[] datas =msg.getBytes(); // 字符�?-->字节数组(编码)
			os.write(datas,0,datas.length);
			os.flush();
		}catch(FileNotFoundException e) {		
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}finally{
			//4、释放资�?
			try {
				if (null != os) {
					os.close();
				} 
			} catch (Exception e) {
			}
		}
	}

}
