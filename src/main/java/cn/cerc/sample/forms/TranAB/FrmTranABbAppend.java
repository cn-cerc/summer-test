package cn.cerc.sample.forms.TranAB;

import cn.cerc.jbean.client.LocalService;
import cn.cerc.jbean.form.IPage;
import cn.cerc.jdb.core.Record;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;
import cn.cerc.jmis.page.RedirectPage;

public class FrmTranABbAppend extends AbstractForm {
    @Override
    public IPage execute() throws Exception {
        JspPage jspPage = new JspPage(this, "TranAB/FrmTranABbAppend.jsp");
        String tbno = this.getRequest().getParameter("tbno");
        jspPage.add("tbno", tbno);
        jspPage.add("code", this.getRequest().getParameter("Code"));
        return jspPage;
    }

    public IPage TranBPost() {
        JspPage jspPage = new JspPage(this, "TranAB/FrmTranABbAppend.jsp");

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
            return new RedirectPage(this, "FrmTranABbInfo?tbno=" + tbno);

        }
        return jspPage;

    }

    @Override
    public boolean logon() {
        return true;
    }
}
