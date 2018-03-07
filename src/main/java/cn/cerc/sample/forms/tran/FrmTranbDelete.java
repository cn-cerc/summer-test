package cn.cerc.sample.forms.tran;

import cn.cerc.jbean.client.LocalService;
import cn.cerc.jbean.form.IPage;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;
import cn.cerc.jmis.page.RedirectPage;

public class FrmTranbDelete extends AbstractForm {

	@Override
	public IPage execute() throws Exception {

		JspPage jspPage = new JspPage(this, "tran/FrmTranbModify.jsp");
		LocalService svr = new LocalService(this);
		String tbNo = getRequest().getParameter("tbNo");
		String code = getRequest().getParameter("code");
		String num = getRequest().getParameter("num");
		svr.setService("SvrTran.tranbDelete");
		svr.getDataIn().getHead().setField("code", code);
		svr.getDataIn().getHead().setField("num", num);
		svr.getDataIn().getHead().setField("tbNo", tbNo);
		if (!svr.exec()) {
			jspPage.setMessage(svr.getMessage());
			return jspPage;
		}

		return new RedirectPage(this, String.format("FrmTran?tbNo=%s&code=%s", tbNo, code));
	}

	@Override
	public boolean logon() {

		return true;
	}
}
