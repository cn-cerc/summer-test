package cn.cerc.sample.model;

import java.io.Serializable;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

public class Tranb implements Serializable {
	/**
	 *  TBNo_	单号
		It_	序
		Code_	商品编号
		Desc_	品名
		Spec_	规格
		Unit_	单位
		Num_	数量

	 */
	private String uid;
	private String tbNo;
	private String it;
	private String code;
	private String desc;
	private String spec;
	private String unit;
	private String num;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getTbNo() {
		return tbNo;
	}
	public void setTbNo(String tbNo) {
		this.tbNo = tbNo;
	}
	public String getIt() {
		return it;
	}
	public void setIt(String it) {
		this.it = it;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	
	

}
