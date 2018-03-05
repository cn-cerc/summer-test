package cn.cerc.sample.model;

import java.io.Serializable;

public class Product implements Serializable {
	/**
	 *  Code_	商品编号
		Desc_	品名
		Spec_	规格
		Unit_	单位
		Stock_	库存

	 */
	private String uid;
	private String corpNo;
	private String code;
	private String desc;
	private String spec;
	private String unit;
	private String stock;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getCorpNo() {
		return corpNo;
	}
	public void setCorpNo(String corpNo) {
		this.corpNo = corpNo;
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
	public String getStock() {
		return stock;
	}
	public void setStock(String stock) {
		this.stock = stock;
	}

	
	
	
}
