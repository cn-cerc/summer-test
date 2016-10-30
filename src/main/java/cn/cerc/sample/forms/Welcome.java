package cn.cerc.sample.forms;

import cn.cerc.jbean.form.IPage;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;

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
