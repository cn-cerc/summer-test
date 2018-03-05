package cn.cerc.sample.services;

import cn.cerc.jbean.core.CustomService;
import cn.cerc.jdb.mysql.SqlQuery;
import cn.cerc.sample.entity.BaseConfig;

public class ServiceProduct extends CustomService{
	
	public boolean search(){
		SqlQuery ds = new SqlQuery(this);
		ds.add("select * from %s", BaseConfig.product);
		ds.add("where CorpNo_=%s", BaseConfig.CorpNo);
		ds.open();
		
		getDataOut().appendDataSet(ds);
		return true;

	}
}
