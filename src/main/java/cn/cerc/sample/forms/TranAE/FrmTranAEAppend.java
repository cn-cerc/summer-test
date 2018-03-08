package cn.cerc.sample.forms.TranAE;

import cn.cerc.jbean.client.LocalService;
import cn.cerc.jbean.form.IPage;
import cn.cerc.jdb.core.Record;
import cn.cerc.jdb.core.TDateTime;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;
import cn.cerc.jmis.page.RedirectPage;

public class FrmTranAEAppend extends AbstractForm {
    @Override
    public IPage execute() throws Exception {
        JspPage jspPage = new JspPage(this, "TranAE/FrmTranAEAppend.jsp");
        String tbno = this.getRequest().getParameter("TBNo");
        String tbdate = this.getRequest().getParameter("TBDate");
        String appuser = this.getRequest().getParameter("AppUser");
        String deptname = this.getRequest().getParameter("DeptName");
        jspPage.add("TBDate", TDateTime.Now().getDate());

        String submit = this.getRequest().getParameter("submit");
        if (submit != null) {
            LocalService svr = new LocalService(this);
            svr.setService("SvrTranInfo.TranHappend");
            Record headIn = svr.getDataIn().getHead();
            headIn.setField("TBNo_", tbno);
            headIn.setField("DeptName_", deptname);
            headIn.setField("TBDate_", tbdate);
            headIn.setField("AppUser_", appuser);
            if (!svr.exec()) {
                jspPage.add("msg", svr.getMessage());
                return jspPage;
            }
            return new RedirectPage(this, "FrmTranAEbInfo?tbno=" + tbno);
        }

        return jspPage;
    }

    @Override
    public boolean logon() {
        return true;
    }
}
