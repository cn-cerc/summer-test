package cn.cerc.sample.services.check;

import cn.cerc.jbean.core.CustomService;
import cn.cerc.jdb.core.TDateTime;
import cn.cerc.jdb.mysql.SqlQuery;
import cn.cerc.sample.common.BaseConfig;

public class SvrCheck extends CustomService {

	public boolean findCheckh() {

		SqlQuery dsH = new SqlQuery(this);
		dsH.add("select * from %s", BaseConfig.Tranh);
		dsH.add("where CorpNo_ = %s", BaseConfig.CorpNo);
		dsH.add("and DeptName_ is not null");
		dsH.open();
		getDataOut().appendDataSet(dsH);
		return true;
	}

	public boolean saveCheckh() {

		String tbNo = getDataIn().getHead().getString("tbNo");
		String tbDate = getDataIn().getHead().getString("tbDate");
		String deptName = getDataIn().getHead().getString("deptName");
		String appUser = getDataIn().getHead().getString("appUser");

		if (!tbNo.substring(0, 2).equals("AE")) {
			throw new RuntimeException("单号格式错误!");
		}

		SqlQuery dsH = new SqlQuery(this);
		dsH.add("select * from %s", BaseConfig.Tranh);
		dsH.add("where CorpNo_ = %s", BaseConfig.CorpNo);
		dsH.add("and TBNo_ = '%s'", tbNo);
		dsH.open();
		if (dsH.eof()) {
			dsH.append();
			dsH.setField("CorpNo_", BaseConfig.CorpNo);
			dsH.setField("TB_", "AE");
			dsH.setField("TBNo_", tbNo);
			dsH.setField("TBDate_", tbDate);
			dsH.setField("DeptName_", deptName);
			dsH.setField("AppUser_", appUser);
			dsH.setField("AppDate_", TDateTime.Now());
			dsH.post();
		} else {
			throw new RuntimeException("单号已存在!");
		}
		return true;
	}

	public boolean findCheck() {

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

	public boolean saveCheckb() {

		String tbNo = getDataIn().getHead().getString("tbNo");
		String code = getDataIn().getHead().getString("code");
		Double actureStock = getDataIn().getHead().getDouble("stock");
		SqlQuery dsPart = new SqlQuery(this);
		dsPart.add("select * from %s", BaseConfig.Product);
		dsPart.add("where CorpNo_ = %s", BaseConfig.CorpNo);
		dsPart.add("and Code_ = '%s'", code);
		dsPart.open();

		double partStock = dsPart.getDouble("Stock_");

		SqlQuery dsB = new SqlQuery(this);
		dsB.add("select * from %s", BaseConfig.Tranb);
		dsB.add("where CorpNo_ = %s", BaseConfig.CorpNo);
		dsB.add("and Code_ = '%s'", code);
		dsB.add("and TBNo_ = '%s'", tbNo);
		dsB.open();

		if (!dsB.eof()) {
			dsB.edit();
			dsB.setField("Num_", actureStock - partStock);
			dsB.setField("CurStock_", partStock);
			dsB.setField("NewStock_", actureStock);
			dsB.post();
			dsPart.edit();
			dsPart.setField("Stock_", actureStock);
			dsPart.post();
		} else {
			dsB.append();
			dsB.setField("CorpNo_", BaseConfig.CorpNo);
			dsB.setField("TBNo_", tbNo);
			dsB.setField("It_", getMaxIt(tbNo));
			dsB.setField("Code_", code);
			dsB.setField("Desc_", dsPart.getField("Desc_"));
			dsB.setField("Spec_", dsPart.getField("Spec_"));
			dsB.setField("Unit_", dsPart.getField("Unit_"));
			dsB.setField("Num_", actureStock - partStock);
			dsB.setField("CurStock_", partStock);
			dsB.setField("NewStock_", actureStock);
			dsB.post();
			dsPart.edit();
			dsPart.setField("Stock_", actureStock);
			dsPart.post();
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

	public boolean checkhModify() {
		String tbNo = getDataIn().getHead().getString("tbNo");
		String deptName = getDataIn().getHead().getString("deptName");
		SqlQuery ds2 = new SqlQuery(this);
		ds2.add("select * from %s", BaseConfig.Tranh);
		ds2.add("where CorpNo_ = %s", BaseConfig.CorpNo);
		ds2.add("and TBNo_ = '%s'", tbNo);
		ds2.open();
		ds2.edit();
		ds2.setField("DeptName_", deptName);
		ds2.post();
		return true;
	}

	public boolean checkbModify() {
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

	public boolean checkbUpdate() {

		// 更新盘点表的表身 1.把原来b表的当前库存 设置为现在表的库存 最后更新商品表
		Double actureStock = getDataIn().getHead().getDouble("num");
		String code = getDataIn().getHead().getString("code");
		String tbNo = getDataIn().getHead().getString("tbNo");

		SqlQuery dsPart = new SqlQuery(this);
		dsPart.add("select * from %s", BaseConfig.Product);
		dsPart.add("where CorpNo_ = %s", BaseConfig.CorpNo);
		dsPart.add("and Code_ = '%s'", code);
		dsPart.open();

		SqlQuery dsB = new SqlQuery(this);

		dsB.add("select * from %s", BaseConfig.Tranb);
		dsB.add("where CorpNo_ = %s", BaseConfig.CorpNo);
		dsB.add("and Code_ = '%s'", code);
		dsB.add("and TBNo_ = '%s'", tbNo);
		dsB.open();
		if (!dsB.eof()) {
			dsB.edit();
			dsB.setField("CurStock_", dsB.getDouble("CurStock_"));
			dsB.setField("NewStock_", actureStock);
			dsB.setField("Num_", actureStock - dsB.getDouble("CurStock_"));
			dsB.post();
			dsPart.edit();
			dsPart.setField("Stock_", actureStock);
			dsPart.post();
		}
		return true;
	}

	public boolean checkbDelete() {

		String code = getDataIn().getHead().getString("code");
		String tbNo = getDataIn().getHead().getString("tbNo");
		SqlQuery dsPart = new SqlQuery(this);
		dsPart.add("select * from %s", BaseConfig.Product);
		dsPart.add("where CorpNo_ = %s", BaseConfig.CorpNo);
		dsPart.add("and Code_ = '%s'", code);
		dsPart.open();
		SqlQuery dsB = new SqlQuery(this);
		dsB.add("select * from %s", BaseConfig.Tranb);
		dsB.add("where CorpNo_ = %s", BaseConfig.CorpNo);
		dsB.add("and TBNo_ = '%s'", tbNo);
		dsB.open();

		if (!dsB.eof()) {
			dsPart.edit();
			dsPart.setField("Stock_", dsPart.getDouble("Stock_") - (dsB.getDouble("Num_")));
			dsPart.post();
			dsB.delete();
		}
		return true;
	}
}
