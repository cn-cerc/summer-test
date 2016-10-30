package cn.cerc.sample.forms;

import cn.cerc.jbean.form.IPage;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;

public class Default extends AbstractForm {

	@Override
	public IPage execute() {
		getJspView().setFile("common/default.jsp");
		return getJspView().getPage();
	}

	public IPage hello() {
		return new JspPage(this, "common/hello.jsp");
	}
}
