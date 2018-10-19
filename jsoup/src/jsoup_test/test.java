package jsoup_test;

 
 
/**
 *
 * @author Cloud
 * @data   2016-12-15
 * JsoupTest
 */
 
public class test {
    
    public static void main(String[] args) throws Exception {
        System.out.println("--start--");
        JsoupUtil.getNetworkImage("https://www.baidu.com/", "C:\\Users\\ZSY\\Desktop\\imgs");
        System.out.println("--end--");
    }
}

