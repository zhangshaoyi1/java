package java_io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

class copyFile1 {
	/**
	 * �����ļ�
	 */
	public static void copy(String soursePath,String destinationPath) {
		//����Դ
		File sourse = new File(soursePath);
		File destination = new File(destinationPath);
		//ѡ����
		InputStream Is = null;
		OutputStream Os = null;
		
		try {
			Is = new FileInputStream(sourse);
			Os = new FileOutputStream(destination);
			
			//����
			byte data[] = new byte[1024];
			int len = -1;
			while((len = Is.read(data))!=-1) {
				Os.write(data, 0, len);
			}
			Os.flush();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				if(null != Os) {
					Os.close();
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		if(null != Is) {
			try {
				Is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
