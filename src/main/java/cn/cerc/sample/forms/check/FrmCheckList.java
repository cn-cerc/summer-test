package cn.cerc.sample.forms.check;

import cn.cerc.jbean.client.LocalService;
import cn.cerc.jbean.form.IPage;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;

public class FrmCheckList extends AbstractForm {

	@Override
	public IPage execute() throws Exception {
		JspPage jspPage = new JspPage(this, "check/FrmCheckList.jsp");
		String tbNo = getRequest().getParameter("tbNo");
		LocalService svr = new LocalService(this);
		svr.setService("SvrCheck.findCheck");
		svr.getDataIn().getHead().setField("tbNo", tbNo);
		if (!svr.exec()) {
			jspPage.add("message", svr.getMessage());
			return jspPage;
		}
		jspPage.add("checkh", svr.getDataOut().getHead());
		jspPage.add("checkbs", svr.getDataOut().getRecords());
		return jspPage;
	}

	@Override
	public boolean logon() {

		return true;
	}
}
