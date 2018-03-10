package cn.cerc.sample.services.tran;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.cerc.jbean.core.CustomService;
import cn.cerc.jdb.mysql.SqlQuery;
import cn.cerc.sample.common.BaseConfig;

public class SvrTran extends CustomService {

	public boolean search() {

		SqlQuery ds = new SqlQuery(this);
		ds.add("select * from  %s", BaseConfig.Tranh);
		ds.add("where CorpNo_ = %s", BaseConfig.CorpNo);
		ds.add("and SupName_ is not null");
		ds.open();
		getDataOut().appendDataSet(ds);
		return true;
	}

	public boolean find() {

		String tbNo = getDataIn().getHead().getString("tbNo");
		String tbDate = getDataIn().getHead().getString("tbDate");
		String supName = getDataIn().getHead().getString("supName");
		String appUser = getDataIn().getHead().getString("appUser");

		boolean equals = tbNo.substring(0, 2).equals("AB");
		if (!equals) {
			throw new RuntimeException("单号格式错误");
		}
		// 查询商品编号存在否
		SqlQuery ds2 = new SqlQuery(this);
		ds2.add("select * from %s", BaseConfig.Tranh);
		ds2.add("where CorpNo_ = %s", BaseConfig.CorpNo);
		ds2.add("and TBNo_ = '%s'", tbNo);
		ds2.open();

		if (!ds2.eof()) {
			throw new RuntimeException("单号不能重复");
		}
		// 插入数据
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String format = simple.format(date);
		ds2.append();
		ds2.setField("CorpNo_", BaseConfig.CorpNo);
		ds2.setField("Tb_", "AB");
		ds2.setField("TBNO_", tbNo);
		ds2.setField("TBDate_", tbDate);
		ds2.setField("SupName_", supName);
		ds2.setField("AppUser_", appUser);
		ds2.setField("AppDate_", format);
		ds2.post();

		/*
		 * SqlQuery ds = new SqlQuery(this); ds.add("select * from  %s",
		 * BaseConfig.Tranh); ds.add("where CorpNo_ = %s", BaseConfig.CorpNo);
		 * ds.add("and TBNo_ = '%s'", tbNo); ds.open(); getDataOut().appendDataSet(ds);
		 */
		return true;
	}

