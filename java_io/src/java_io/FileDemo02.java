package java_io;

import java.io.File;

/**
 * 
 * @author 裴新
 *
 */
public class FileDemo02 {
	/**
	 * 构建File对象
	 * 相对路径与绝对路�?
	 * 1)、存在盘�?: 绝对路径
	 * 2)、不存在盘符:相对路径  ,当前目录 user.dir
	 * @param args
	 */
	public static void main(String[] args) {
		String path ="D:/java300/IO_study01/IO.png";
		
		//绝对路径
		File src = new File(path);
		System.out.println(src.getAbsolutePath());
		
		//相对路径
		System.out.println(System.getProperty("user.dir"));
		src = new File("IO.png");
		System.out.println(src.getAbsolutePath());
		
		
		//构建�?个不存在的文�?
		src = new File("aaa/IO2.png");
		System.out.println(src.getAbsolutePath());
	}

}
