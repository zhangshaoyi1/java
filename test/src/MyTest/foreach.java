package MyTest;

import java.util.ArrayList;
import java.util.List;



public class foreach {

	public static void main(String[] args) {
		List<String> person=new ArrayList<>();
        person.add("jackie");   //����Ϊ0  //.add(e)
        person.add("peter");    //����Ϊ1
        person.add("annie");    //����Ϊ2
        person.add("martin");   //����Ϊ3
        person.add("marry");    //����Ϊ4
         
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
