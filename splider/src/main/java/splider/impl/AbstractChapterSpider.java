package splider.impl;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import splider.entitys.Chapter;
import splider.interfaces.IChapterSpider;


public class AbstractChapterSpider extends AbstractSpider implements IChapterSpider {
	
	@Override
	public List<Chapter> getsChapter(String url) {
		try {
			String result = crawl(url);
			result =result.replace("&nbsp", "  ").replace("<br />", "\n").replace("<br/>", "\n");
			Document doc = Jsoup.parse(result);
			doc.setBaseUri(url);
			Elements as = doc.select("#list dd a");
			List<Chapter> chapters = new ArrayList<>();
			for (Element a : as) {
				Chapter chapter = new Chapter();
				chapter.setTitle(a.text());
				chapter.setUrl(a.absUrl("href"));
				chapters.add(chapter);
			}
			return chapters;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
