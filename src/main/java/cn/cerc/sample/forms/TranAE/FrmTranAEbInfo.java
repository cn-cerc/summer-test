package cn.cerc.sample.forms.TranAE;

import cn.cerc.jbean.client.LocalService;
import cn.cerc.jbean.form.IPage;
import cn.cerc.jdb.core.DataSet;
import cn.cerc.jdb.core.Record;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;
import cn.cerc.jmis.page.RedirectPage;
import cn.cerc.sample.forms.TranAB.TranABInfo_Record;

public class FrmTranAEbInfo extends AbstractForm {
    @Override
    public IPage execute() throws Exception {
        JspPage jspPage = new JspPage(this, "TranAE/FrmTranAEbInfo.jsp");
        String tbno = this.getRequest().getParameter("tbno");
        LocalService svr = new LocalService(this);
        svr.setService("SvrTranInfo.TranHsch");
        Record headIn = svr.getDataIn().getHead();
        headIn.setField("TBNo_", tbno);
        if (!svr.exec()) {
            jspPage.setMessage(svr.getMessage());
            return jspPage;
        }
        // jspPage.add("head", svr.getDataOut().getCurrent().getItems());
        DataSet ds = svr.getDataOut();
        if (!ds.eof()) {
            TranABInfo_Record item = new TranABInfo_Record();
            item.setTbno(ds.getString("TBNo_"));
            item.setTbdate(ds.getString("TBDate_"));
            item.setDeptname(ds.getString("DeptName_"));
            item.setAppuser(ds.getString("AppUser_"));
            jspPage.add("item", item);
        }
        LocalService svr2 = new LocalService(this);
        svr2.setService("SvrTranInfo.TranBStocksch");
        Record headIn2 = svr2.getDataIn().getHead();
        headIn2.setField("TBNo_", tbno);
        if (!svr2.exec()) {
            jspPage.setMessage(svr2.getMessage());
            return jspPage;
        }
        jspPage.add("dataSet", svr2.getDataOut().getRecords());
        return jspPage;
    }

    public IPage submit() {
        JspPage jspPage = new JspPage(this, "TranAE/FrmTranAEbInfo.jsp");
        String tbno = this.getRequest().getParameter("TBNo");
        String submit = this.getRequest().getParameter("submit");
        if (submit != null) {

            String deptname = this.getRequest().getParameter("DeptName");
            LocalService svr = new LocalService(this);
            svr.setService("SvrTranInfo.TranHmodify");
            Record headIn = svr.getDataIn().getHead();
            headIn.setField("TBNo_", tbno);
            headIn.setField("DeptName_", deptname);
            if (!svr.exec()) {
                jspPage.add("msg", svr.getMessage());
                return new RedirectPage(this, "FrmTranAEbInfo?tbno=" + tbno);
            }

        }
        return new RedirectPage(this, "FrmTranAEbInfo?tbno=" + tbno);

    }

    @Override
    public boolean logon() {
        return true;
    }
}
