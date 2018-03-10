package cn.cerc.sample.pojo;

import cn.cerc.jdb.core.TDate;
import cn.cerc.jdb.core.TDateTime;

public class TranH {

	private String corpNo;
	private String tb;
	private String tbNo;
	private TDate tbDate;
	private String supName;

	private String appUser;
	private TDateTime appDate;

	public String getCorpNo() {
		return corpNo;
	}

	public void setCorpNo(String corpNo) {
		this.corpNo = corpNo;
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

	public TDate getTbDate() {
		return tbDate;
	}

	public void setTbDate(TDate tbDate) {
		this.tbDate = tbDate;
	}

	public TDateTime getAppDate() {
		return appDate;
	}

	public void setAppDate(TDateTime appDate) {
		this.appDate = appDate;
	}

	public String getSupName() {
		return supName;
	}

	public void setSupName(String supName) {
		this.supName = supName;
	}

	public String getAppUser() {
		return appUser;
	}

	public void setAppUser(String appUser) {
		this.appUser = appUser;
	}

}
