package novel.splider.junit;

import java.util.List;

import org.junit.Test;

import splider.NovelSiteEnum;
import splider.entitys.Chapter;
import splider.impl.BxwxChapterSoider;
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
	/**
	 * 该测试方法用来获取看书中网站的章节列表
	 * @throws Exception
	 */
	@Test
	public void testGetsChapter2() throws Exception {
		IChapterSpider spider = new DefaultChapterSpider();
		List<Chapter>  chapters  = spider.getsChapter("http://www.kanshuzhong.com/book/1236/");
		for (Chapter chapter : chapters) {
			System.out.println(chapter);
		}
	}
	@Test
	public void testGetsChapter3() throws Exception {
		IChapterSpider spider = new BxwxChapterSoider();
		List<Chapter>  chapters  = spider.getsChapter("http://www.bxwx8.org/b/70/70093/");
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
	/**
	 * 该测试方法用于测试是否能拿到看书中网站的章节详细内容
	 */
	@Test
	public void testGetChapterDetail2() {
		IChapterDetailSpider spider = new DefaultChapterDetailSpider();
		System.out.println(spider.getChapterDetail("http://www.kanshuzhong.com/book/1236/12899575.html"));
	}
	/**
	 * 该测试方法用于测试手否能正确拿到笔下文学的章节消息内容
	 */
	@Test
	public void testGetChapterDetail3() {
		IChapterDetailSpider spider = new DefaultChapterDetailSpider();
		System.out.println(spider.getChapterDetail("http://www.bxwx8.org/b/70/29204416.html"));
	}
}
