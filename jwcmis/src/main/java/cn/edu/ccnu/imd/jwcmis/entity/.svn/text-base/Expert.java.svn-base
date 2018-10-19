/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.edu.ccnu.imd.jwcmis.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 专家信息管理Entity
 * @author cgq
 * @version 2018-08-13
 */
public class Expert extends DataEntity<Expert> {
	
	private static final long serialVersionUID = 1L;
	private Research research;
	
	private String expertName;		// 专家姓名
	private String major;		// 专业
	private String researchName;		// 研究方向
	private Integer distributeNum;		// 已分配论文数
	private String expertType;		// 专家类型
	private String email;		// 邮箱
	private String phone;		// 手机号
	private String bankCardNumber;		// 银行卡号
	private String place;		// 单位
	
	
	public Expert() {
		super();
	}

	public Expert(String id){
		super(id);
	}
	
	public Expert(Research research){
		this.research = research;
	}
	
	public Research getResearch() {
		return research;
	}
	
	public void setResearch(Research research) {
		this.research = research;
	}

	@Length(min=1, max=20, message="专家名长度必须介于 1 和 20 之间")
	@ExcelField(title="专家姓名",align=2, sort=10)
	public String getExpertName() {
		return expertName;
	}

	public void setExpertName(String expertName) {
		this.expertName = expertName;
	}
	
	@Length(min=1, max=64, message="专业长度必须介于 1 和 64 之间")
	@ExcelField(title="专业",align=2, sort=20)
	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}
	
	@Length(min=1, max=64, message="研究方向长度必须介于 1 和 64 之间")
	@ExcelField(title="研究方向",align=2, sort=30)
	public String getResearchName() {
		return researchName;
	}

	public void setResearchName(String researchName) {
		this.researchName = researchName;
	}
	
	@ExcelField(title="已分配论文数",align=2, sort=40)	
	public Integer getDistributeNum() {
		return distributeNum;
	}

	public void setDistributeNum(Integer distributeNum) {
		this.distributeNum = distributeNum;
	}
	
	@ExcelField(title="专家类型",align=2, sort=50)
	public String getExpertType() {
		return expertType;
	}

	public void setExpertType(String expertType) {
		this.expertType = expertType;
	}

	@Length(min=1, max=64, message="邮箱长度必须介于 1 和 64 之间")
	@ExcelField(title="邮箱",align=2, sort=55)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Length(min=1, max=11, message="手机号长度必须介于 1 和 11 之间")
	@ExcelField(title="电话",align=2, sort=60)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Length(min=1, max=64, message="银行卡号长度必须介于 1 和 64 之间")
	@ExcelField(title="银行卡号",align=2, sort=70)
	public String getBankCardNumber() {
		return bankCardNumber;
	}

	public void setBankCardNumber(String bankCardNumber) {
		this.bankCardNumber = bankCardNumber;
	}

	
	@Length(min=1, max=64, message="单位长度必须介于 1 和 64 之间")
	@ExcelField(title="单位",align=2, sort=80)
	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}
}