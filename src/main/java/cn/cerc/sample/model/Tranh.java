package cn.cerc.sample.model;

import java.io.Serializable;
import java.sql.Date;

public class Tranh implements Serializable {
	/**
	 *  TB_	单别
		TBNo_	单号
		SupName_	厂商名称
		CusName_	客户名称
		DeptName_	部门名称
		TBDate_	单据日期
		AppUser_	建档人员
		AppDate_	建档日期

	 */
	private String uid;
	private String tb;
	private String tbNo;
	private String tbDate;
	private String supName;
	private String cusName;
	private String appUser;
	private Date appDate;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getTb() {
		return tb;
	}
	public void setTb(String tb) {
		this.tb = tb;
	}
	public String getTbNo() {
		return tbNo;
	}
	public void setTbNo(String tbNo) {
		this.tbNo = tbNo;
	}
	public String getTbDate() {
		return tbDate;
	}
	public void setTbDate(String tbDate) {
		this.tbDate = tbDate;
	}
	public String getSupName() {
		return supName;
	}
	public void setSupName(String supName) {
		this.supName = supName;
	}
	public String getCusName() {
		return cusName;
	}
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	public String getAppUser() {
		return appUser;
	}
	public void setAppUser(String appUser) {
		this.appUser = appUser;
	}
	public Date getAppDate() {
		return appDate;
	}
	public void setAppDate(Date appDate) {
		this.appDate = appDate;
	}
	
}
