package cn.cerc.sample.forms.tran;

import cn.cerc.jbean.client.LocalService;
import cn.cerc.jbean.form.IPage;
import cn.cerc.jdb.core.Record;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;
import cn.cerc.jmis.page.RedirectPage;

public class FrmTranHAB extends AbstractForm {
    @Override
    public IPage execute() {
        // 新增加AB单单头数据
        JspPage jspPage = new JspPage(this, "tran/FrmTranHAB.jsp");
        String tbno = this.getRequest().getParameter("TBNo");
        String tbdate = this.getRequest().getParameter("TBDate");
        String supname = this.getRequest().getParameter("SupName");
        String deptname = this.getRequest().getParameter("DeptName");

        String submit = this.getRequest().getParameter("submit");

        if (submit != null) {
            LocalService svr = new LocalService(this);
            svr.setService("SvrTranAB.tranHappend");

            Record headIn = svr.getDataIn().getHead();
            headIn.setField("TBNo_", tbno);
            headIn.setField("TBDate_", tbdate);
            headIn.setField("SupName_", supname);
            headIn.setField("DeptName_", deptname);

            if (!svr.exec()) {
                jspPage.setMessage(svr.getMessage());
                return jspPage;
            }
            return new RedirectPage(this, "FrmTranAB?tbno=" + tbno);
        }
        return jspPage;
    }

    @Override
    public boolean logon() {
        return true;
    }

}
