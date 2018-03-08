package cn.cerc.sample.forms.sale;

import cn.cerc.jbean.client.LocalService;
import cn.cerc.jbean.form.IPage;
import cn.cerc.jdb.core.TDateTime;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;
import cn.cerc.jmis.page.RedirectPage;

public class FrmSalehAppend extends AbstractForm {

	@Override
	public IPage execute() throws Exception {
		JspPage jspPage = new JspPage(this, "sale/FrmSalehAdd.jsp");

		String tbNo = getRequest().getParameter("tbNo");
		String tbDate = getRequest().getParameter("tbDate");
		String cusName = getRequest().getParameter("cusName");
		String appUser = getRequest().getParameter("appUser");
		LocalService svr = new LocalService(this);
		svr.setService("SvrSale.saveSaleh");
		svr.getDataIn().getHead().setField("tbNo", tbNo);
		svr.getDataIn().getHead().setField("tbDate", tbDate);
		svr.getDataIn().getHead().setField("cusName", cusName);
		svr.getDataIn().getHead().setField("appUser", appUser);
		if (!svr.exec()) {
			jspPage.add("date", TDateTime.Now().getDate());
			jspPage.add("message", svr.getMessage());
			return jspPage;
		}
		return new RedirectPage(this, String.format("FrmSaleList?tbNo=%s", tbNo));
	}

	@Override
	public boolean logon() {

		return true;
	}
}
