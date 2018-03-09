package cn.cerc.sample.forms.check;

import cn.cerc.jbean.client.LocalService;
import cn.cerc.jbean.form.IPage;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;
import cn.cerc.jmis.page.RedirectPage;

public class FrmCheckhModify extends AbstractForm {

	@Override
	public IPage execute() throws Exception {

		JspPage jspPage = new JspPage(this, "check/FrmCheckList.jsp");
		LocalService svr = new LocalService(this);
		svr.setService("SvrCheck.checkhModify");
		String tbNo = getRequest().getParameter("tbNo");
		String deptName = getRequest().getParameter("deptName");
		svr.getDataIn().getHead().setField("tbNo", tbNo);
		svr.getDataIn().getHead().setField("deptName", deptName);
		if (!svr.exec()) {
			jspPage.setMessage(svr.getMessage());
			return jspPage;
		}
		return new RedirectPage(this, String.format("FrmSaleList?tbNo=%s", tbNo));
	}

	@Override
	public boolean logon() {
		return true;
	}

}
