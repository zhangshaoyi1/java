package MyTest;

import java.util.ArrayList;
import java.util.List;



public class foreach {

	public static void main(String[] args) {
		List<String> person=new ArrayList<>();
        person.add("jackie");   //索引为0  //.add(e)
        person.add("peter");    //索引为1
        person.add("annie");    //索引为2
        person.add("martin");   //索引为3
        person.add("marry");    //索引为4
         
        person.remove(3);   //.remove(index)
        person.remove("marry");     //.remove(Object o)
         
        String per="";
        per=person.get(1);
        System.out.println(per);    ////.get(index)
         
        for (int i = 0; i < person.size(); i++) {
            System.out.println(person.get(i));  //.get(index)
        }

}
}
