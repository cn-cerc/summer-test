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

	public boolean findByTbNo() {

		String tbNo = getDataIn().getHead().getString("tbNo");
		SqlQuery ds = new SqlQuery(this);
		ds.add("select * from %s", BaseConfig.Tranh);
		ds.add("where CorpNo_ = %s", BaseConfig.CorpNo);
		ds.add("and TBNo_ = '%s'", tbNo);
		ds.open();
		getDataOut().appendDataSet(ds);

		return true;
	}
}
