package cn.cerc.sample.forms.part;

import cn.cerc.jbean.client.LocalService;
import cn.cerc.jbean.form.IPage;
import cn.cerc.jdb.core.DataSet;
import cn.cerc.jdb.core.Record;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;
import cn.cerc.jmis.page.RedirectPage;

public class FrmPartmodify extends AbstractForm {
    @Override
    public IPage execute() throws Exception {
        JspPage jspPage = new JspPage(this, "part/FrmPartmodify.jsp");
        String code = this.getRequest().getParameter("code");
        LocalService svr = new LocalService(this);
        svr.setService("svrPartinfo.modifysch");
        // svr.getDataIn().getHead().setField("Code_", code);
        Record headIn = svr.getDataIn().getHead();
        headIn.setField("Code_", code);
        if (!svr.exec()) {
            jspPage.setMessage(svr.getMessage());
            return jspPage;
        }
        DataSet ds = svr.getDataOut();
        if (!ds.eof()) {
            PartInfo_Record item = new PartInfo_Record();
            item.setCode(ds.getString("Code_"));
            item.setDesc(ds.getString("Desc_"));
            item.setSpec(ds.getString("Spec_"));
            item.setUnit(ds.getString("Unit_"));
            item.setStock(ds.getDouble("Stock_"));
            jspPage.add("item", item);
        }

        return jspPage;
    }

    public IPage modify() {
        JspPage jspPage = new JspPage(this, "part/FrmPartmodify.jsp");
        String submit = this.getRequest().getParameter("submit");
        if (submit != null) {
            String Partcode = this.getRequest().getParameter("Code");
            String spec = this.getRequest().getParameter("Spec");
            String desc = this.getRequest().getParameter("Desc");
            String unit = this.getRequest().getParameter("Unit");
            LocalService svr = new LocalService(this);
            svr.setService("svrPartinfo.modify");
            Record headIn = svr.getDataIn().getHead();
            headIn.setField("Code_", Partcode);
            headIn.setField("Desc_", desc);
            headIn.setField("Spec_", spec);
            headIn.setField("Unit_", unit);
            if (!svr.exec()) {
                jspPage.setMessage(svr.getMessage());
                return jspPage;
            }
        }
        return new RedirectPage(this, "FrmPartinfo");
    }

    @Override
    public boolean logon() {
        return true;
    }
}
