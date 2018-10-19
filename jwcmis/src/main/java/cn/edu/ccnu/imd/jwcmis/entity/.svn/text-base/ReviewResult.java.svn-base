/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.edu.ccnu.imd.jwcmis.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 评审结果管理Entity
 * @author 黑白
 * @version 2018-09-23
 */
public class ReviewResult extends DataEntity<ReviewResult> {
	
	private static final long serialVersionUID = 1L;
	private String thesisid;		// 论文ID
	private String expertid;		// 专家ID
	private String score;		// 分数
	private String comment;		// 评语
	
	public ReviewResult() {
		super();
	}

	public ReviewResult(String id){
		super(id);
	}

	@Length(min=1, max=255, message="论文ID长度必须介于 1 和 255 之间")
	public String getThesisid() {
		return thesisid;
	}

	public void setThesisid(String thesisid) {
		this.thesisid = thesisid;
	}
	
	@Length(min=1, max=255, message="专家ID长度必须介于 1 和 255 之间")
	public String getExpertid() {
		return expertid;
	}

	public void setExpertid(String expertid) {
		this.expertid = expertid;
	}
	
	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
}