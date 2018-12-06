package cn.cerc.sample.forms;

import cn.cerc.mis.core.AbstractForm;
import cn.cerc.mis.core.IPage;
import cn.cerc.mis.page.JspPage;

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
