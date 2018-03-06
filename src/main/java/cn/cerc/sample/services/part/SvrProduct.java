package cn.cerc.sample.services.part;

import cn.cerc.jbean.core.CustomService;
import cn.cerc.jdb.mysql.SqlQuery;
import cn.cerc.sample.common.BaseConfig;

public class SvrProduct extends CustomService {

	public boolean search() {

		SqlQuery ds = new SqlQuery(this);
		ds.add("select * from  %s", BaseConfig.Product);
		ds.add("where CorpNo_ = %s", BaseConfig.CorpNo);
		ds.open();
		getDataOut().appendDataSet(ds);
		return true;
	}

	public boolean findById() {

		String code = getDataIn().getHead().getString("code");
		SqlQuery ds = new SqlQuery(this);
		ds.add("select * from %s", BaseConfig.Product);
		ds.add("where CorpNo_ = %s", BaseConfig.CorpNo);
		ds.add("and Code_ = '%s'", code);
		ds.open();
		getDataOut().appendDataSet(ds);
		/* getDataIn().getHead().copyValues(ds.getCurrent()); */
		return true;
	}

	public boolean add() {

		String code = getDataIn().getHead().getString("code");
		String desc = getDataIn().getHead().getString("desc");
		String spec = getDataIn().getHead().getString("spec");
		String unit = getDataIn().getHead().getString("unit");

		// 查询商品编号存在否
		SqlQuery ds2 = new SqlQuery(this);
		ds2.add("select * from %s", BaseConfig.Product);
		ds2.add("where CorpNo_ = %s", BaseConfig.CorpNo);
		ds2.add("and Code_ = '%s'", code);
		ds2.open();
		if (!ds2.eof()) {
			throw new RuntimeException("编号不能重复");
		}
		// 插入数据
		ds2.append();
		ds2.setField("CorpNo_", BaseConfig.CorpNo);
		ds2.setField("Code_", code);
		ds2.setField("Desc_", desc);
		ds2.setField("Spec_", spec);
		ds2.setField("Unit_", unit);
		ds2.setField("Stock_", 0.0);
		ds2.post();
		/*
		 * // 修改数据 ds2.edit(); ds2.setField("", value); ds2.post();
		 */
		/*
		 * // 删除数据 ds2.delete();
		 */
		return true;
	}

	public boolean update() {
		String code = getDataIn().getHead().getString("code");
		String desc = getDataIn().getHead().getString("desc");
		String spec = getDataIn().getHead().getString("spec");
		String unit = getDataIn().getHead().getString("unit");
		SqlQuery ds2 = new SqlQuery(this);
		ds2.add("select * from %s", BaseConfig.Product);
		ds2.add("where CorpNo_ = %s", BaseConfig.CorpNo);
		ds2.add("and Code_ = '%s'", code);
		ds2.open();
		ds2.edit();
		ds2.setField("Code_", code);
		ds2.setField("Desc_", desc);
		ds2.setField("Unit_", unit);
		ds2.setField("Spec_", spec);
		ds2.post();
		return true;

	}

	public boolean delete() {
		String code = getDataIn().getHead().getString("code");
		SqlQuery ds2 = new SqlQuery(this);
		ds2.add("select * from %s", BaseConfig.Product);
		ds2.add("where CorpNo_ = %s", BaseConfig.CorpNo);
		ds2.add("and Code_ = '%s'", code);
		ds2.open();
		ds2.delete();
		return true;
	}
}
