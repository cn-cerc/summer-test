package cn.cerc.sample.services;

import cn.cerc.jbean.core.CustomService;
import cn.cerc.jdb.mysql.SqlQuery;
import cn.cerc.sample.base.BaseConfig;

public class ServicePartInfo extends CustomService {
	
	
	public boolean search() {
		SqlQuery sqlQuery=new SqlQuery(this);
		sqlQuery.add("select * from %s",BaseConfig.PRODUCT);
		//sqlQuery.add("where CorpNo_='%s'",BaseConfig.CorpNo);
		sqlQuery.open();
		getDataOut().appendDataSet(sqlQuery);
		
		return true;
	}
	
	

	
	
}
