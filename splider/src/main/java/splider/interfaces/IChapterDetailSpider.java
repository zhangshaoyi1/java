package splider.interfaces;

import splider.entitys.ChapterDetail;

public interface IChapterDetailSpider {
	/**
	 * 通过url获取对应网站的章节内容实体
	 * @param url
	 * @return
	 */
	public ChapterDetail getChapterDetail(String url);
}
