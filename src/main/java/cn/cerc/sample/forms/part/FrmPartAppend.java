package cn.cerc.sample.forms.part;

import cn.cerc.jbean.client.LocalService;
import cn.cerc.jbean.form.IPage;
import cn.cerc.jdb.core.Record;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;
import cn.cerc.jmis.page.RedirectPage;

public class FrmPartAppend extends AbstractForm {
    @Override
    public IPage execute() throws Exception {
        JspPage jspPage = new JspPage(this, "part/FrmPartAppend.jsp");
        String code = this.getRequest().getParameter("Code");
        String spec = this.getRequest().getParameter("Spec");
        String desc = this.getRequest().getParameter("Desc");
        String unit = this.getRequest().getParameter("Unit");

        String submit = this.getRequest().getParameter("submit");
        if (submit != null) {
            LocalService svr = new LocalService(this);
            svr.setService("svrPartinfo.append");
            Record headIn = svr.getDataIn().getHead();
            headIn.setField("Code_", code);
            headIn.setField("Desc_", desc);
            headIn.setField("Spec_", spec);
            headIn.setField("Unit_", unit);
            if (!svr.exec()) {
                jspPage.setMessage(svr.getMessage());
                return jspPage;
            }
            return new RedirectPage(this, "FrmPartinfo");
        }

        return jspPage;
    }

    @Override
    public boolean logon() {
        return true;
    }
}
