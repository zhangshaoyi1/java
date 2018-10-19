package jsoup_test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.jsoup.nodes.Document;

/**
 * 创建文件之后再写入（不追加）
 * 文件字符输出流 加入缓冲流
 *1、创建源
 *2、选择流
 *3、操作(写出内容)
 *4、释放资源
 *  @author 裴新
 *
 */
public class output{

	public  void appendFile(String FilePath,String str) {
		//1、创建源
		File file = new File(FilePath);
		//2、选择流
		BufferedWriter writer =null;
		try {
			writer = new BufferedWriter(new FileWriter(file));
			//3、操作(写出)
			writer.append((CharSequence) str);
			writer.flush();
		}catch(FileNotFoundException e) {		
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}finally{
			//4、释放资源
			try {
				if (null != writer) {
					writer.close();
				} 
			} catch (Exception e) {
			}
		}
	}

}
