package cn.cerc.sample.forms;

import cn.cerc.jbean.form.IPage;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;

public class FrmDefault extends AbstractForm {

    @Override
    public IPage execute() {
        return new JspPage(this, "common/FrmDefault.jsp");
    }

    public IPage hello() {
        return new JspPage(this, "common/hello.jsp");
    }

    @Override
    public boolean logon() {
        return true;
    }
}
