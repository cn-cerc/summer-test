package cn.cerc.sample.services.sale;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.cerc.jbean.core.CustomService;
import cn.cerc.jdb.mysql.SqlQuery;
import cn.cerc.sample.common.BaseConfig;

public class SvrSale extends CustomService {

	public boolean findSaleh() {

		SqlQuery dsH = new SqlQuery(this);
		dsH.add("select * from %s", BaseConfig.Tranh);
		dsH.add("where CorpNo_ = %s", BaseConfig.CorpNo);
		dsH.add("and CusName_ is not null");
		dsH.open();
		getDataOut().appendDataSet(dsH);
		return true;
	}

	public boolean saveSaleh() {

		String tbNo = getDataIn().getHead().getString("tbNo");
		String tbDate = getDataIn().getHead().getString("tbDate");
		String cusName = getDataIn().getHead().getString("cusName");
		String appUser = getDataIn().getHead().getString("appUser");

		if (!tbNo.substring(0, 2).equals("BC")) {
			throw new RuntimeException("单号格式错误");
		}
		SqlQuery dsH = new SqlQuery(this);
		dsH.add("select * from %s", BaseConfig.Tranh);
		dsH.add("where CorpNo_ = %s", BaseConfig.CorpNo);
		dsH.add("and TBNo_ = '%s'", tbNo);
		dsH.open();
		if (!dsH.eof()) {
			throw new RuntimeException("单号已存在");
		}
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String format = simple.format(date);
		dsH.append();
		dsH.setField("CorpNo_", BaseConfig.CorpNo);
		dsH.setField("Tb_", "BC");
		dsH.setField("TBNO_", tbNo);
		dsH.setField("TBDate_", tbDate);
		dsH.setField("CusName_", cusName);
		dsH.setField("AppUser_", appUser);
		dsH.setField("AppDate_", format);
		dsH.post();
		return true;
	}

	public boolean findSale() {

		String tbNo = getDataIn().getHead().getString("tbNo");
		SqlQuery dsH = new SqlQuery(this);
		dsH.add("select * from %s", BaseConfig.Tranh);
		dsH.add("where CorpNo_ = %s", BaseConfig.CorpNo);
		dsH.add("and TBNo_ = '%s'", tbNo);
		dsH.open();
		getDataOut().getHead().copyValues(dsH.getCurrent());

		SqlQuery dsB = new SqlQuery(this);
		dsB.add("select * from %s", BaseConfig.Tranb);
		dsB.add("where CorpNo_ = %s", BaseConfig.CorpNo);
		dsB.add("and TBNo_ = '%s'", tbNo);
		dsB.open();
		getDataOut().appendDataSet(dsB);

		return true;
	}

	public boolean salebAdd() {

		String tbNo = getDataIn().getHead().getString("tbNo");
		String code = getDataIn().getHead().getString("code");
		Double saleStock = getDataIn().getHead().getDouble("stock");
		SqlQuery dsPart = new SqlQuery(this);
		dsPart.add("select * from %s", BaseConfig.Product);
		dsPart.add("where CorpNo_ = %s", BaseConfig.CorpNo);
		dsPart.add("and Code_ = '%s'", code);
		dsPart.open();

		double partStock = dsPart.getDouble("Stock_");
		if (partStock - saleStock > 0) {
			dsPart.edit();
			dsPart.setField("Stock_", partStock - saleStock);
			dsPart.post();
		} else {
			throw new RuntimeException("商品库存不足");
		}

		SqlQuery dsB = new SqlQuery(this);
		dsB.add("select * from %s", BaseConfig.Tranb);
		dsB.add("where CorpNo_ = %s", BaseConfig.CorpNo);
		dsB.add("and Code_ = '%s'", code);
		dsB.add("and TBNo_ = '%s'", tbNo);
		dsB.open();

		if (!dsB.eof()) {
			dsB.edit();
			dsB.setField("Num_", dsB.getDouble("Num_") + saleStock);
			dsB.post();
		} else {
			dsB.append();
			dsB.setField("CorpNo_", BaseConfig.CorpNo);
			dsB.setField("TBNo_", tbNo);
			dsB.setField("It_", getMaxIt(tbNo));
			dsB.setField("Code_", code);
			dsB.setField("Desc_", dsPart.getField("Desc_"));
			dsB.setField("Spec_", dsPart.getField("Spec_"));
			dsB.setField("Unit_", dsPart.getField("Unit_"));
			dsB.setField("Num_", saleStock);
			dsB.post();
		}

		return true;
	}

