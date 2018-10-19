/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.edu.ccnu.imd.gsmis.entity;

import java.util.List;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 分配结果管理Entity
 * @author zjy
 * @version 2018-08-16
 */
public class DistributeResult extends DataEntity<DistributeResult> {
	
	private static final long serialVersionUID = 1L;
	private String thesisId;		// 论文ID
	private String topic;			// 论文名
	private String masterName;		// 硕士名
	private String studentID;		//学号
	private String keywords;		// 关键词
	private String inExpertId;		// 校内专家ID
	private String outExpertId;		// 外校专家ID
	private String status;			// 分配状态
	private String emailstatus;		//论文发送状态
	private String url;				//网址
	private String inemail;			//校内邮件
	private String outemail;		//校外邮件
	private String inexpertName;	//校内专家名
	private String outexpertName;	//校外专家名
	
	public DistributeResult() {
		super();
	}

	public DistributeResult(String id){
		super(id);
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public DistributeResult(String thesisId,String inExpertId,String outExpertId){
		this.thesisId = thesisId;
		this.inExpertId = inExpertId;
		this.outExpertId = outExpertId;
	}
	
	public String getStudentID() {
		return studentID;
	}

	public String getEmailstatus() {
		return emailstatus;
	}

	public void setEmailstatus(String emailstatus) {
		this.emailstatus = emailstatus;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	private List<Account> accountList = Lists.newArrayList();//账号,密码
	
	public List<Account> getAccountList() {
		return accountList;
	}

	public void setAccountList(List<Account> accountList) {
		this.accountList = accountList;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	

	public String getInemail() {
		return inemail;
	}

	public void setInemail(String inemail) {
		this.inemail = inemail;
	}

	public String getInexpertName() {
		return inexpertName;
	}

	public void setInexpertName(String inexpertName) {
		this.inexpertName = inexpertName;
	}

	public String getOutexpertName() {
		return outexpertName;
	}
	
	public void setOutexpertName(String outexpertName) {
		this.outexpertName = outexpertName;
	}
	
	public String getOutemail() {
		return outemail;
	}

	public void setOutemail(String outemail) {
		this.outemail = outemail;
	}

	public String getThesisId() {
		return thesisId;
	}

	public void setThesisId(String thesisId) {
		this.thesisId = thesisId;
	}

	public String getInExpertId() {
		return inExpertId;
	}

	public void setInExpertId(String inExpertId) {
		this.inExpertId = inExpertId;
	}

	public String getOutExpertId() {
		return outExpertId;
	}

	public void setOutExpertId(String outExpertId) {
		this.outExpertId = outExpertId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMasterName() {
		return masterName;
	}

	public void setMasterName(String masterName) {
		this.masterName = masterName;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
}