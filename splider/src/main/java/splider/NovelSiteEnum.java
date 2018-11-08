package splider;

/**
 * �Ѿ���֧�ֵ�С˵��վö��
 * @author LiuKeFeng
 * @date   2016��10��9��
 */
public enum NovelSiteEnum {
	DingDianXiaoShuo(1, "23wx.com"),
	BiQuGe(2, "biquge.tw"),
	KanShuZhong(3, "kanshuzhong.com"),
	Bxwx(4, "bxwx8.org");
	private int id;
	private String url;
	private NovelSiteEnum(int id, String url) {
		this.id = id;
		this.url = url;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public static NovelSiteEnum getEnumById(int id) {
		switch (id) {
		case 1 : return DingDianXiaoShuo;
		case 2 : return BiQuGe;
		case 3 : return KanShuZhong;
		case 4 : return Bxwx;
		default : throw new RuntimeException("id=" + id + "�ǲ���֧�ֵ�С˵��վ");
		}
	}
	public static NovelSiteEnum getEnumByUrl(String url) {
		for (NovelSiteEnum novelSiteEnum : values()) {
			if (url.contains(novelSiteEnum.url)) {
				return novelSiteEnum;
			}
		}
		throw new RuntimeException("url=" + url + "�ǲ���֧�ֵ�С˵��վ");
	}
}
