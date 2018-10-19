package MyTest;

public class shuzu {
	
	public static void main(String[] args) {
		b b = new b();
		String a[] = new String[2];
		a = b.han();
		System.out.println(a[0]);
	}

}
class b{
	public  String[] han(){
		String a[] = {"11","22"};
		return a;
	}
}
