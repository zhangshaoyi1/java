package java_io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

class copyFile {
	 /**
	  * 拷贝文件
	  * @param isPath 数据来源
	  * @param doesPath 数据去向
	  */
	public static void copy(String srcPath,String doesPath) {
		//创建源
		File src = new File(srcPath);//源头
		File does = new File(doesPath);//目的地
		//选择流
		InputStream is = null;
		OutputStream os = null;
		try{
			is = new FileInputStream(src);
			os = new FileOutputStream(does);
			
			//操作
			byte flush[] = new byte[1024];//缓冲容器
			int len = -1;
			while((len = is.read(flush))!=-1) {
				os.write(flush, 0, len);
			}
			os.flush();
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if(null!=os) {
				try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(null!=is) {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}