	private int getMaxIt(String tbNo) {
		SqlQuery ds = new SqlQuery(this);
		ds.add("select max(It_) as It_ from %s", BaseConfig.Tranb);
		ds.add("where CorpNo_='%s' and TBNo_='%s'", BaseConfig.CorpNo, tbNo);
		ds.open();
		return ds.getInt("It_") + 1;
	}

	public boolean salebModify() {
		String code = getDataIn().getHead().getString("code");
		String tbNo = getDataIn().getHead().getString("tbNo");
		SqlQuery ds = new SqlQuery(this);
		ds.add("select * from %s", BaseConfig.Tranb);
		ds.add("where CorpNo_ = %s", BaseConfig.CorpNo);
		ds.add("and Code_ = '%s' and TBNo_ = '%s'", code, tbNo);
		ds.open();
		getDataOut().getHead().copyValues(ds.getCurrent());
		return true;
	}

	public boolean salebUpdate() {

		Double num = getDataIn().getHead().getDouble("num");
		String code = getDataIn().getHead().getString("code");
		String tbNo = getDataIn().getHead().getString("tbNo");

		SqlQuery dsPart = new SqlQuery(this);
		dsPart.add("select * from %s", BaseConfig.Product);
		dsPart.add("where CorpNo_ = '%s'", BaseConfig.CorpNo);
		dsPart.add("and Code_ ='%s'", code);
		dsPart.open();

		if (dsPart.getDouble("Stock_") - num > 0) {
			SqlQuery dsB = new SqlQuery(this);
			dsB.add("select * from %s", BaseConfig.Tranb);
			dsB.add("where CorpNo_ = %s", BaseConfig.CorpNo);
			dsB.add("and Code_ = '%s'", code);
			dsB.add("and TBNo_ = '%s'", tbNo);
			dsB.open();
			dsPart.edit();
			dsPart.setField("Stock_", dsPart.getDouble("Stock_") - num + dsB.getDouble("Num_"));
			dsPart.post();

			dsB.edit();
			dsB.setField("Num_", num);
			dsB.post();
		} else {
			throw new RuntimeException("库存不足");
		}

		return true;
	}

	public boolean salebDelete() {

		SqlQuery dsB = new SqlQuery(this);
		Double num = getDataIn().getHead().getDouble("num");
		String code = getDataIn().getHead().getString("code");
		String tbNo = getDataIn().getHead().getString("tbNo");
		dsB.add("select * from %s", BaseConfig.Tranb);
		dsB.add("where CorpNo_ = %s", BaseConfig.CorpNo);
		dsB.add("and Code_ = '%s'", code);
		dsB.add("and TBNo_ = '%s'", tbNo);
		dsB.open();

		dsB.delete();

		SqlQuery dsPart = new SqlQuery(this);
		dsPart.add("select * from %s", BaseConfig.Product);
		dsPart.add("where CorpNo_ = '%s'", BaseConfig.CorpNo);
		dsPart.add("and Code_ ='%s'", code);
		dsPart.open();
		dsPart.edit();
		dsPart.setField("Stock_", dsPart.getDouble("Stock_") + num);
		dsPart.post();

		return true;
	}

	public boolean salehModify() {
		String tbNo = getDataIn().getHead().getString("tbNo");
		String cusName = getDataIn().getHead().getString("cusName");
		SqlQuery ds2 = new SqlQuery(this);
		ds2.add("select * from %s", BaseConfig.Tranh);
		ds2.add("where CorpNo_ = %s", BaseConfig.CorpNo);
		ds2.add("and TBNo_ = '%s'", tbNo);
		ds2.open();
		ds2.edit();
		ds2.setField("CusName_", cusName);
		ds2.post();
		return true;
	}

}
