package cn.cerc.sample.forms;

import cn.cerc.jbean.form.IPage;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;

public class Default extends AbstractForm {

	@Override
	public IPage execute() {
		return new JspPage(this,"common/default.jsp");
	}

	public IPage hello() {
		return new JspPage(this, "common/hello.jsp");
	}
}
