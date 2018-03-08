package cn.cerc.sample.forms.check;

import cn.cerc.jbean.client.LocalService;
import cn.cerc.jbean.form.IPage;
import cn.cerc.jdb.core.TDateTime;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;
import cn.cerc.jmis.page.RedirectPage;

public class FrmCheckhAppend extends AbstractForm {

	@Override
	public IPage execute() throws Exception {

		JspPage jspPage = new JspPage(this, "check/FrmCheckhAdd.jsp");

		String tbNo = getRequest().getParameter("tbNo");
		String tbDate = getRequest().getParameter("tbDate");
		String deptName = getRequest().getParameter("deptName");
		String appUser = getRequest().getParameter("appUser");
		LocalService svr1 = new LocalService(this);
		svr1.setService("SvrCheck.saveCheckh");
		svr1.getDataIn().getHead().setField("tbNo", tbNo);
		svr1.getDataIn().getHead().setField("tbDate", tbDate);
		svr1.getDataIn().getHead().setField("deptName", deptName);
		svr1.getDataIn().getHead().setField("appUser", appUser);
		if (!svr1.exec()) {

			jspPage.add("date", TDateTime.Now().getDate());
			jspPage.add("message", svr1.getMessage());
			return jspPage;
		}
		return new RedirectPage(this, String.format("FrmCheckList?tbNo=%s", tbNo));
	}

	@Override
	public boolean logon() {
		return true;
	}

}
