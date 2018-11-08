package ioTest2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * 面向对象思想封装 分割
 * @author 裴新
 *
 */
public class SplitFile {
	//源头
	private File src;
	//目的�?(文件�?)
	private String destDir;
	//�?有分割后的文件存储路�?
	private List<String> destPaths;
	//每块大小
	private int blockSize;
	//块数: 多少�?
	private int size;
	
	public SplitFile(String srcPath,String destDir) {
		this(srcPath,destDir,1024);
	}
	public SplitFile(String srcPath,String destDir,int blockSize) {
		this.src =new File(srcPath);
		this.destDir =destDir;
		this.blockSize =blockSize;
		this.destPaths =new ArrayList<String>();
		
		//初始�?
		 init();
	}
	//初始�?
	private void init() {
		//总长�?
		long len = this.src.length();		
		//块数: 多少�?
		this.size =(int) Math.ceil(len*1.0/blockSize);
		//路径
		for(int i=0;i<size;i++) {
			this.destPaths.add(this.destDir +"/"+i+"-"+this.src.getName());
		}
	}
	/**
	 * 分割
	 * 1、计算每�?块的起始位置及大�?
	 * 2、分�?
	 * @throws IOException 
	 */
	public void split() throws IOException {
		//总长�?
		long len = src.length();		
		//起始位置和实际大�?
		int beginPos = 0;
		int actualSize = (int)(blockSize>len?len:blockSize); 
		for(int i=0;i<size;i++) {
			beginPos = i*blockSize;
			if(i==size-1) { //�?后一�?
				actualSize = (int)len;
			}else {
				actualSize = blockSize;
				len -=actualSize; //剩余�?
			}
			splitDetail(i,beginPos,actualSize);
		}
	}	
	/**
	 * 指定第i块的起始位置 和实际长�?
	 * @param i
	 * @param beginPos
	 * @param actualSize
	 * @throws IOException
	 */
	private  void splitDetail(int i,int beginPos,int actualSize ) throws IOException {
		RandomAccessFile raf =new RandomAccessFile(this.src,"r");
		RandomAccessFile raf2 =new RandomAccessFile(this.destPaths.get(i),"rw");
		//随机读取 
		raf.seek(beginPos);
		//读取
		//3、操�? (分段读取)
		byte[] flush = new byte[1024]; //缓冲容器
		int len = -1; //接收长度
		while((len=raf.read(flush))!=-1) {			
			if(actualSize>len) { //获取本次读取的所有内�?
				raf2.write(flush, 0, len);
				actualSize -=len;
			}else { 
				raf2.write(flush, 0, actualSize);
				break;
			}
		}			
		raf2.close();
		raf.close();
	}	
	/**
	 * 文件的合�?
	 * @throws IOException 
	 */
	public void merge(String destPath) throws IOException {
		//输出�?
		OutputStream os =new BufferedOutputStream( new FileOutputStream(destPath,true));	
		Vector<InputStream> vi=new Vector<InputStream>();
		SequenceInputStream sis =null;
		//输入�?
		for(int i=0;i<destPaths.size();i++) {
			vi.add(new BufferedInputStream(new FileInputStream(destPaths.get(i))));											
		}
		sis =new SequenceInputStream(vi.elements());
		//拷贝
		//3、操�? (分段读取)
		byte[] flush = new byte[1024]; //缓冲容器
		int len = -1; //接收长度
		while((len=sis.read(flush))!=-1) {
			os.write(flush,0,len); //分段写出
		}			
		os.flush();	
		sis.close();
		os.close();
	}
	public static void main(String[] args) throws IOException {
		SplitFile sf = new SplitFile("src/com/sxt/io/SplitFile.java","dest") ;
		sf.split();
		sf.merge("aaa.java");
	}
}
