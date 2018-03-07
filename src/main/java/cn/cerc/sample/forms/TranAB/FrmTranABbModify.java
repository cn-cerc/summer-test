package cn.cerc.sample.forms.TranAB;

import cn.cerc.jbean.client.LocalService;
import cn.cerc.jbean.form.IPage;
import cn.cerc.jdb.core.DataSet;
import cn.cerc.jdb.core.Record;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;
import cn.cerc.jmis.page.RedirectPage;

public class FrmTranABbModify extends AbstractForm {
    @Override
    public IPage execute() throws Exception {
        JspPage jspPage = new JspPage(this, "TranAB/FrmTranABbModify.jsp");
        String tbno = this.getRequest().getParameter("tbno");
        String it = this.getRequest().getParameter("it");
        jspPage.add("tbno", tbno);
        LocalService svr = new LocalService(this);
        svr.setService("SvrTranABInfo.TranABItsch");
        Record headIn = svr.getDataIn().getHead();
        headIn.setField("TBNo_", tbno);
        headIn.setField("It_", it);
        if (!svr.exec()) {
            jspPage.add("msg", svr.getMessage());
            return jspPage;
        }
        DataSet ds = svr.getDataOut();
        if (!ds.eof()) {
            TranABInfo_Record item = new TranABInfo_Record();
            item.setIt(ds.getInt("It_"));
            item.setCode(ds.getString("Code_"));
            item.setDesc(ds.getString("Desc_"));
            item.setSpec(ds.getString("Spec_"));
            item.setUnit(ds.getString("Unit_"));
            item.setNum(ds.getDouble("Num_"));
            jspPage.add("item", item);
        }
        return jspPage;
    }

    public IPage TranBPost() {
        JspPage jspPage = new JspPage(this, "TranAB/FrmTranABbModify.jsp");
        String tbno = this.getRequest().getParameter("tbno");
        String it = this.getRequest().getParameter("It");
        String num = this.getRequest().getParameter("Num");
        String code = this.getRequest().getParameter("Code");
        String submit = this.getRequest().getParameter("submit");
        if (submit != null) {
            LocalService svr = new LocalService(this);
            svr.setService("SvrTranABInfo.TranBmodify");
            Record headIn = svr.getDataIn().getHead();
            headIn.setField("TBNo_", tbno);
            headIn.setField("It_", it);
            headIn.setField("Code_", code);
            headIn.setField("Num_", num);
            if (!svr.exec()) {
                jspPage.add("msg", svr.getMessage());
                return jspPage;
            }
            return new RedirectPage(this, "FrmTranABbInfo?tbno=" + tbno);

        }
        String delete = this.getRequest().getParameter("delete");
        if (delete != null) {
            LocalService svr = new LocalService(this);
            svr.setService("SvrTranABInfo.TranBdelete");
            Record headIn = svr.getDataIn().getHead();
            headIn.setField("TBNo_", tbno);
            headIn.setField("It_", it);
            headIn.setField("Code_", code);
            headIn.setField("Num_", num);
            if (!svr.exec()) {
                jspPage.add("msg", svr.getMessage());
                return jspPage;
            }
            return new RedirectPage(this, "FrmTranABbInfo?tbno=" + tbno);

        }
        return jspPage;

    }

    @Override
    public boolean logon() {
        return true;
    }
}
