package java_io;

import java.io.File;

/**
 * 名称或路�?
 * getName()   : 名称
 * getPath()    : 相对、绝�?
	getAbsolutePath() :绝对
	getParent(): 上路�? 返回null

 * @author 裴新
 *
 */
public class FileDemo03 {

	public static void main(String[] args) {
		File src = new File("IO_study01/IO.png");
		
		//基本信息
		System.out.println("名称:"+src.getName());
		System.out.println("路径:"+src.getPath());
		System.out.println("绝对路径:"+src.getAbsolutePath());
		System.out.println("父路�?:"+src.getParent());
		System.out.println("父对�?:"+src.getParentFile().getName());
	}

}
