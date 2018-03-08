package cn.cerc.sample.forms.sale;

import cn.cerc.jbean.client.LocalService;
import cn.cerc.jbean.form.IPage;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;
import cn.cerc.jmis.page.RedirectPage;

public class FrmSalehModify extends AbstractForm {

	@Override
	public IPage execute() throws Exception {
		JspPage jspPage = new JspPage(this, "sale/FrmSaleList.jsp");
		LocalService svr = new LocalService(this);
		svr.setService("SvrSale.salehModify");
		String tbNo = getRequest().getParameter("tbNo");
		String cusName = getRequest().getParameter("cusName");
		svr.getDataIn().getHead().setField("tbNo", tbNo);
		svr.getDataIn().getHead().setField("cusName", cusName);
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
