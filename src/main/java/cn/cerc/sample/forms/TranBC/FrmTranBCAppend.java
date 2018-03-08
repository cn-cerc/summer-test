package cn.cerc.sample.forms.TranBC;

import cn.cerc.jbean.client.LocalService;
import cn.cerc.jbean.form.IPage;
import cn.cerc.jdb.core.Record;
import cn.cerc.jdb.core.TDateTime;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;
import cn.cerc.jmis.page.RedirectPage;

public class FrmTranBCAppend extends AbstractForm {
    @Override
    public IPage execute() throws Exception {
        JspPage jspPage = new JspPage(this, "TranBC/FrmTranBCAppend.jsp");
        String tbno = this.getRequest().getParameter("TBNo");
        String cusname = this.getRequest().getParameter("CusName");
        String tbdate = this.getRequest().getParameter("TBDate");
        String appuser = this.getRequest().getParameter("AppUser");
        jspPage.add("TBDate", TDateTime.Now().getDate());

        String submit = this.getRequest().getParameter("submit");
        if (submit != null) {
            LocalService svr = new LocalService(this);
            svr.setService("SvrTranInfo.TranHappend");
            Record headIn = svr.getDataIn().getHead();
            headIn.setField("TBNo_", tbno);
            headIn.setField("CusName_", cusname);
            headIn.setField("TBDate_", tbdate);
            headIn.setField("AppUser_", appuser);
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
