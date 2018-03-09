package cn.cerc.sample.forms.inAndOut;

import cn.cerc.jbean.form.IPage;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;

public class FrmInAndOutList extends AbstractForm {

	@Override
	public IPage execute() throws Exception {
		JspPage jspPage = new JspPage(this, "inAndOut/FrmInAndOutList.jsp");

		return jspPage;
	}

	@Override
	public boolean logon() {

		return true;
	}
}
