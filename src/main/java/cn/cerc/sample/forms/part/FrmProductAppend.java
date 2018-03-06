package cn.cerc.sample.forms.part;

import cn.cerc.jbean.client.LocalService;
import cn.cerc.jbean.form.IPage;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;
import cn.cerc.jmis.page.RedirectPage;

public class FrmProductAppend extends AbstractForm {

	@Override
	public IPage execute() throws Exception {
		JspPage jspPage = new JspPage(this,"part/FrmProductAdd.jsp");
		LocalService svr = new LocalService(this);
		String code = getRequest().getParameter("code");
		String desc = getRequest().getParameter("desc");
		String spec = getRequest().getParameter("spec");
		String unit = getRequest().getParameter("unit");
		svr.setService("SvrProduct.add");
		svr.getDataIn().getHead().setField("code", code);
		svr.getDataIn().getHead().setField("desc", desc);
		svr.getDataIn().getHead().setField("spec", spec);
		svr.getDataIn().getHead().setField("unit", unit);
		if (!svr.exec()) { 
			jspPage.add("message",svr.getMessage());
			return jspPage;
		}
		return new RedirectPage(this, String.format("FrmProductModify?code=%s", code));
	}

	@Override
	public boolean logon() {

		return true;
	}
}
