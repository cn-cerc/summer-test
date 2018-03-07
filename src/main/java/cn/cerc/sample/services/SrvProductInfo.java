package cn.cerc.sample.services;

import cn.cerc.jbean.core.CustomService;
import cn.cerc.jdb.mysql.SqlQuery;
import cn.cerc.sample.constant.BaseConfig;

public class SrvProductInfo extends CustomService {

    public boolean getData() {
        SqlQuery sqlQuery = new SqlQuery(this);
        sqlQuery.add("select * from %s", BaseConfig.PRODUCT);
        sqlQuery.add("where CorpNo_", BaseConfig.CORPNO);
        sqlQuery.open();
        getDataOut().appendDataSet(sqlQuery);
        return true;
    }

}
