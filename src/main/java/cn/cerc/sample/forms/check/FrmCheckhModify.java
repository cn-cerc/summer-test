package cn.cerc.sample.forms.check;

import cn.cerc.jbean.form.IPage;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;

public class FrmCheckhModify extends AbstractForm {

	@Override
	public IPage execute() throws Exception {

		JspPage jspPage = new JspPage(this);
		return jspPage;
	}

	@Override
	public boolean logon() {
		return true;
	}

}
