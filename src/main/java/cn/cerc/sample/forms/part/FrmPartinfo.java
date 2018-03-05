package cn.cerc.sample.forms.part;

import java.util.ArrayList;
import java.util.List;

import cn.cerc.jbean.client.LocalService;
import cn.cerc.jbean.form.IPage;
import cn.cerc.jdb.core.DataSet;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;

public class FrmPartinfo extends AbstractForm {
    @Override
    public IPage execute() throws Exception {
        JspPage jspPage = new JspPage(this, "part/FrmPartinfo.jsp");
        LocalService svr = new LocalService(this);
        svr.setService("svrPartinfo.sch");
        if (!svr.exec()) {
            jspPage.setMessage(svr.getMessage());
            return jspPage;
        }
        DataSet ds = svr.getDataOut();
        List<PartInfo_Record> items = new ArrayList<>();
        while (ds.fetch()) {
            PartInfo_Record item = new PartInfo_Record();
            item.setCode(ds.getString("Code_"));
            item.setDesc(ds.getString("Desc_"));
            item.setSpec(ds.getString("Spec_"));
            item.setUnit(ds.getString("Unit_"));
            item.setStock(ds.getDouble("Stock_"));
            items.add(item);

        }
        jspPage.add("items", items);
        return jspPage;
    }

    @Override
    public boolean logon() {
        return true;
    }

}
