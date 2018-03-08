package cn.cerc.sample.forms.TranBC;

import java.util.ArrayList;
import java.util.List;

import cn.cerc.jbean.client.LocalService;
import cn.cerc.jbean.form.IPage;
import cn.cerc.jdb.core.DataSet;
import cn.cerc.jdb.core.Record;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;
import cn.cerc.sample.forms.TranAB.TranABInfo_Record;

public class FrmTranBCInfo extends AbstractForm {
    @Override
    public IPage execute() throws Exception {
        JspPage jspPage = new JspPage(this, "TranBC/FrmTranBCInfo.jsp");
        LocalService svr = new LocalService(this);
        svr.setService("SvrTranInfo.sch");
        Record headIn = svr.getDataIn().getHead();
        headIn.setField("TB_", "BC");
        if (!svr.exec()) {
            jspPage.setMessage(svr.getMessage());
            return jspPage;
        }
        DataSet ds = svr.getDataOut();
        List<TranABInfo_Record> items = new ArrayList<>();
        while (ds.fetch()) {
            TranABInfo_Record item = new TranABInfo_Record();
            item.setTbno(ds.getString("TBNo_"));
            item.setTb(ds.getString("TB_"));
            item.setTbdate(ds.getString("TBDate_"));
            item.setCusname(ds.getString("CusName_"));
            item.setAppuser(ds.getString("AppUser_"));
            item.setAppdate(ds.getString("AppDate_"));
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
