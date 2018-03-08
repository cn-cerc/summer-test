package cn.cerc.sample.forms.sale;

import cn.cerc.jbean.form.IPage;
import cn.cerc.jdb.core.TDateTime;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;

public class FrmSalehAdd extends AbstractForm {

	@Override
	public IPage execute() throws Exception {
		JspPage jspPage = new JspPage(this, "sale/FrmSalehAdd.jsp");

		jspPage.add("date", TDateTime.Now().getDate());
		return jspPage;
	}

	@Override
	public boolean logon() {

		return true;
	}
}
