package cn.cerc.sample.forms.tran;

import cn.cerc.jbean.client.LocalService;
import cn.cerc.jbean.form.IPage;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;
import cn.cerc.jmis.page.RedirectPage;

public class FrmTranbAppend extends AbstractForm {

	@Override
	public IPage execute() throws Exception {

		JspPage jspPage = new JspPage(this, "tran/FrmTranbAdd.jsp");
		LocalService svr = new LocalService(this);
		svr.setService("SvrTran.tranbAdd");
		String code = getRequest().getParameter("code");
		String stock = getRequest().getParameter("stock");
		String tbNo = getRequest().getParameter("tbNo");
		svr.getDataIn().getHead().setField("code", code);
		svr.getDataIn().getHead().setField("stock", stock);
		svr.getDataIn().getHead().setField("tbNo", tbNo);
		if (!svr.exec()) {
			jspPage.add("message", svr.getMessage());
			return jspPage;
		}

		return new RedirectPage(this, String.format("FrmTran?tbNo=%s&code=%s", tbNo, code));

	}

	@Override
	public boolean logon() {

		return true;
	}
}
