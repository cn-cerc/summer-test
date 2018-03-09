package cn.cerc.sample.services.TranAB;

import cn.cerc.jbean.core.CustomService;
import cn.cerc.jdb.core.Record;
import cn.cerc.jdb.core.TDateTime;
import cn.cerc.jdb.core.Utils;
import cn.cerc.jdb.mysql.BuildQuery;
import cn.cerc.jdb.mysql.SqlQuery;
import cn.cerc.jdb.other.utils;
import cn.cerc.sample.common.BaseConfig;

public class SvrTranInfo extends CustomService {
    public boolean sch() {
        Record headIn = getDataIn().getHead();
        String tb = Utils.safeString(headIn.getString("TB_"));
        SqlQuery ds = new SqlQuery(this);
        ds.add("SELECT * FROM %s ", BaseConfig.tranh);
        ds.add("where CorpNo_='%s' and TB_='%s'", BaseConfig.CorpNo, tb);
        ds.open();
        getDataOut().appendDataSet(ds);
        return true;
    }

    public boolean TranHsch() {
        Record headIn = getDataIn().getHead();
        String tbno = Utils.safeString(headIn.getString("TBNo_"));
        SqlQuery dsTranH = new SqlQuery(this);
        dsTranH.add("SELECT * FROM %s ", BaseConfig.tranh);
        dsTranH.add("where CorpNo_='%s' and TBNo_='%s'", BaseConfig.CorpNo, tbno);
        dsTranH.open();
        getDataOut().appendDataSet(dsTranH);
        // getDataOut().getHead().copyValues(dsTranH.getCurrent());

        /*
         * SqlQuery dsTranB = new SqlQuery(this); dsTranB.add("SELECT *FROM %s ",
         * BaseConfig.tranb); dsTranB.add("where CorpNo_='%s' and TBNo_='%s' ",
         * BaseConfig.CorpNo, tbno); dsTranB.open();
         * getDataOut().appendDataSet(dsTranB);
         */
        return true;
    }

    public boolean TranHappend() {
        Record headIn = getDataIn().getHead();
        String tbno = Utils.safeString(headIn.getString("TBNo_"));
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
        ds.setField("DeptName_", headIn.getString("DeptName_"));
        ds.setField("AppUser_", headIn.getString("AppUser_"));
        ds.setField("AppDate_", TDateTime.Now().getDate());
        ds.post();
        return true;
    }

    public boolean TranHmodify() {
        Record headIn = getDataIn().getHead();
        String tbno = Utils.safeString(headIn.getString("TBNo_"));
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
        ds.setField("DeptName_", headIn.getString("DeptName_"));
        ds.post();
        return true;
    }

    public boolean TranBappend() {
        Record headIn = getDataIn().getHead();
        String tbno = Utils.safeString(headIn.getString("TBNo_"));
        String code = Utils.safeString(headIn.getString("Code_"));
        Double num = headIn.getDouble("Num_");

        SqlQuery ds = new SqlQuery(this);
        ds.add("SELECT * FROM %s", BaseConfig.product);
        ds.add("WHERE CorpNo_='%s' and Code_='%s'", BaseConfig.CorpNo, code);
        ds.open();
        if (ds.eof()) {
            throw new RuntimeException("此商品编号不存在，无法增加！");
        }
        String desc = ds.getString("Desc_");
        String spec = ds.getString("Spec_");
        String unit = ds.getString("Unit_");
        double stock = ds.getDouble("Stock_");
        if (tbno.startsWith("AE")) {
            num = num - stock;
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
            if (tbno.startsWith("AE")) {
                dsTran.setField("CurStock_", stock);
                dsTran.setField("Num_", num);
                dsTran.setField("NewStock_", stock + num);
            } else {
                dsTran.setField("Num_", num);
            }
            dsTran.setField("CorpNo_", BaseConfig.CorpNo);
            dsTran.setField("TBNo_", tbno);
            dsTran.setField("Code_", code);
            dsTran.setField("Desc_", desc);
            dsTran.setField("Spec_", spec);
            dsTran.setField("Unit_", unit);
        } else {
            dsTran.edit();
            if (tbno.startsWith("AE")) {
                dsTran.setField("CurStock_", stock);
                dsTran.setField("Num_", num);
                dsTran.setField("NewStock_", stock + num);
            } else {
                dsTran.setField("Num_", num + dsTran.getDouble("Num_"));
            }

        }
        dsTran.post();

        ds.edit();
        if (tbno.startsWith("AB")) {
            ds.setField("Stock_", stock + num);
        } else if (tbno.startsWith("BC")) {
            ds.setField("Stock_", stock - num);
        } else if (tbno.startsWith("AE")) {
            ds.setField("Stock_", stock + num);
        }
        ds.post();
        return true;
    }

