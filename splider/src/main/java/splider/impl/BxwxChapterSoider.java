package splider.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import splider.entitys.Chapter;

public class BxwxChapterSoider extends AbstractChapterSpider {
	
	public List<Chapter> getChapter(String url){
		List<Chapter> chapters =super.getsChapter(url);
		Collections.sort(chapters,new Comparator<Chapter>() {

			@Override
			public int compare(Chapter o1, Chapter o2) {
				String o1url = o1.getUrl();
				String o2url = o2.getUrl();
				String o1index = o1url.substring(o1url.lastIndexOf('/')+1, o1url.lastIndexOf('.'));
				String o2index = o2url.substring(o2url.lastIndexOf('/')+1, o2url.lastIndexOf('.'));
				return o1index.compareTo(o2index);
			}
			
		});
		return chapters;
		
	}

}
