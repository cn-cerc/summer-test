package cn.cerc.sample.services.TranAB;

import cn.cerc.jbean.core.CustomService;
import cn.cerc.jdb.core.Record;
import cn.cerc.jdb.core.TDateTime;
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

    public boolean TranABHsch() {
        Record headIn = getDataIn().getHead();
        String tbno = headIn.getString("TBNo_");
        SqlQuery ds = new SqlQuery(this);
        ds.add("SELECT *FROM %s ", BaseConfig.tranh);
        ds.add("where CorpNo_='%s' and TB_='AB' and TBNo_='%s' ", BaseConfig.CorpNo, tbno);
        ds.open();
        getDataOut().appendDataSet(ds);
        return true;
    }

    public boolean TranHappend() {
        Record headIn = getDataIn().getHead();
        String tbno = headIn.getString("TBNo_");
        SqlQuery ds = new SqlQuery(this);
        ds.add("SELECT * FROM %s", BaseConfig.tranh);
        ds.add("WHERE CorpNo_='%s' and TBNo_='%s'", BaseConfig.CorpNo, tbno);
        ds.open();
        if (!ds.eof()) {
            throw new RuntimeException("此单号已存在，无法增加！");
        }
        ds.append();
        ds.setField("CorpNo_", BaseConfig.CorpNo);
        ds.setField("TBNo_", headIn.getString("TBNo_"));
        ds.setField("TB_", "AB");
        ds.setField("TBDate_", headIn.getString("TBDate_"));
        ds.setField("SupName_", headIn.getString("SupName_"));
        ds.setField("AppUser_", headIn.getString("AppUser_"));
        ds.setField("AppDate_", TDateTime.Now().getDate());
        ds.post();
        return true;
    }

    public boolean TranHmodify() {
        Record headIn = getDataIn().getHead();
        String tbno = headIn.getString("TBNo_");
        String supname = headIn.getString("SupName_");
        SqlQuery ds = new SqlQuery(this);
        ds.add("SELECT * FROM %s", BaseConfig.tranh);
        ds.add("WHERE CorpNo_='%s' and TBNo_='%s' ", BaseConfig.CorpNo, tbno);
        ds.open();
        if (ds.eof()) {
            throw new RuntimeException("此单号不存在，无法修改！");
        }
        ds.edit();
        ds.setField("SupName_", supname);
        ds.post();
        return true;
    }

    public boolean TranBappend() {
        Record headIn = getDataIn().getHead();
        String tbno = headIn.getString("TBNo_");
        String code = headIn.getString("Code_");
        Double num = headIn.getDouble("Num_");
        String desc = "";
        String spec = "";
        String unit = "";
        SqlQuery ds = new SqlQuery(this);
        ds.add("SELECT * FROM %s", BaseConfig.product);
        ds.add("WHERE CorpNo_='%s' and Code_='%s'", BaseConfig.CorpNo, code);
        ds.open();
        if (!ds.eof()) {
            desc = ds.getString("Desc_");
            spec = ds.getString("Spec_");
            unit = ds.getString("Unit_");
        }
        int it = 1;
        SqlQuery dsTran = new SqlQuery(this);
        dsTran.add("SELECT * FROM %s", BaseConfig.tranb);
        dsTran.add("WHERE CorpNo_='%s' and TBNo_='%s' ORDER BY It_ DESC ", BaseConfig.CorpNo, tbno);
        dsTran.open();
        if (!dsTran.eof()) {
            dsTran.first();
            it = dsTran.getInt("It_") + 1;

        }
        dsTran.append();
        dsTran.setField("CorpNo_", BaseConfig.CorpNo);
        dsTran.setField("It_", it);
        dsTran.setField("TBNo_", tbno);
        dsTran.setField("Code_", code);
        dsTran.setField("Desc_", desc);
        dsTran.setField("Spec_", spec);
        dsTran.setField("Unit_", unit);
        dsTran.setField("Num_", num);
        dsTran.post();

        SqlQuery dsproduct = new SqlQuery(this);
        dsproduct.add("SELECT * FROM %s", BaseConfig.product);
        dsproduct.add("WHERE CorpNo_='%s' and Code_='%s' ", BaseConfig.CorpNo, code);
        dsproduct.open();
        if (!dsproduct.eof()) {
            double stock = dsproduct.getDouble("Stock_");
            dsproduct.edit();
            dsproduct.setField("Stock_", stock + num);
            dsproduct.post();
        }
        return true;
    }

    public boolean TranABBsch() {
        Record headIn = getDataIn().getHead();
        String tbno = headIn.getString("TBNo_");
        SqlQuery ds = new SqlQuery(this);
        ds.add("SELECT * FROM %s", BaseConfig.tranb);
        ds.add("WHERE CorpNo_='%s' and TBNo_='%s' ORDER BY It_  ", BaseConfig.CorpNo, tbno);
        ds.open();
        getDataOut().appendDataSet(ds);
        return true;
    }

    public boolean TranABItsch() {
        Record headIn = getDataIn().getHead();
        String tbno = headIn.getString("TBNo_");
        String it = headIn.getString("It_");
        SqlQuery ds = new SqlQuery(this);
        ds.add("SELECT * FROM %s", BaseConfig.tranb);
        ds.add("WHERE CorpNo_='%s' and TBNo_='%s' and It_='%s' ORDER BY It_  ", BaseConfig.CorpNo, tbno, it);
        ds.open();
        getDataOut().appendDataSet(ds);
        return true;
    }

    public boolean TranBmodify() {
        Record headIn = getDataIn().getHead();
        String tbno = headIn.getString("TBNo_");
        String it = headIn.getString("It_");
        String code = headIn.getString("Code_");
        Double num = headIn.getDouble("Num_");
        SqlQuery ds = new SqlQuery(this);
        ds.add("SELECT * FROM %s", BaseConfig.tranb);
        ds.add("WHERE CorpNo_='%s' and TBNo_='%s' and It_='%s' ", BaseConfig.CorpNo, tbno, it);
        ds.open();
        if (ds.eof()) {
            throw new RuntimeException("此单身资料不存在，无法修改！");
        }
        ds.edit();
        ds.setField("Num_", num);
        ds.post();

        SqlQuery dsproduct = new SqlQuery(this);
        dsproduct.add("SELECT * FROM %s", BaseConfig.product);
        dsproduct.add("WHERE CorpNo_='%s' and Code_='%s' ", BaseConfig.CorpNo, code);
        dsproduct.open();
        if (!dsproduct.eof()) {
            double stock = dsproduct.getDouble("Stock_");
            dsproduct.edit();
            dsproduct.setField("Stock_", stock + num);
            dsproduct.post();
        }
        return true;
    }

    public boolean TranBdelete() {
        Record headIn = getDataIn().getHead();
        String tbno = headIn.getString("TBNo_");
        String it = headIn.getString("It_");
        String code = headIn.getString("Code_");
        Double num = headIn.getDouble("Num_");
        SqlQuery ds = new SqlQuery(this);
        ds.add("SELECT * FROM %s", BaseConfig.tranb);
        ds.add("WHERE CorpNo_='%s' and TBNo_='%s' and It_='%s' ", BaseConfig.CorpNo, tbno, it);
        ds.open();
        if (ds.eof()) {
            throw new RuntimeException("此单身资料不存在，无法删除！");
        }
        ds.delete();

        SqlQuery dsproduct = new SqlQuery(this);
        dsproduct.add("SELECT * FROM %s", BaseConfig.product);
        dsproduct.add("WHERE CorpNo_='%s' and Code_='%s' ", BaseConfig.CorpNo, code);
        dsproduct.open();
        if (!dsproduct.eof()) {
            double stock = dsproduct.getDouble("Stock_");
            dsproduct.edit();
            dsproduct.setField("Stock_", stock - num);
            dsproduct.post();
        }
        return true;
    }

}
