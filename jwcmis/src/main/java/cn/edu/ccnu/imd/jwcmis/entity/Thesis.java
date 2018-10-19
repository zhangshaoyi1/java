/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.edu.ccnu.imd.jwcmis.entity;

import org.hibernate.validator.constraints.Length;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;
import java.util.List;
import com.google.common.collect.Lists;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 论文信息管理Entity
 * @author hyf
 * @version 2018-08-16
 */
public class Thesis extends DataEntity<Thesis> {
	
	private static final long serialVersionUID = 1L;
	private String reviewdepart;		// 审评单位
	private Date reviewdeadline;		// 审评截止时间
	private String studentid;		// 学号
	private String mastername;		// 硕士名
	private String supervisor;		// 导师
	private String thesisnumber;		// 论文编号
	private String major;		// 专业
	private String topic;		// 论文主题
	private String studentClass;//学生类别
	private String keywords;		// keywords
	private String status;		// 状态1表示论文已分配0表示未分配
	private String url;		// 网址
	private String username;//用户名
	private String password;//网址
	private List<Account> accountList = Lists.newArrayList();		// 子表列表
	
	public Thesis() {
		super();
	}

	public Thesis(String id){
		super(id);
	}
	//thesis构造函数
	public Thesis(String reviewdepart, Date reviewdeadline, String studentid, String mastername, String supervisor,
			String thesisnumber, String major, String topic, String studentClass, String keywords, String status,
			String url, String username, String password) {
		super();
		this.reviewdepart = reviewdepart;
		this.reviewdeadline = reviewdeadline;
		this.studentid = studentid;
		this.mastername = mastername;
		this.supervisor = supervisor;
		this.thesisnumber = thesisnumber;
		this.major = major;
		this.topic = topic;
		this.studentClass = studentClass;
		this.keywords = keywords;
		this.status = status;
		this.url = url;
		this.username = username;
		this.password = password;
	}
	@Length(min=1, max=64, message="审评单位长度必须介于 1 和 64 之间")
	@ExcelField(title="评审单位",align=2,sort=1)
	public String getReviewdepart() {
		return reviewdepart;
	}

	public void setReviewdepart(String reviewdepart) {
		this.reviewdepart = reviewdepart;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message="审评截止时间不能为空")
	@ExcelField(title="审评截止时间",align=2,sort=10)
	public Date getReviewdeadline() {
		return reviewdeadline;
	}

	public void setReviewdeadline(Date reviewdeadline) {
			this.reviewdeadline = reviewdeadline;
	}
	
	@Length(min=1, max=20, message="学号长度必须介于 1 和 20 之间")
	@ExcelField(title="学号",align=2,sort=20)
	public String getStudentid() {
		return studentid;
	}

	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}
	
	@Length(min=1, max=20, message="硕士名长度必须介于 1 和 20 之间")
	@ExcelField(title="姓名",align=2,sort=30)
	public String getMastername() {
		return mastername;
	}

	public void setMastername(String mastername) {
		this.mastername = mastername;
	}
	
	@Length(min=1, max=20, message="导师长度必须介于 1 和 20 之间")
	@ExcelField(title="导师姓名",align=2,sort=40)
	public String getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}
	
	@Length(min=1, max=64, message="论文编号长度必须介于 1 和 64 之间")
	@ExcelField(title="论文编号",align=2,sort=50)
	public String getThesisnumber() {
		return thesisnumber;
	}

	public void setThesisnumber(String thesisnumber) {
		this.thesisnumber = thesisnumber;
	}
	
	@Length(min=1, max=20, message="专业长度必须介于 1 和 20 之间")
	@ExcelField(title="专业名称",align=2,sort=60)
	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}
	
	@Length(min=1, max=64, message="论文主题长度必须介于 1 和 64 之间")
	@ExcelField(title="论文题目",align=2,sort=80)
	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}
	
	public String getStudentClass() {
		return studentClass;
	}
	@ExcelField(title="学生类别",align=2,sort=90)
	public void setStudentClass(String studentClass) {
		this.studentClass = studentClass;
	}

	@Length(min=1, max=255, message="keywords长度必须介于 1 和 255 之间")
	@ExcelField(title="关键字",align=2,sort=70)
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
	@Length(min=1, max=1, message="状态2表示已评审状态1表示论文已分配未评审0表示未分配长度必须介于 1 和 10 之间")
	@ExcelField(title="评审状态",align=2,sort=100)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	@ExcelField(title="用户名",align=2,sort=110)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@ExcelField(title="密码",align=2,sort=120)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	@Length(min=1, max=64, message="网址长度必须介于 1 和 64 之间")
	@ExcelField(title="登录网址",align=2,sort=130)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public List<Account> getAccountList() {
		return accountList;
	}

	public void setAccountList(List<Account> accountList) {
		this.accountList = accountList;
	}
}