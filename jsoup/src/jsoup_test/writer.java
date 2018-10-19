package jsoup_test;

import java.io.FileWriter;
import java.io.IOException;
/**
 * 在文件后面追加
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
