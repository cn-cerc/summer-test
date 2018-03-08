package cn.cerc.sample.forms.check;

import cn.cerc.jbean.form.IPage;
import cn.cerc.jdb.core.TDateTime;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;

public class FrmCheckhAdd extends AbstractForm {

	@Override
	public IPage execute() throws Exception {
		JspPage jspPage = new JspPage(this, "check/FrmCheckhAdd.jsp");

		jspPage.add("date", TDateTime.Now().getDate());
		return jspPage;
	}

	@Override
	public boolean logon() {
		return true;
	}

}
