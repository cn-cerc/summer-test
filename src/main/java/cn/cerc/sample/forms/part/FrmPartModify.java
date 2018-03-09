package cn.cerc.sample.forms.part;

import cn.cerc.jbean.client.LocalService;
import cn.cerc.jbean.form.IPage;
import cn.cerc.jdb.core.DataSet;
import cn.cerc.jdb.core.Record;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;

public class FrmPartModify extends AbstractForm {
    @Override
    public IPage execute() {
        // 修改商品资料
        JspPage jspPage = new JspPage(this, "part/FrmPartModify.jsp");
        String code = this.getRequest().getParameter("code");
        LocalService svr = new LocalService(this);
        svr.setService("SvrPartInfo.searchSingle");
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
        // 新增加商品资料
        JspPage jspPage = new JspPage(this, "part/FrmPartModify.jsp");
        String code = this.getRequest().getParameter("Code");
        String desc = this.getRequest().getParameter("Desc");
        String spec = this.getRequest().getParameter("Spec");
        String unit = this.getRequest().getParameter("Unit");

        String submit = this.getRequest().getParameter("submit");

        if (submit != null) {
            LocalService svr = new LocalService(this);
            svr.setService("SvrPartInfo.modify");

            Record headIn = svr.getDataIn().getHead();
            headIn.setField("Code_", code);
            headIn.setField("Desc_", desc);
            headIn.setField("Spec_", spec);
            headIn.setField("Unit_", unit);

            if (svr.exec()) {
                jspPage.setMessage(svr.getMessage());
                return jspPage;
            }
        }

        return jspPage;
    }

    @Override
    public boolean logon() {
        return true;
    }
}
