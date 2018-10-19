package novel.splider.junit;

import java.util.List;

import org.junit.Test;

import splider.entitys.Chapter;
import splider.impl.DefaultChapterSpider;
import splider.interfaces.IChapterSpider;


public class Testcase {
	@Test
	public void test1() throws Exception {
		IChapterSpider spider = new DefaultChapterSpider();
		List<Chapter>  chapters  = spider.getsChapter("http://www.biquge.tw/0_5/");
		for (Chapter chapter : chapters) {
			System.out.println(chapter);
		}
	}
}
