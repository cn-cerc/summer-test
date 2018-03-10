package cn.cerc.sample.forms.TranBC;

import java.util.ArrayList;
import java.util.List;

import cn.cerc.jbean.client.LocalService;
import cn.cerc.jbean.form.IPage;
import cn.cerc.jdb.core.DataSet;
import cn.cerc.jdb.core.Record;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;
import cn.cerc.jmis.page.RedirectPage;
import cn.cerc.sample.forms.TranAB.TranABInfo_Record;

public class FrmTranBCbInfo extends AbstractForm {
    @Override
    public IPage execute() throws Exception {
        JspPage jspPage = new JspPage(this, "TranBC/FrmTranBCbInfo.jsp");
        String tbno = this.getRequest().getParameter("tbno");
        LocalService svr = new LocalService(this);
        svr.setService("SvrTranInfo.TranHsch");
        Record headIn = svr.getDataIn().getHead();
        headIn.setField("TBNo_", tbno);
        if (!svr.exec()) {
            jspPage.setMessage(svr.getMessage());
            return jspPage;
        }
        DataSet ds = svr.getDataOut();
        if (!ds.eof()) {
            TranABInfo_Record item = new TranABInfo_Record();
            item.setTbno(ds.getString("TBNo_"));
            item.setCusname(ds.getString("CusName_"));
            item.setTbdate(ds.getString("TBDate_"));
            item.setAppuser(ds.getString("AppUser_"));
            jspPage.add("item", item);
        }

        LocalService svr2 = new LocalService(this);
        svr2.setService("SvrTranInfo.TranBsch");
        Record headIn2 = svr2.getDataIn().getHead();
        headIn2.setField("TBNo_", tbno);
        if (!svr2.exec()) {
            jspPage.setMessage(svr2.getMessage());
            return jspPage;
        }
        DataSet dsTranB = svr2.getDataOut();
        List<TranABInfo_Record> items = new ArrayList<>();
        while (dsTranB.fetch()) {
            TranABInfo_Record item = new TranABInfo_Record();
            item.setIt(dsTranB.getInt("It_"));
            item.setTbno(dsTranB.getString("TBNo_"));
            item.setCode(dsTranB.getString("Code_"));
            item.setDesc(dsTranB.getString("Desc_"));
            item.setSpec(dsTranB.getString("Spec_"));
            item.setUnit(dsTranB.getString("Unit_"));
            item.setNum(dsTranB.getDouble("Num_"));
            items.add(item);

        }
        jspPage.add("items", items);

        return jspPage;
    }

    public IPage submit() {
        JspPage jspPage = new JspPage(this, "TranAB/FrmTranABbInfo.jsp");
        String tbno = this.getRequest().getParameter("TBNo");
        String submit = this.getRequest().getParameter("submit");
        if (submit != null) {

            String cusname = this.getRequest().getParameter("CusName");
            LocalService svr = new LocalService(this);
            svr.setService("SvrTranInfo.TranHmodify");
            Record headIn = svr.getDataIn().getHead();
            headIn.setField("TBNo_", tbno);
            headIn.setField("CusName_", cusname);
            if (!svr.exec()) {
                jspPage.add("msg", svr.getMessage());
                return new RedirectPage(this, "FrmTranBCbInfo?tbno=" + tbno);
            }

        }
        return new RedirectPage(this, "FrmTranBCbInfo?tbno=" + tbno);

    }

    @Override
    public boolean logon() {
        return true;
    }
}
