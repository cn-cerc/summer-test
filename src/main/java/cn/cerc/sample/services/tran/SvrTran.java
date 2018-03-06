package cn.cerc.sample.services.tran;

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
}
