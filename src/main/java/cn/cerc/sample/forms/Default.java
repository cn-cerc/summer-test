package cn.cerc.sample.forms;

import cn.cerc.jbean.form.IPage;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;

public class Default extends AbstractForm {

	@Override
	public IPage execute() {
		JspPage jsp = new JspPage(this);
		jsp.setJspFile("common/default.jsp");
		return jsp;
	}

	public IPage hello() {
		return new JspPage(this, "common/hello.jsp");
	}
}
