package ioTest2;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 随机读取和写入流 RandomAccessFile
 * @author 裴新
 *
 */
public class RandTest01 {

	public static void main(String[] args) throws IOException {
		//分多少块
		File src = new File("src/com/sxt/io/Copy.java");
		//总长�?
		long len = src.length();
		//每块大小
		int blockSize =1024;
		//块数: 多少�?
		int size =(int) Math.ceil(len*1.0/blockSize);
		System.out.println(size);
		
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
			System.out.println(i+"-->"+beginPos +"-->"+actualSize);
			split(i,beginPos,actualSize);
		}
		
	}
	/**
	 * 指定第i块的起始位置 和实际长�?
	 * @param i
	 * @param beginPos
	 * @param actualSize
	 * @throws IOException
	 */
	public static void split(int i,int beginPos,int actualSize ) throws IOException {
		RandomAccessFile raf =new RandomAccessFile(new File("src/com/sxt/io/Copy.java"),"r");
		//随机读取 
		raf.seek(beginPos);
		//读取
		//3、操�? (分段读取)
		byte[] flush = new byte[1024]; //缓冲容器
		int len = -1; //接收长度
		while((len=raf.read(flush))!=-1) {			
			if(actualSize>len) { //获取本次读取的所有内�?
				System.out.println(new String(flush,0,len));
				actualSize -=len;
			}else { 
				System.out.println(new String(flush,0,actualSize));
				break;
			}
		}			
		
		raf.close();
	}
	//分开思想: 起始、实际大�?
	public static void test2() throws IOException {
		RandomAccessFile raf =new RandomAccessFile(new File("src/com/sxt/io/Copy.java"),"r");
		//起始位置
		int beginPos =2+1026;
		//实际大小
		int actualSize = 1026;
		//随机读取 
		raf.seek(beginPos);
		//读取
		//3、操�? (分段读取)
		byte[] flush = new byte[1024]; //缓冲容器
		int len = -1; //接收长度
		while((len=raf.read(flush))!=-1) {			
			if(actualSize>len) { //获取本次读取的所有内�?
				System.out.println(new String(flush,0,len));
				actualSize -=len;
			}else { 
				System.out.println(new String(flush,0,actualSize));
				break;
			}
		}			
		
		raf.close();
	}
	
	
	//指定起始位置，读取剩余所有内�?
	public static void test1() throws IOException {
		RandomAccessFile raf =new RandomAccessFile(new File("src/com/sxt/io/Copy.java"),"r");
		//随机读取 
		raf.seek(2);
		//读取
		//3、操�? (分段读取)
		byte[] flush = new byte[1024]; //缓冲容器
		int len = -1; //接收长度
		while((len=raf.read(flush))!=-1) {
			System.out.println(new String(flush,0,len));
		}			
		
		raf.close();
	}

}
