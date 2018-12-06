package cn.cerc.sample.forms;

import cn.cerc.mis.core.AbstractForm;
import cn.cerc.mis.core.IPage;
import cn.cerc.mis.page.JspPage;

public class FrmClientTest extends AbstractForm {

    @Override
    public IPage execute() throws Exception {
        return new JspPage(this, "common/FrmClientTest.jsp");
    }

    @Override
    public boolean logon() {
        return true;
    }
}
