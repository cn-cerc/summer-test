package cn.cerc.sample.forms.tran;

import cn.cerc.jbean.client.LocalService;
import cn.cerc.jbean.form.IPage;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;
import cn.cerc.jmis.page.RedirectPage;

public class FrmTranhModify extends AbstractForm {

	@Override
	public IPage execute() throws Exception {
		JspPage jspPage = new JspPage(this, "tran/FrmTran.jsp");
		LocalService svr = new LocalService(this);
		svr.setService("SvrTran.tranhModify");
		String tbNo = getRequest().getParameter("tbNo");
		String supName = getRequest().getParameter("supName");
		svr.getDataIn().getHead().setField("tbNo", tbNo);
		svr.getDataIn().getHead().setField("supName", supName);
		if (!svr.exec()) {
			jspPage.setMessage(svr.getMessage());
			return jspPage;
		}
		return new RedirectPage(this, String.format("FrmTran?tbNo=%s", tbNo));
	}

	@Override
	public boolean logon() {

		return true;
	}
}
