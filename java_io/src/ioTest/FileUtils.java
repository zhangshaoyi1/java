package ioTest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 1、封装拷�?
 * 2、封装释�?
 * @author 裴新
 *
 */
public class FileUtils {

	public static void main(String[] args) {
		//文件到文�?
		try {
			InputStream is = new FileInputStream("abc.txt");
			OutputStream os = new FileOutputStream("abc-copy.txt");
			copy(is,os);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//文件到字节数�?
		byte[] datas = null;
		try {
			InputStream is = new FileInputStream("p.png");
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			copy(is,os);
			datas = os.toByteArray();
			System.out.println(datas.length);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//字节数组到文�?
		try {
			InputStream is = new ByteArrayInputStream(datas);
			OutputStream os = new FileOutputStream("p-copy.png");
			copy(is,os);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 对接输入输出�?
	 * @param is
	 * @param os
	 */
	public static void copy(InputStream is,OutputStream os) {		
			try {			
				//3、操�? (分段读取)
				byte[] flush = new byte[1024]; //缓冲容器
				int len = -1; //接收长度
				while((len=is.read(flush))!=-1) {
					os.write(flush,0,len); //分段写出
				}			
				os.flush();
			}catch(FileNotFoundException e) {		
				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			}finally{
				//4、释放资�? 分别关闭 先打�?的后关闭
				close(is,os);
			}
	}
	/**
	 * 释放资源
	 * @param is
	 * @param os
	 */
	public static void close(InputStream is ,OutputStream os) {
		try {
			if (null != os) {
				os.close();
			} 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			if(null!=is) {
				is.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	/**
	 * 释放资源
	 * @param ios
	 */
	public static void close(Closeable... ios) {
		for(Closeable io:ios) {
			try {
				if(null!=io) {
					io.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
