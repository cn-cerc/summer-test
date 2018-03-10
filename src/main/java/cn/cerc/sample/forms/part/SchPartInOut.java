package cn.cerc.sample.forms.part;

import cn.cerc.jbean.client.LocalService;
import cn.cerc.jbean.form.IPage;
import cn.cerc.jdb.core.Record;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;

public class SchPartInOut extends AbstractForm {
    @Override
    public IPage execute() throws Exception {
        JspPage jspPage = new JspPage(this, "part/SchPartInOut.jsp");
        String submit = this.getRequest().getParameter("submit");
        if (submit != null) {
            String tbno = this.getRequest().getParameter("TBNo");
            String partSchText = this.getRequest().getParameter("PartSchText");
            String cupSupSchText = this.getRequest().getParameter("CupSupSchText");
            String datefm = this.getRequest().getParameter("TBDateFM");
            String dateto = this.getRequest().getParameter("TBDateTO");
            LocalService svr = new LocalService(this);
            svr.setService("SvrTranInfo.schPartInOut");
            Record headIn = svr.getDataIn().getHead();
            headIn.setField("TBNo_", tbno);
            headIn.setField("PartSchText_", partSchText);
            headIn.setField("CupSupSchText_", cupSupSchText);
            headIn.setField("TBDateFM_", datefm);
            headIn.setField("TBDateTO_", dateto);
            if (!svr.exec()) {
                jspPage.setMessage(svr.getMessage());
                return jspPage;
            }
            jspPage.add("dataSet", svr.getDataOut().getRecords());
        }

        return jspPage;
    }

    @Override
    public boolean logon() {
        return true;
    }

}