	public boolean tranhFindByTbNo() {

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

	public boolean tranhModify() {
		String tbNo = getDataIn().getHead().getString("tbNo");
		String supName = getDataIn().getHead().getString("supName");
		SqlQuery ds2 = new SqlQuery(this);
		ds2.add("select * from %s", BaseConfig.Tranh);
		ds2.add("where CorpNo_ = %s", BaseConfig.CorpNo);
		ds2.add("and TBNo_ = '%s'", tbNo);
		ds2.open();
		ds2.edit();
		ds2.setField("SupName_", supName);
		ds2.post();
		return true;
	}

	public boolean tranbAdd() {

		String code = getDataIn().getHead().getString("code");
		String tbNo = getDataIn().getHead().getString("tbNo");
		Double stock = getDataIn().getHead().getDouble("stock");
		SqlQuery dsPart = new SqlQuery(this);
		dsPart.add("select * from %s", BaseConfig.Product);
		dsPart.add("where CorpNo_ = '%s'", BaseConfig.CorpNo);
		dsPart.add("and Code_ ='%s'", code);
		dsPart.open();
		dsPart.edit();
		dsPart.setField("Stock_", dsPart.getDouble("Stock_") + stock);
		dsPart.post();

		SqlQuery dsB = new SqlQuery(this);
		dsB.add("select * from %s", BaseConfig.Tranb);
		dsB.add("where CorpNo_ = %s", BaseConfig.CorpNo);
		dsB.add("and Code_ = '%s'", code);
		dsB.add("and TBNo_ ='%s'", tbNo);
		dsB.open();
		if (dsB.eof()) {
			dsB.append();
			dsB.setField("CorpNo_", BaseConfig.CorpNo);
			dsB.setField("TBNo_", tbNo);
			dsB.setField("It_", getMaxIt(tbNo));
			dsB.setField("Code_", code);
			dsB.setField("Desc_", dsPart.getField("Desc_"));
			dsB.setField("Spec_", dsPart.getField("Spec_"));
			dsB.setField("Unit_", dsPart.getField("Unit_"));
			dsB.setField("Num_", stock);
			dsB.post();
		} else {
			dsB.edit();
			dsB.setField("Num_", dsB.getDouble("Num_") + stock);
			dsB.post();
		}

		/*
		 * SqlQuery ds2 = new SqlQuery(this); ds2.add("select * from %s",
		 * BaseConfig.Product); ds2.add("where CorpNo_ = %s", BaseConfig.CorpNo);
		 * ds2.add("and Code_ = '%s'", code); ds2.open(); DataSet dataOut2 =
		 * getDataOut().appendDataSet(ds2);
		 * 
		 * SqlQuery ds = new SqlQuery(this); ds.add("select * from %s",
		 * BaseConfig.Tranb); ds.add("where CorpNo_ = %s", BaseConfig.CorpNo);
		 * ds.add("and Code_ = '%s'", code); ds.open(); DataSet dataOut =
		 * getDataOut().appendDataSet(ds); // 如果有的话库存增加 if (dataOut != null) {
		 * ds2.edit(); ds2.setField("Num_", "Num_ +" + dataOut.getDouble("Num_"));
		 * return true; } // 如果没有的话新增商品 ds.append();
		 * 
		 * while(dataOut2.fetch())
		 * 
		 * { ds.setField("CorpNo_", BaseConfig.CorpNo); ds.setField("TBNo_", tbNo);
		 * ds.setField("It_", 1); ds.setField("Code_", dataOut2.getField("Code_"));
		 * ds.setField("Desc_", dataOut2.getField("Desc_")); ds.setField("Spec_",
		 * dataOut2.getField("Spec_")); ds.setField("Unit_",
		 * dataOut2.getField("Unit_")); ds.setField("Num_", stock); }ds.post();
		 */
		return true;
	}

	private int getMaxIt(String tbNo) {
		SqlQuery ds = new SqlQuery(this);
		ds.add("select max(It_) as It_ from %s", BaseConfig.Tranb);
		ds.add("where CorpNo_='%s' and TBNo_='%s'", BaseConfig.CorpNo, tbNo);
		ds.open();
		return ds.getInt("It_") + 1;
	}

	/*
	 * public boolean tranbFindByTbNo() {
	 * 
	 * String tbNo = getDataIn().getHead().getString("tbNo"); SqlQuery ds = new
	 * SqlQuery(this);
	 * ds.add("select * from %s ,%s where %s.TBNo_ = %s.TBNo_ AND %s.TBNo_ = '%s' ",
	 * BaseConfig.Tranb, BaseConfig.Tranh, BaseConfig.Tranb, BaseConfig.Tranh,
	 * BaseConfig.Tranb, tbNo);
	 * 
	 * ds.open(); getDataOut().appendDataSet(ds);
	 * 
	 * return true; }
	 */
	public boolean tranbModify() {
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

	public boolean tranbUpdate() {

		SqlQuery dsB = new SqlQuery(this);
		Double num = getDataIn().getHead().getDouble("num");
		String code = getDataIn().getHead().getString("code");
		String tbNo = getDataIn().getHead().getString("tbNo");
		dsB.add("select * from %s", BaseConfig.Tranb);
		dsB.add("where CorpNo_ = %s", BaseConfig.CorpNo);
		dsB.add("and Code_ = '%s'", code);
		dsB.add("and TBNo_ = '%s'", tbNo);
		dsB.open();

		SqlQuery dsPart = new SqlQuery(this);
		dsPart.add("select * from %s", BaseConfig.Product);
		dsPart.add("where CorpNo_ = '%s'", BaseConfig.CorpNo);
		dsPart.add("and Code_ ='%s'", code);
		dsPart.open();
		dsPart.edit();
		dsPart.setField("Stock_", dsPart.getDouble("Stock_") - dsB.getDouble("Num_") + num);
		dsPart.post();

		dsB.edit();
		dsB.setField("Num_", num);
		dsB.post();
		return true;
	}

	public boolean tranbDelete() {

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
		dsPart.setField("Stock_", dsPart.getDouble("Stock_") - num);
		dsPart.post();

		return true;
	}

}
