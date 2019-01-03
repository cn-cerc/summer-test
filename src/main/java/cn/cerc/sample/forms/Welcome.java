package cn.cerc.sample.forms;

import org.springframework.stereotype.Component;

import cn.cerc.mis.core.AbstractForm;
import cn.cerc.mis.core.IPage;
import cn.cerc.mis.page.JspPage;

@Component
public class Welcome extends AbstractForm {

    @Override
    public IPage execute() {
        return new JspPage(this, "common/welcome.jsp");
    }

    @Override
    public boolean logon() {
        return true;
    }
}
