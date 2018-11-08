package ioTest;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 字节数组输出�? ByteArrayOutputStream
 *1、创建源  : 内部维护
 *2、�?�择�?  : 不关联源
 *3、操�?(写出内容)
 *4、释放资�? :可以不用
 *
 * 获取数据:  toByteArray()
 *  @author 裴新
 *
 */
public class IOTest08 {

	public static void main(String[] args) {
		//1、创建源
		byte[] dest =null;
		//2、�?�择�? （新增方法）
		ByteArrayOutputStream baos =null;
		try {
			baos = new ByteArrayOutputStream();
			//3、操�?(写出)
			String msg ="show me the code";
			byte[] datas =msg.getBytes(); // 字符�?-->字节数组(编码)
			baos.write(datas,0,datas.length);
			baos.flush();
			//获取数据
			dest = baos.toByteArray();
			System.out.println(dest.length +"-->"+new String(dest,0,baos.size()));
		}catch(FileNotFoundException e) {		
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}finally{
			//4、释放资�?
			try {
				if (null != baos) {
					baos.close();
				} 
			} catch (Exception e) {
			}
		}
	}

}
