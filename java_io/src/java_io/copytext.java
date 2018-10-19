package java_io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

class copytext {
	/**
	 * ���ı�����
	 * @param soursePath �ļ���Դ
	 * @param destinationPath �ļ�Ŀ�ĵ�
	 */
	public static void copytext(String soursePath,String destinationPath) {
		//����Դ
		File sourse =new File(soursePath);
		File destination = new File(destinationPath);
		//ѡ����
		
		Reader reader = null;
		Writer writer = null;
		try {
			reader = new FileReader(sourse);
			writer = new FileWriter(destination);
			
			//����
			char data[] = new char[1];
			int len = -1;
			while((len = reader.read(data))!=-1) {
				writer.write(data,0,len);
			}
			writer.flush();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {}
			if(null != reader) {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(null != writer) {
				try {
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}

}
