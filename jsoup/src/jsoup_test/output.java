package jsoup_test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.jsoup.nodes.Document;

/**
 * �����ļ�֮����д�루��׷�ӣ�
 * �ļ��ַ������ ���뻺����
 *1������Դ
 *2��ѡ����
 *3������(д������)
 *4���ͷ���Դ
 *  @author ����
 *
 */
public class output{

	public  void appendFile(String FilePath,String str) {
		//1������Դ
		File file = new File(FilePath);
		//2��ѡ����
		BufferedWriter writer =null;
		try {
			writer = new BufferedWriter(new FileWriter(file));
			//3������(д��)
			writer.append((CharSequence) str);
			writer.flush();
		}catch(FileNotFoundException e) {		
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}finally{
			//4���ͷ���Դ
			try {
				if (null != writer) {
					writer.close();
				} 
			} catch (Exception e) {
			}
		}
	}

}
