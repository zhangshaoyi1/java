package jsoup_test;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class firstJsoup {
	public static void main(String[] args) throws IOException {
		Document document = Jsoup.connect("http://www.cnblogs.com/")
				.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36")
				.get();
		Element element = document.getElementById("post_list");
		Elements element1 = element.getElementsByClass("post_item");
		
		for(Element e:element1) {
			System.out.println(e.select(".post_item_body .titlelnk h3 a").text());
			System.out.println(e.select(".post_item_body .titlelnk h3 a").attr("href"));
			//System.out.println(e.select(".post_item_summary").text());
		}
		
		
		
	}
}
