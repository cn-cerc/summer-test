package cn.cerc.sample.services.part;

import cn.cerc.jbean.core.CustomService;
import cn.cerc.jdb.core.Record;
import cn.cerc.jdb.mysql.BuildQuery;
import cn.cerc.jdb.mysql.SqlQuery;
import cn.cerc.sample.common.BaseConfig;

public class SvrPartInfo extends CustomService {
    public boolean search() {
        SqlQuery ds = new SqlQuery(this);
        ds.add("select * from %s ", BaseConfig.Product);
        ds.add("Where CorpNo_='%s' ", BaseConfig.CorpNo);
        ds.open();
        getDataOut().appendDataSet(ds);
        return true;
    }

    public boolean inOutSearch() {
        Record headIn = getDataIn().getHead();
        BuildQuery f = new BuildQuery(this);
        f.byField("H.CorpNo_", BaseConfig.CorpNo);
        if (headIn.hasValue("PartCode_")) {
            f.byLink(new String[] { "B.Code_", "B.Desc_", "B.Spec_", "B.Unit_" }, headIn.getString("PartCode_"));
        }
        if (headIn.hasValue("CusSup_")) {
            f.byLink(new String[] { "H.SupName_", "H.CusName_", "H.DeptName" }, headIn.getString("CusSup_"));
        }
        if (headIn.hasValue("TBNo_")) {
            f.byField("H.TBNo_", headIn.getString("TBNo_"));
        }
        if (headIn.hasValue("TBDateFrm_") && headIn.hasValue("TBDateTo_")) {
            f.byBetween("H.TBDate_", headIn.getDate("TBDateFrm_"), headIn.getDate("TBDateTo_"));
        }
        if (headIn.hasValue("TBDateFrm_") && !headIn.hasValue("TBDateTo_")) {
            f.byParam(String.format("H.TBDate_>='%s'", headIn.getDate("TBDateFrm_")));
        }
        if (headIn.hasValue("TBDateTo_") && !headIn.hasValue("TBDateFrm_")) {
            f.byParam(String.format("H.TBDate_<'%s'", headIn.getDate("TBDateTo_")));
        }
        f.add("select * from %s H", BaseConfig.TranH);
        f.add("inner join %s B on H.CorpNo_=B.CorpNo_ and H.TBNo_=B.TBNo_", BaseConfig.TranB);
        f.add("inner join %s P on P.CorpNo_=B.CorpNo_ and P.Code_=B.Code_", BaseConfig.Product);
        f.setOrderText("Order By H.TBDate_ ASC ,B.TBNo_, B.It_ ");
        SqlQuery ds = f.open();

        System.out.println(ds.getCommandText());

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

    public boolean searchSingle() {
        Record headIn = getDataIn().getHead();
        String code = headIn.getString("Code_");

        SqlQuery ds = new SqlQuery(this);
        ds.add("select * from %s ", BaseConfig.Product);
        ds.add("Where CorpNo_='%s' and Code_='%s' ", BaseConfig.CorpNo, code);
        ds.open();
        getDataOut().appendDataSet(ds);
        return true;
    }

    public boolean modify() {
        Record headIn = getDataIn().getHead();
        String code = headIn.getString("Code_");

        SqlQuery ds = new SqlQuery(this);
        ds.add("select * from %s", BaseConfig.Product);
        ds.add("Where CorpNo_='%s' and Code_='%s' ", BaseConfig.CorpNo, code);
        ds.open();

        // 检查是否存在
        if (ds.eof()) {
            throw new RuntimeException("该商品料号不存在，无法修改");
        }

        ds.edit();
        ds.setField("Desc_", headIn.getString("Desc_"));
        ds.setField("Spec_", headIn.getString("Spec_"));
        ds.setField("Unit_", headIn.getString("Unit_"));
        ds.post();

        return true;
    }

    public boolean append() {
        Record headIn = getDataIn().getHead();
        String code = headIn.getString("Code_");

        SqlQuery ds = new SqlQuery(this);
        ds.add("select * from %s", BaseConfig.Product);
        ds.add("Where CorpNo_='%s' and Code_='%s' ", BaseConfig.CorpNo, code);
        ds.open();

        // 检查是否存在
        if (!ds.eof()) {
            throw new RuntimeException("该商品料号已存在，无法重复增加");
        }

        ds.append();
        ds.setField("CorpNo_", BaseConfig.CorpNo);
        ds.setField("Code_", headIn.getString("Code_"));
        ds.setField("Desc_", headIn.getString("Desc_"));
        ds.setField("Spec_", headIn.getString("Spec_"));
        ds.setField("Unit_", headIn.getString("Unit_"));
        ds.setField("Stock_", 0);
        ds.post();

        return true;
    }

    public boolean delete() {
        Record headIn = getDataIn().getHead();
        String code = headIn.getString("Code_");

        SqlQuery ds = new SqlQuery(this);
        ds.add("select * from %s", BaseConfig.Product);
        ds.add("Where CorpNo_='%s' and Code_='%s' ", BaseConfig.CorpNo, code);
        ds.open();

        // 检查是否存在
        if (ds.eof()) {
            throw new RuntimeException("该商品料号不存在，无法删除");
        }

        ds.delete();

        return true;
    }
}
