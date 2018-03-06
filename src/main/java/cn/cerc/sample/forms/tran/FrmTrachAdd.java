package cn.cerc.sample.forms.tran;

import cn.cerc.jbean.form.IPage;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;

public class FrmTrachAdd extends AbstractForm {

	@Override
	public IPage execute() throws Exception {
		JspPage jspPage = new JspPage(this, "tran/FrmTranHadd.jsp");
		return jspPage;
	}

	@Override
	public boolean logon() {

		return true;
	}
}
