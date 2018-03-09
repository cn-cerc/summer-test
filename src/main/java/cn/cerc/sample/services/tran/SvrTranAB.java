package cn.cerc.sample.services.tran;

import cn.cerc.jbean.core.CustomService;
import cn.cerc.jdb.core.Record;
import cn.cerc.jdb.core.TDateTime;
import cn.cerc.jdb.mysql.SqlQuery;
import cn.cerc.jdb.mysql.Transaction;
import cn.cerc.sample.common.BaseConfig;

public class SvrTranAB extends CustomService {

    public boolean searchTranH() {
        SqlQuery ds = new SqlQuery(this);
        ds.add("select * from %s ", BaseConfig.TranH);
        ds.add("Where CorpNo_='%s' and TB_='AB' ", BaseConfig.CorpNo);
        ds.open();
        getDataOut().appendDataSet(ds);
        return true;
    }

    public boolean searchSingleTranH() {
        Record headIn = getDataIn().getHead();
        String tbno = headIn.getString("TBNo_");

        SqlQuery ds = new SqlQuery(this);
        ds.add("select * from %s ", BaseConfig.TranH);
        ds.add("Where CorpNo_='%s' and TB_='AB' and TBNo_='%s' ", BaseConfig.CorpNo, tbno);
        ds.open();
        getDataOut().appendDataSet(ds);
        return true;
    }

    public boolean appendTranH() {
        Record headIn = getDataIn().getHead();
        String tbno = headIn.getString("TBNo_");

        SqlQuery ds = new SqlQuery(this);
        ds.add("select * from %s", BaseConfig.TranH);
        ds.add("Where CorpNo_='%s' and TB_='AB' and TBNo_='%s' ", BaseConfig.CorpNo, tbno);
        ds.open();

        if (!ds.eof()) {
            throw new RuntimeException("该单据编号已存在，无法重复增加");
        }

        ds.append();

        ds.setField("CorpNo_", BaseConfig.CorpNo);
        ds.setField("TB_", "AB");
        ds.setField("TBNo_", headIn.getString("TBNo_"));
        ds.setField("TBDate_", headIn.getString("TBDate_"));
        ds.setField("SupName_", headIn.getString("SupName_"));
        ds.setField("DeptName_", headIn.getString("DeptName_"));
        ds.setField("AppUser_", BaseConfig.AppUser);
        ds.setField("AppDate_", TDateTime.Now().getDate());

        ds.post();

        return true;
    }

    public boolean deleteTranH() {
        /*
         * Record headIn = getDataIn().getHead(); String code =
         * headIn.getString("Code_");
         * 
         * SqlQuery ds = new SqlQuery(this); ds.add("select * from %s",
         * BaseConfig.TranH); ds.add("Where CorpNo_='%s' and TB_='AB' and Code_='%s' ",
         * BaseConfig.CorpNo, code); ds.open();
         * 
         * if (ds.eof()) { throw new RuntimeException("该商品料号不存在，无法删除"); }
         * 
         * ds.delete();
         */
        return true;
    }

    public boolean searchTranB() {
        SqlQuery ds = new SqlQuery(this);
        ds.add("select * from %s ", BaseConfig.TranB);
        ds.add("Where CorpNo_='%s' and LEFT(TBNo_,2)='AB'  ", BaseConfig.CorpNo);
        ds.open();
        getDataOut().appendDataSet(ds);
        return true;
    }

    public boolean searchSingleTranB() {
        Record headIn2 = getDataIn().getHead();
        String tbno = headIn2.getString("TBNo_");

        SqlQuery ds = new SqlQuery(this);
        ds.add("select * from %s ", BaseConfig.TranB);
        ds.add("Where CorpNo_='%s' and LEFT(TBNo_,2)='AB' and TBNo_='%s' Order By It_ ", BaseConfig.CorpNo, tbno);
        ds.open();
        getDataOut().appendDataSet(ds);
        return true;
    }

    public boolean searchDetailTranB() {
        Record headIn = getDataIn().getHead();
        String tbno = headIn.getString("TBNo_");
        String it = headIn.getString("It_");

        SqlQuery ds = new SqlQuery(this);
        ds.add("select * from %s ", BaseConfig.TranB);
        ds.add("Where CorpNo_='%s' and LEFT(TBNo_,2)='AB' and TBNo_='%s' and It_='%s' Order By It_ ", BaseConfig.CorpNo,
                tbno, it);
        ds.open();
        getDataOut().appendDataSet(ds);
        return true;
    }

