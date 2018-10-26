package novel.splider.junit;

import java.util.List;

import org.junit.Test;

import splider.NovelSiteEnum;
import splider.entitys.Chapter;
import splider.impl.DefaultChapterDetailSpider;
import splider.impl.DefaultChapterSpider;
import splider.interfaces.IChapterDetailSpider;
import splider.interfaces.IChapterSpider;
import splider.util.NovelSpiderUtil;


public class Testcase {
	@Test
	public void testGetChapter() throws Exception {
		IChapterSpider spider = new DefaultChapterSpider();
		List<Chapter>  chapters  = spider.getsChapter("http://www.biquge.tw/0_5/");
		for (Chapter chapter : chapters) {
			System.out.println(chapter);
		}
	}
	@Test
	public void testGetSiteContext() {
		System.out.println(NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl("http://www.biquge.tw")));
	}
	@Test
	public void testGetChapterDetail() {
		IChapterDetailSpider spider = new DefaultChapterDetailSpider();
		System.out.println(spider.getChapterDetail("http://www.biquge.tw/0_5/1374.html").getContent());
	}
}
