package cn.cerc.sample.services.TranAB;

import java.util.Date;

import cn.cerc.jbean.core.CustomService;
import cn.cerc.jdb.core.Record;
import cn.cerc.jdb.mysql.SqlQuery;
import cn.cerc.sample.common.BaseConfig;

public class SvrTranABInfo extends CustomService {
    public boolean sch() {
        SqlQuery ds = new SqlQuery(this);
        ds.add("SELECT *FROM %s ", BaseConfig.tranh);
        ds.add("where CorpNo_='%s' and TB_='AB'", BaseConfig.CorpNo);
        ds.open();
        getDataOut().appendDataSet(ds);
        return true;
    }

    public boolean append() {
        Record headIn = getDataIn().getHead();
        String tbno = headIn.getString("TBNo_");
        Date date = new Date();
        SqlQuery ds = new SqlQuery(this);
        ds.add("SELECT * FROM %s", BaseConfig.tranh);
        ds.add("WHERE CorpNo_='%s' and TBNo_='%s'", BaseConfig.CorpNo, tbno);
        ds.open();
        if (!ds.eof()) {
            throw new RuntimeException("此商品料号已存在，无法增加！");
        }
        ds.append();
        ds.setField("CorpNo_", BaseConfig.CorpNo);
        ds.setField("TBNo_", headIn.getString("TBNo_"));
        ds.setField("TB_", "AB");
        ds.setField("TBDate_", headIn.getString("TBDate_"));
        ds.setField("SupName_", headIn.getString("SupName_"));
        ds.setField("AppUser_", headIn.getString("AppUser_"));
        ds.setField("AppDate_", date);
        ds.post();
        return true;
    }
}
