package cn.cerc.sample.forms;

import cn.cerc.mis.core.AbstractForm;
import cn.cerc.mis.core.IPage;
import cn.cerc.mis.page.JspPage;

public class FrmWelcome extends AbstractForm {

    @Override
    public IPage execute() {
        return new JspPage(this, "common/FrmWelcome.jsp");
    }

    @Override
    public boolean logon() {
        return true;
    }
}
