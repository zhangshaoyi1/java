package splider.interfaces;

import splider.entitys.ChapterDetail;

public interface IChapterDetailSpider {
	/**
	 * ͨ��url��ȡ��Ӧ��վ���½�����ʵ��
	 * @param url
	 * @return
	 */
	public ChapterDetail getChapterDetail(String url);
}
