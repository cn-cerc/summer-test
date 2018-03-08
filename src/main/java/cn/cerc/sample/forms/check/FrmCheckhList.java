package cn.cerc.sample.forms.check;

import cn.cerc.jbean.client.LocalService;
import cn.cerc.jbean.form.IPage;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;

public class FrmCheckhList extends AbstractForm {

	@Override
	public IPage execute() throws Exception {

		JspPage jspPage = new JspPage(this, "check/FrmCheckhList.jsp");
		LocalService svr = new LocalService(this);
		svr.setService("SvrCheck.findCheckh");
		if (!svr.exec()) {
			jspPage.add("message", svr.getMessage());
			return jspPage;
		}

		jspPage.add("checkhs", svr.getDataOut().getRecords());
		return jspPage;
	}

	@Override
	public boolean logon() {
		return true;
	}

}
