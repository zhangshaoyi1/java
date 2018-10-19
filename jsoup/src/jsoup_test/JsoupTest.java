package jsoup_test;


import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupTest {
    public static void main(String[] args) throws IOException {
        //��ȡ�༭�Ƽ�ҳ
        Document document=Jsoup.connect("https://www.zhihu.com/explore/recommendations")
                //ģ���������
                .userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)")
                .get();
        Element main=document.getElementById("zh-recommend-list-full");
        Elements url=main.select("div").select("div:nth-child(2)")
                .select("h2").select("a[class=question_link]");
        for(Element question:url){
            //���href���ֵ������ҳ��ÿ����ע���������
            String URL=question.attr("abs:href");
            //������������ָ���ҳ��
            Document document2=Jsoup.connect(URL)
                    .userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)")
                    .get();
            //����
            Elements title=document2.select("#zh-question-title").select("h2").select("a");
            //��������
            Elements detail=document2.select("#zh-question-detail");
            //�ش�
            Elements answer=document2.select("#zh-question-answer-wrap")
                    .select("div.zm-item-rich-text.expandable.js-collapse-body")
                    .select("div.zm-editable-content.clearfix");
            System.out.println("\n"+"���ӣ�"+URL
                    +"\n"+"���⣺"+title.text()
                    +"\n"+"����������"+detail.text()
                    +"\n"+"�ش�"+answer.text());
            output output = new output();
            output.appendFile("C:\\Users\\ZSY\\Desktop\\test.txt", "\n"+"���ӣ�"+URL
                    +"\n"+"���⣺"+title.text()
                    +"\n"+"����������"+detail.text()
                    +"\n"+"�ش�"+answer.text());
            writer writer =new writer();
            writer.write("C:\\Users\\ZSY\\Desktop\\test1.txt", "\n"+"���ӣ�"+URL
                    +"\n"+"���⣺"+title.text()
                    +"\n"+"����������"+detail.text()
                    +"\n"+"�ش�"+answer.text());
        }   
    }
}

