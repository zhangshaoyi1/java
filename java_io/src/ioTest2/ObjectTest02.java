package ioTest2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

/**
 * 对象�?: 1、写出后读取 2、读取的顺序与写出保持一�? 3、不是所有的对象都可以序列化Serializable
 * 
 * ObjectOutputStream ObjectInputStream
 * 
 * @author TW
 *
 */
public class ObjectTest02 {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// 写出 -->序列�?
		ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("obj.ser")));
		// 操作数据类型 +数据
		oos.writeUTF("编码辛酸�?");
		oos.writeInt(18);
		oos.writeBoolean(false);
		oos.writeChar('a');
		// 对象
		oos.writeObject("谁解其中�?");
		oos.writeObject(new Date());
		Employee emp = new Employee("马云", 400);
		oos.writeObject(emp);
		oos.flush();
		oos.close();
		// 读取 -->反序列化
		ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("obj.ser")));
		// 顺序与写出一�?
		String msg = ois.readUTF();
		int age = ois.readInt();
		boolean flag = ois.readBoolean();
		char ch = ois.readChar();
		System.out.println(flag);
		// 对象的数据还�?
		Object str = ois.readObject();
		Object date = ois.readObject();
		Object employee = ois.readObject();

		if (str instanceof String) {
			String strObj = (String) str;
			System.out.println(strObj);
		}
		if (date instanceof Date) {
			Date dateObj = (Date) date;
			System.out.println(dateObj);
		}
		if (employee instanceof Employee) {
			Employee empObj = (Employee) employee;
			System.out.println(empObj.getName() + "-->" + empObj.getSalary());
		}
		ois.close();
	}
}
