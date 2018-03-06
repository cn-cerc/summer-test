package cn.cerc.sample.forms;

import cn.cerc.jbean.form.IPage;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;

public class FrmWelcome extends AbstractForm {

    @Override
    public IPage execute() {
        JspPage jspPage = new JspPage(this);
        jspPage.setJspFile("common/FrmWelcome.jsp");

        return jspPage;
    }

    public IPage list() {
        return null;
    }

    @Override
    public boolean logon() {
        return true;
    }
}
