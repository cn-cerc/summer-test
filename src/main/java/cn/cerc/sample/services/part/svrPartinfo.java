package cn.cerc.sample.services.part;

import cn.cerc.jbean.core.CustomService;
import cn.cerc.jdb.mysql.SqlQuery;
import cn.cerc.sample.common.BaseConfig;

public class svrPartinfo extends CustomService {
    public boolean sch() {
        SqlQuery ds = new SqlQuery(this);
        ds.add("SELECT *FROM %s ", BaseConfig.product);
        ds.add("where CorpNo_='%s'", BaseConfig.CorpNo);
        ds.open();
        getDataOut().appendDataSet(ds);
        return true;
    }
}
