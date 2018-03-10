package cn.cerc.sample.pojo;

public class Product {

	private String corpNo;
	private String code;
	private String desc;
	private String spec;
	private String unit;
	private Double stock;
	
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
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
	public Double getStock() {
		return stock;
	}
	public void setStock(Double stock) {
		this.stock = stock;
	}
	@Override
	public String toString() {
		return "Product [corpNo=" + corpNo + ", code=" + code + ", desc=" + desc + ", spec=" + spec + ", unit=" + unit
				+ ", stock=" + stock + "]";
	}
	
	
	
}