    public boolean TranBsch() {
        Record headIn = getDataIn().getHead();
        String tbno = Utils.safeString(headIn.getString("TBNo_"));
        SqlQuery ds = new SqlQuery(this);
        ds.add("SELECT * FROM %s", BaseConfig.tranb);
        ds.add("WHERE CorpNo_='%s' and TBNo_='%s' ORDER BY It_  ", BaseConfig.CorpNo, tbno);
        ds.open();
        getDataOut().appendDataSet(ds);
        return true;
    }

    public boolean TranBStocksch() {
        Record headIn = getDataIn().getHead();
        String tbno = Utils.safeString(headIn.getString("TBNo_"));
        SqlQuery ds = new SqlQuery(this);
        ds.add("SELECT *,(SELECT Stock_ FROM %s ", BaseConfig.product);
        ds.add("WHERE Code_=tranb.Code_ AND CorpNo_=tranb.CorpNo_) AS Stock_ FROM %s", BaseConfig.tranb);
        ds.add("WHERE CorpNo_='%s' and TBNo_='%s' ORDER BY It_  ", BaseConfig.CorpNo, tbno);
        ds.open();
        getDataOut().appendDataSet(ds);
        return true;
    }

    public boolean TranItsch() {
        Record headIn = getDataIn().getHead();
        String tbno = Utils.safeString(headIn.getString("TBNo_"));
        String it = headIn.getString("It_");
        SqlQuery ds = new SqlQuery(this);
        ds.add("SELECT * FROM %s", BaseConfig.tranb);
        ds.add("WHERE CorpNo_='%s' and TBNo_='%s' and It_='%s' ORDER BY It_  ", BaseConfig.CorpNo, tbno, it);
        ds.open();
        getDataOut().appendDataSet(ds);
        return true;
    }

    public boolean TranItStocksch() {
        Record headIn = getDataIn().getHead();
        String tbno = Utils.safeString(headIn.getString("TBNo_"));
        String it = headIn.getString("It_");
        SqlQuery ds = new SqlQuery(this);
        ds.add("SELECT *,(SELECT Stock_ FROM product WHERE Code_=tranb.Code_ AND CorpNo_=tranb.CorpNo_) AS Stock_ FROM %s",
                BaseConfig.tranb);
        ds.add("WHERE CorpNo_='%s' and TBNo_='%s' and It_='%s' ORDER BY It_  ", BaseConfig.CorpNo, tbno, it);
        ds.open();
        getDataOut().appendDataSet(ds);
        return true;
    }

    public boolean TranBmodify() {
        Record headIn = getDataIn().getHead();
        String tbno = Utils.safeString(headIn.getString("TBNo_"));
        String it = headIn.getString("It_");
        String code = Utils.safeString(headIn.getString("Code_"));
        Double num = headIn.getDouble("Num_");
        SqlQuery ds = new SqlQuery(this);
        ds.add("SELECT * FROM %s", BaseConfig.tranb);
        ds.add("WHERE CorpNo_='%s' and TBNo_='%s' and It_='%s' ", BaseConfig.CorpNo, tbno, it);
        ds.open();
        if (ds.eof()) {
            throw new RuntimeException("此单身资料不存在，无法修改！");
        }
        Double oldnum = ds.getDouble("Num_");

        SqlQuery dsproduct = new SqlQuery(this);
        dsproduct.add("SELECT * FROM %s", BaseConfig.product);
        dsproduct.add("WHERE CorpNo_='%s' and Code_='%s' ", BaseConfig.CorpNo, code);
        dsproduct.open();
        if (dsproduct.eof()) {
            throw new RuntimeException("此商品资料不存在，无法修改！");
        }
        double stock = dsproduct.getDouble("Stock_");
        dsproduct.edit();
        if (tbno.startsWith("AB")) {
            dsproduct.setField("Stock_", stock + (num - oldnum));
        } else if (tbno.startsWith("BC")) {
            dsproduct.setField("Stock_", stock - (num - oldnum));
        } else if (tbno.startsWith("AE")) {
            dsproduct.setField("Stock_", num);
        }
        dsproduct.post();
        ds.edit();
        num = num - stock;
        ds.setField("CurStock_", stock);
        ds.setField("Num_", num);
        ds.setField("NewStock_", stock + num);
        ds.post();
        return true;
    }

