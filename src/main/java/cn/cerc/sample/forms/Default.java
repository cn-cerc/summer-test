package cn.cerc.sample.forms;

import org.springframework.stereotype.Component;

import cn.cerc.mis.core.AbstractForm;
import cn.cerc.mis.core.IPage;
import cn.cerc.mis.page.JspPage;

@Component
public class Default extends AbstractForm {

    @Override
    public IPage execute() {
        return new JspPage(this, "common/default.jsp");
    }

    public IPage hello() {
        return new JspPage(this, "common/hello.jsp");
    }

    @Override
    public boolean logon() {
        return true;
    }
}
