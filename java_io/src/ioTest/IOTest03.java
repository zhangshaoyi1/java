package ioTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * 四个步骤: 分段读取 文件字节输入�?
 * 1、创建源
 * 2、�?�择�?
 * 3、操�?
 * 4、释放资�?
 * 
 * @author 裴新
 *
 */
public class IOTest03 {

	public static void main(String[] args) {
		//1、创建源
		File src = new File("abc.txt");
		//2、�?�择�?
		InputStream  is =null;
		try {
			is =new FileInputStream(src);
			//3、操�? (分段读取)
			byte[] flush = new byte[1024*10]; //缓冲容器
			int len = -1; //接收长度
			while((len=is.read(flush))!=-1) {
				//字节数组-->字符�? (解码)
				String str = new String(flush,0,len);
				System.out.println(str);
			}		
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			//4、释放资�?
			try {
				if(null!=is) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
