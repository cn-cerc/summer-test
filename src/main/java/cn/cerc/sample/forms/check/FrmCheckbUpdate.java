package cn.cerc.sample.forms.check;

import cn.cerc.jbean.client.LocalService;
import cn.cerc.jbean.form.IPage;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;
import cn.cerc.jmis.page.RedirectPage;

public class FrmCheckbUpdate extends AbstractForm {

	@Override
	public IPage execute() throws Exception {

		JspPage jspPage = new JspPage(this, "check/FrmCheckbModify.jsp");
		LocalService svr = new LocalService(this);
		String tbNo = getRequest().getParameter("tbNo");
		String code = getRequest().getParameter("code");
		String num = getRequest().getParameter("num");
		svr.setService("SvrCheck.checkbUpdate");
		svr.getDataIn().getHead().setField("code", code);
		svr.getDataIn().getHead().setField("num", num);
		svr.getDataIn().getHead().setField("tbNo", tbNo);
		if (!svr.exec()) {
			LocalService svr2 = new LocalService(this);
			svr2.setService("SvrCheck.checkbModify");
			svr2.getDataIn().getHead().setField("code", code);

			if (!svr2.exec()) {
				jspPage.setMessage(svr.getMessage());
				return jspPage;
			}

			jspPage.add("check", svr2.getDataOut().getHead());
			jspPage.add("message", svr.getMessage());
			return jspPage;
		}
		return new RedirectPage(this, String.format("FrmCheckList?tbNo=%s", tbNo));
	}

	@Override
	public boolean logon() {
		return true;
	}

}
