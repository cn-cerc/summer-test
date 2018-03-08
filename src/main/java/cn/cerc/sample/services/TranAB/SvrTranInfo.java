package cn.cerc.sample.services.TranAB;

import cn.cerc.jbean.core.CustomService;
import cn.cerc.jdb.core.Record;
import cn.cerc.jdb.core.TDateTime;
import cn.cerc.jdb.mysql.SqlQuery;
import cn.cerc.jdb.other.utils;
import cn.cerc.sample.common.BaseConfig;

public class SvrTranInfo extends CustomService {
    public boolean sch() {
        Record headIn = getDataIn().getHead();
        String tb = headIn.getString("TB_");
        SqlQuery ds = new SqlQuery(this);
        ds.add("SELECT *FROM %s ", BaseConfig.tranh);
        ds.add("where CorpNo_='%s' and TB_='%s'", BaseConfig.CorpNo, tb);
        ds.open();
        getDataOut().appendDataSet(ds);
        return true;
    }

    public boolean TranHsch() {
        Record headIn = getDataIn().getHead();
        String tbno = headIn.getString("TBNo_");
        SqlQuery ds = new SqlQuery(this);
        ds.add("SELECT *FROM %s ", BaseConfig.tranh);
        ds.add("where CorpNo_='%s' and TBNo_='%s' ", BaseConfig.CorpNo, tbno);
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
        ds.setField("TB_", utils.copy(tbno, 1, 2));
        ds.setField("TBDate_", headIn.getString("TBDate_"));
        ds.setField("SupName_", headIn.getString("SupName_"));
        ds.setField("CusName_", headIn.getString("CusName_"));
        ds.setField("AppUser_", headIn.getString("AppUser_"));
        ds.setField("AppDate_", TDateTime.Now().getDate());
        ds.post();
        return true;
    }

    public boolean TranHmodify() {
        Record headIn = getDataIn().getHead();
        String tbno = headIn.getString("TBNo_");
        SqlQuery ds = new SqlQuery(this);
        ds.add("SELECT * FROM %s", BaseConfig.tranh);
        ds.add("WHERE CorpNo_='%s' and TBNo_='%s' ", BaseConfig.CorpNo, tbno);
        ds.open();
        if (ds.eof()) {
            throw new RuntimeException("此单号不存在，无法修改！");
        }
        ds.edit();
        ds.setField("SupName_", headIn.getString("SupName_"));
        ds.setField("CusName_", headIn.getString("CusName_"));
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
        } else {
            throw new RuntimeException("此商品编号不存在，无法增加！");
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
        if (!dsTran.locate("Code_", code)) {
            dsTran.append();
            dsTran.setField("It_", it);
            dsTran.setField("Num_", num);
            dsTran.setField("CorpNo_", BaseConfig.CorpNo);
            dsTran.setField("TBNo_", tbno);
            dsTran.setField("Code_", code);
            dsTran.setField("Desc_", desc);
            dsTran.setField("Spec_", spec);
            dsTran.setField("Unit_", unit);
        } else {
            dsTran.edit();
            dsTran.setField("Num_", num + dsTran.getDouble("Num_"));
        }
        dsTran.post();
        double stock = ds.getDouble("Stock_");
        ds.edit();
        if (tbno.startsWith("AB")) {
            ds.setField("Stock_", stock + num);
        } else if (tbno.startsWith("BC")) {
            ds.setField("Stock_", stock - num);
        }

        ds.post();
        return true;
    }

    public boolean TranBsch() {
        Record headIn = getDataIn().getHead();
        String tbno = headIn.getString("TBNo_");
        SqlQuery ds = new SqlQuery(this);
        ds.add("SELECT * FROM %s", BaseConfig.tranb);
        ds.add("WHERE CorpNo_='%s' and TBNo_='%s' ORDER BY It_  ", BaseConfig.CorpNo, tbno);
        ds.open();
        getDataOut().appendDataSet(ds);
        return true;
    }

    public boolean TranItsch() {
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
        Double oldnum = ds.getDouble("Num_");
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

            if (tbno.startsWith("AB")) {
                dsproduct.setField("Stock_", stock + (num - oldnum));
            } else if (tbno.startsWith("BC")) {
                dsproduct.setField("Stock_", stock - (num - oldnum));
            }

            dsproduct.post();
        }
        return true;
    }

    public boolean TranBdelete() {
        Record headIn = getDataIn().getHead();
        String tbno = headIn.getString("TBNo_");
        String it = headIn.getString("It_");
        String code = headIn.getString("Code_");

        SqlQuery ds = new SqlQuery(this);
        ds.add("SELECT * FROM %s", BaseConfig.tranb);
        ds.add("WHERE CorpNo_='%s' and TBNo_='%s' and It_='%s' ", BaseConfig.CorpNo, tbno, it);
        ds.open();
        if (ds.eof()) {
            throw new RuntimeException("此单身资料不存在，无法删除！");
        }
        Double num = ds.getDouble("Num_");
        ds.delete();

        SqlQuery dsproduct = new SqlQuery(this);
        dsproduct.add("SELECT * FROM %s", BaseConfig.product);
        dsproduct.add("WHERE CorpNo_='%s' and Code_='%s' ", BaseConfig.CorpNo, code);
        dsproduct.open();
        if (!dsproduct.eof()) {
            double stock = dsproduct.getDouble("Stock_");
            dsproduct.edit();
            if (tbno.startsWith("AB")) {
                dsproduct.setField("Stock_", stock - num);
            } else if (tbno.startsWith("BC")) {
                dsproduct.setField("Stock_", stock + num);
            }
            dsproduct.post();
        }
        return true;
    }

}