    public boolean appendTranB() {
        Record headIn = getDataIn().getHead();
        String tbno = headIn.getString("TBNo_");
        String it = headIn.getString("It_");
        String code = headIn.getString("Code_");
        String desc = "";
        String spec = "";
        String unit = "";
        Double num = headIn.getDouble("Num_");

        try (Transaction tx = new Transaction(this)) {
            SqlQuery ds = new SqlQuery(this);
            ds.add("select * from %s", BaseConfig.Product);
            ds.add("Where CorpNo_='%s' and Code_='%s' ", BaseConfig.CorpNo, code);
            ds.open();
            if (!ds.eof()) {
                desc = ds.getString("Desc_");
                spec = ds.getString("Spec_");
                unit = ds.getString("Unit_");
            }

            SqlQuery dsTranB = new SqlQuery(this);
            dsTranB.add("select * from %s", BaseConfig.TranB);
            dsTranB.add("Where CorpNo_='%s' and LEFT(TBNo_,2)='AB' and TBNo_='%s' ", BaseConfig.CorpNo, tbno);
            dsTranB.open();

            dsTranB.append();
            dsTranB.setField("CorpNo_", BaseConfig.CorpNo);
            dsTranB.setField("TBNo_", tbno);
            dsTranB.setField("It_", it);
            dsTranB.setField("Code_", code);
            dsTranB.setField("Desc_", desc);
            dsTranB.setField("Spec_", spec);
            dsTranB.setField("Unit_", unit);
            dsTranB.setField("Num_", num);
            dsTranB.post();

            SqlQuery dsProduct = new SqlQuery(this);
            dsProduct.add("select * from %s", BaseConfig.Product);
            dsProduct.add("Where CorpNo_='%s' and Code_='%s' ", BaseConfig.CorpNo, code);
            dsProduct.open();
            if (!dsProduct.eof()) {
                Double stock = dsProduct.getDouble("Stock_");
                dsProduct.edit();
                dsProduct.setField("Stock_", stock + num);
                dsProduct.post();
            }
            tx.commit();
        }
        return true;
    }

    public boolean deleteTranB() {
        Record headIn = getDataIn().getHead();
        String tbno = headIn.getString("TBNo_");
        String it = headIn.getString("It_");
        String code = headIn.getString("Code_");
        Double num = 0.0;
        try (Transaction tx = new Transaction(this)) {
            SqlQuery ds = new SqlQuery(this);
            ds.add("select * from %s", BaseConfig.TranB);
            ds.add("Where CorpNo_='%s' and LEFT(TBNo_,2)='AB' and TBNo_='%s' and It_='%s' and Code_='%s' ",
                    BaseConfig.CorpNo, tbno, it, code);
            ds.open();

            if (!ds.eof()) {
                num = ds.getDouble("Num_");
            }
            ds.delete();

            SqlQuery dsProduct = new SqlQuery(this);
            dsProduct.add("select * from %s", BaseConfig.Product);
            dsProduct.add("Where CorpNo_='%s' and Code_='%s' ", BaseConfig.CorpNo, code);
            dsProduct.open();
            if (!dsProduct.eof()) {
                double stock = dsProduct.getDouble("Stock_");
                dsProduct.edit();
                dsProduct.setField("Stock_", stock - num);
                dsProduct.post();
            }
            tx.commit();
        }
        return true;
    }

    public boolean modifyTranB() {
        Record headIn = getDataIn().getHead();
        String tbno = headIn.getString("TBNo_");
        String it = headIn.getString("It_");
        String code = headIn.getString("Code_");
        Double num = headIn.getDouble("Num_");
        Double oldnum = 0.0;

        try (Transaction tx = new Transaction(this)) {
            SqlQuery ds = new SqlQuery(this);
            ds.add("select * from %s", BaseConfig.TranB);
            ds.add("Where CorpNo_='%s' and LEFT(TBNo_,2)='AB'and TBNo_='%s' and It_='%s' and Code_='%s' ",
                    BaseConfig.CorpNo, tbno, it, code);
            ds.open();
            if (!ds.eof()) {
                oldnum = ds.getDouble("Num_");
                ds.edit();
                ds.setField("Num_", num);
                ds.post();
            }

            SqlQuery dsProduct = new SqlQuery(this);
            dsProduct.add("select * from %s", BaseConfig.Product);
            dsProduct.add("Where CorpNo_='%s' and Code_='%s' ", BaseConfig.CorpNo, code);
            dsProduct.open();
            if (!dsProduct.eof()) {
                Double stock = dsProduct.getDouble("Stock_");
                dsProduct.edit();
                dsProduct.setField("Stock_", stock - oldnum + num);
                dsProduct.post();
            }
            tx.commit();
        }
        return true;
    }

}
