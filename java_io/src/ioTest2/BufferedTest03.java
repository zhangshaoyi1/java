package ioTest2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * 四个步骤: 分段读取 文件字符输入�?  加入缓冲�?
 * 1、创建源
 * 2、�?�择�?
 * 3、操�?
 * 4、释放资�?
 * 
 * @author 裴新
 *
 */
public class BufferedTest03 {

	public static void main(String[] args) {
		//1、创建源
		File src = new File("abc.txt");
		//2、�?�择�?
		BufferedReader  reader =null;
		try {
			reader =new BufferedReader(new FileReader(src));
			//3、操�? (分段读取)
			String line =null;
			while((line=reader.readLine())!=null) {
				//字符数组-->字符�?
				System.out.println(line);
			}		
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			//4、释放资�?
			try {
				if(null!=reader) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
