package java_io;

import java.io.File;

/**
 * 列出下一�?
 * 1、list() :列出下级名称
 * 2、listFiles():列出下级File对象
 * 
 * 列出�?有的盘符: listRoots()
 * @author 裴新
 *
 */
public class DirDemo02 {
	public static void main(String[] args) {
		File dir = new File("D:/java300/IO_study01");
		
		//下级名称  list
		String[] subNames = dir.list();
		for(String s:subNames) {
			System.out.println(s);
		}
		
		//下级对象  listFiles()
		File[] subFiles = dir.listFiles();
		for(File s:subFiles) {
			System.out.println(s.getAbsolutePath());
		}
		
		//�?有盘�?
		File[] roots = dir.listRoots();
		for(File r:roots) {
			System.out.println(r.getAbsolutePath());
		}
	}
}
