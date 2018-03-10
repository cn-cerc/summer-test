package cn.cerc.sample.forms.TranBC;

import cn.cerc.jbean.client.LocalService;
import cn.cerc.jbean.form.IPage;
import cn.cerc.jdb.core.Record;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;
import cn.cerc.jmis.page.RedirectPage;

public class FrmTranBCbAppend extends AbstractForm {
    @Override
    public IPage execute() throws Exception {
        JspPage jspPage = new JspPage(this, "TranBC/FrmTranBCbAppend.jsp");
        String tbno = this.getRequest().getParameter("tbno");
        jspPage.add("tbno", tbno);
        return jspPage;
    }

    public IPage TranBPost() {
        JspPage jspPage = new JspPage(this, "TranBC/FrmTranBCbAppend.jsp");

        String submit = this.getRequest().getParameter("submit");
        if (submit != null) {
            String tbno = this.getRequest().getParameter("tbno");
            String code = this.getRequest().getParameter("Code");
            String num = this.getRequest().getParameter("Num");
            LocalService svr = new LocalService(this);
            svr.setService("SvrTranInfo.TranBappend");
            Record headIn = svr.getDataIn().getHead();
            headIn.setField("TBNo_", tbno);
            headIn.setField("Code_", code);
            headIn.setField("Num_", num);
            if (!svr.exec()) {
                jspPage.add("msg", svr.getMessage());
                return jspPage;
            }
            return new RedirectPage(this, "FrmTranBCbInfo?tbno=" + tbno);

        }
        return jspPage;

    }

    @Override
    public boolean logon() {
        return true;
    }
}
