package ioTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * 第一个程�?:理解操作步骤  标准
 * 1、创建源
 * 2、�?�择�?
 * 3、操�?
 * 4、释放资�?
 * 
 * @author 裴新
 *
 */
public class IOTest02 {

	public static void main(String[] args) {
		//1、创建源
		File src = new File("abc.txt");
		//2、�?�择�?
		InputStream  is =null;
		try {
			is =new FileInputStream(src);
			//3、操�? (读取)
			int temp ;
			while((temp=is.read())!=-1) {
				System.out.println((char)temp);
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
