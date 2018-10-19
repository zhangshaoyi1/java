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
	  * �����ļ�
	  * @param isPath ������Դ
	  * @param doesPath ����ȥ��
	  */
	public static void copy(String srcPath,String doesPath) {
		//����Դ
		File src = new File(srcPath);//Դͷ
		File does = new File(doesPath);//Ŀ�ĵ�
		//ѡ����
		InputStream is = null;
		OutputStream os = null;
		try{
			is = new FileInputStream(src);
			os = new FileOutputStream(does);
			
			//����
			byte flush[] = new byte[1024];//��������
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
