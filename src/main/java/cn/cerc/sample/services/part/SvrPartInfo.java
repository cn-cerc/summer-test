package cn.cerc.sample.services.part;

import cn.cerc.jbean.core.CustomService;
import cn.cerc.jdb.core.Record;
import cn.cerc.jdb.mysql.SqlQuery;
import cn.cerc.sample.common.BaseConfig;

public class SvrPartInfo extends CustomService {
    public boolean search() {
        SqlQuery ds = new SqlQuery(this);
        ds.add("select * from %s", BaseConfig.Product);
        ds.add("Where CorpNo_='%s' ", BaseConfig.CorpNo);
        ds.open();

        // 返回forms
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
