package jsoup_test;

import java.io.FileWriter;
import java.io.IOException;
/**
 * ���ļ�����׷��
 * @author ZSY
 *
 */

public class writer {
	public void write(String path,String str) {
		try {
			FileWriter fileWriter = new FileWriter(path, true);
			fileWriter.write("\n");
			fileWriter.write(str);
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
