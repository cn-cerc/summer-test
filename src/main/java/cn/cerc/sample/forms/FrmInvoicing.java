package cn.cerc.sample.forms;

import cn.cerc.jbean.form.IPage;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;

public class FrmInvoicing extends AbstractForm {

    @Override
    public IPage execute() {
        JspPage jspPage = new JspPage(this, "common/FrmInvoicing.jsp");
        return jspPage;
    }

    @Override
    public boolean logon() {
        return true;
    }
}