    public boolean TranBdelete() {
        Record headIn = getDataIn().getHead();
        String tbno = Utils.safeString(headIn.getString("TBNo_"));
        String it = Utils.safeString(headIn.getString("It_"));
        String code = Utils.safeString(headIn.getString("Code_"));

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

    public boolean schPartInOut() {
        Record headIn = getDataIn().getHead();
        BuildQuery f = new BuildQuery(this);
        f.byField("h.CorpNo_", BaseConfig.CorpNo);
        if (headIn.hasValue("PartSchText_")) {
            f.byLink(new String[] { "b.Code_", "b.Desc_", "b.Spec_", "b.Unit_" },
                    Utils.safeString(headIn.getString("PartSchText_")));
        }
        if (headIn.hasValue("CusSupSchText_")) {
            f.byLink(new String[] { "h.SupName_", "h.CusName_", "h.DeptName" },
                    Utils.safeString(headIn.getString("CuSupSchText_")));
        }
        if (headIn.hasValue("TBNo_")) {
            f.byField("h.TBNo_", headIn.getString("TBNo_"));
        }
        if (headIn.hasValue("TBDateFM_") && headIn.hasValue("TBDateTO_")) {
            f.byBetween("h.TBDate_", headIn.getDate("TBDateFM_"), headIn.getDate("TBDateTO_"));
        }
        if (headIn.hasValue("TBDateFM_") && !headIn.hasValue("TBDateTO_")) {
            f.byParam(String.format("h.TBDate_>='%s'", headIn.getDate("TBDateFM_")));
        }
        if (headIn.hasValue("TBDateTO_") && !headIn.hasValue("TBDateFM_")) {
            f.byParam(String.format("h.TBDate_<'%s'", headIn.getDate("TBDateTO_")));
        }
        f.add("select * from %s h", BaseConfig.tranh);
        f.add("inner join %s b on h.CorpNo_=b.CorpNo_ and h.TBNo_=b.TBNo_", BaseConfig.tranb);
        f.add("inner join %s p on p.CorpNo_=b.CorpNo_ and p.Code_=b.Code_", BaseConfig.product);
        f.setOrderText("order by h.TBDate_ desc, b.It_ ");
        SqlQuery ds = f.open();
        // System.out.println(ds.getCommandText());

        getDataOut().appendDataSet(ds);
        double totalInNum = 0;
        double totalOutNum = 0;
        double totalAENum = 0;
        getDataIn().first();
        while (getDataOut().fetch()) {
            String tb = getDataOut().getString("TB_");
            if ("AB".equals(tb)) {
                getDataOut().setField("InNum_", getDataOut().getDouble("Num_"));
                getDataOut().setField("ObjName_", getDataOut().getString("SupName_"));
            } else if ("BC".equals(tb)) {
                getDataOut().setField("OutNum_", getDataOut().getDouble("Num_"));
                getDataOut().setField("ObjName_", getDataOut().getString("CusName_"));
            } else if ("AE".equals(tb)) {
                getDataOut().setField("AENum_", getDataOut().getDouble("Num_"));
                getDataOut().setField("ObjName_", getDataOut().getString("DeptName_"));
            }
            totalInNum += getDataOut().getDouble("InNum_");
            totalOutNum += getDataOut().getDouble("OutNum_");
            totalAENum += getDataOut().getDouble("AENum_");
        }
        getDataOut().last();
        getDataOut().append();
        getDataOut().setField("Unit_", "合计");
        getDataOut().setField("InNum_", totalInNum);
        getDataOut().setField("OutNum_", totalOutNum);
        getDataOut().setField("AENum_", totalAENum);
        return true;
    }

}
