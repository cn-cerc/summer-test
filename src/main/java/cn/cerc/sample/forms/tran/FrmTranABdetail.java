package cn.cerc.sample.forms.tran;

import cn.cerc.jbean.client.LocalService;
import cn.cerc.jbean.form.IPage;
import cn.cerc.jdb.core.DataSet;
import cn.cerc.jdb.core.Record;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;
import cn.cerc.jmis.page.RedirectPage;

public class FrmTranABdetail extends AbstractForm {
    @Override
    public IPage execute() {
        JspPage jspPage = new JspPage(this, "tran/FrmTranABdetail.jsp");
        String tbno = this.getRequest().getParameter("tbno");
        String it = this.getRequest().getParameter("it");

        LocalService svr = new LocalService(this);
        svr.setService("SvrTranAB.tranBsearDetail");
        Record headIn = svr.getDataIn().getHead();
        headIn.setField("TBNo_", tbno);
        headIn.setField("It_", it);

        if (!svr.exec()) {
            jspPage.setMessage(svr.getMessage());
            return jspPage;
        }
        DataSet ds = svr.getDataOut();
        if (!ds.eof()) {
            TranB_Record item = new TranB_Record();
            item.setTbno(ds.getString("TBNo_"));
            item.setIt(ds.getString("It_"));
            item.setCode(ds.getString("Code_"));
            item.setDesc(ds.getString("Desc_"));
            item.setSpec(ds.getString("Spec_"));
            item.setUnit(ds.getString("Unit_"));
            item.setNum(ds.getDouble("Num_"));
            jspPage.add("item", item);
        }
        return jspPage;
    }

    public IPage delete() {
        JspPage jspPage = new JspPage(this, "tran/FrmTranABS.jsp");
        LocalService svr = new LocalService(this);
        svr.setService("SvrTranAB.delete");
        String tbno = this.getRequest().getParameter("tbno");
        Record headIn = svr.getDataIn().getHead();
        headIn.setField("TBNo_", tbno);

        if (!svr.exec()) {
            jspPage.setMessage(svr.getMessage());
            return jspPage;
        }
        // return jspPage;
        return new RedirectPage(this, "FrmTranABS");
    }

    @Override
    public boolean logon() {
        return true;
    }
}
