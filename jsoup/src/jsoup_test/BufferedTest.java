package jsoup_test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * �ļ��ַ������ ���뻺����
 *1������Դ
 *2��ѡ����
 *3������(д������)
 *4���ͷ���Դ
 *  @author ����
 *
 */
public class BufferedTest{

	public static void main(String[] args) {
		//1������Դ
		File dest = new File("dest.txt");
		//2��ѡ����
		BufferedWriter writer =null;
		try {
			writer = new BufferedWriter(new FileWriter(dest));
			//3������(д��)			
			writer.append("this is io test");
			writer.newLine();
			writer.append("��ѧ�û�ӭ��");
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
