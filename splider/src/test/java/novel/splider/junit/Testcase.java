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
	 * �ò��Է���������ȡ��������վ���½��б�
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
	 * �ò��Է������ڲ����Ƿ����õ���������վ���½���ϸ����
	 */
	@Test
	public void testGetChapterDetail2() {
		IChapterDetailSpider spider = new DefaultChapterDetailSpider();
		System.out.println(spider.getChapterDetail("http://www.kanshuzhong.com/book/1236/12899575.html"));
	}
	/**
	 * �ò��Է������ڲ����ַ�����ȷ�õ�������ѧ���½���Ϣ����
	 */
	@Test
	public void testGetChapterDetail3() {
		IChapterDetailSpider spider = new DefaultChapterDetailSpider();
		System.out.println(spider.getChapterDetail("http://www.bxwx8.org/b/70/29204416.html"));
	}
}
