package cn.cerc.sample.forms.part;

import cn.cerc.jbean.client.LocalService;
import cn.cerc.jbean.form.IPage;
import cn.cerc.jdb.core.Record;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;

public class FrmPartInOut extends AbstractForm {
    @Override
    public IPage execute() {
        JspPage jspPage = new JspPage(this, "part/FrmPartInOut.jsp");
        String submit = this.getRequest().getParameter("submit");
        if (submit != null) {
            String tbno = this.getRequest().getParameter("TBNo");
            String partcode = this.getRequest().getParameter("PartCode");
            String cussup = this.getRequest().getParameter("CupSup");
            String tdatefm = this.getRequest().getParameter("TBDateFrm");
            String tdateto = this.getRequest().getParameter("TBDateTo");

            LocalService svr = new LocalService(this);
            svr.setService("SvrPartInfo.inOutSearch");

            Record headIn = svr.getDataIn().getHead();
            headIn.setField("TBNo_", tbno);
            headIn.setField("PartCode_", partcode);
            headIn.setField("CusSup_", cussup);
            headIn.setField("TBDateFrm_", tdatefm);
            headIn.setField("TBDateTo_", tdateto);

            if (!svr.exec()) {
                jspPage.setMessage(svr.getMessage());
                return jspPage;
            }
            jspPage.add("dataSet", svr.getDataOut());
        }

        return jspPage;
    }

    @Override
    public boolean logon() {
        return true;
    }
}
