package cn.cerc.sample.forms.check;

import cn.cerc.jbean.client.LocalService;
import cn.cerc.jbean.form.IPage;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;

public class FrmCheckbModify extends AbstractForm {

	@Override
	public IPage execute() throws Exception {

		JspPage jspPage = new JspPage(this, "check/FrmCheckbModify.jsp");
		LocalService svr = new LocalService(this);
		svr.setService("SvrCheck.checkbModify");
		String code = getRequest().getParameter("code");
		svr.getDataIn().getHead().setField("code", code);
		String tbNo = getRequest().getParameter("tbNo");
		svr.getDataIn().getHead().setField("tbNo", tbNo);
		if (!svr.exec()) {
			jspPage.add("message", svr.getMessage());
			return jspPage;
		}

		jspPage.add("checkb", svr.getDataOut().getHead());
		return jspPage;
	}

	@Override
	public boolean logon() {
		return true;
	}

}
