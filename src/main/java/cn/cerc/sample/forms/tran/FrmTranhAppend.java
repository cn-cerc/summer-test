package cn.cerc.sample.forms.tran;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.cerc.jbean.client.LocalService;
import cn.cerc.jbean.form.IPage;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;
import cn.cerc.jmis.page.RedirectPage;

public class FrmTranhAppend extends AbstractForm {

	@Override
	public IPage execute() throws Exception {
		JspPage jspPage = new JspPage(this, "tran/FrmTranhAdd.jsp");

		String tbNo = getRequest().getParameter("tbNo");
		String tbDate = getRequest().getParameter("tbDate");
		String supName = getRequest().getParameter("superName");
		String appUser = getRequest().getParameter("appUser");
		LocalService svr = new LocalService(this);
		svr.setService("SvrTran.find");
		svr.getDataIn().getHead().setField("tbNo", tbNo);
		svr.getDataIn().getHead().setField("tbDate", tbDate);
		svr.getDataIn().getHead().setField("supName", supName);
		svr.getDataIn().getHead().setField("appUser", appUser);
		if (!svr.exec()) {
			SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			String format = simple.format(date);
			jspPage.add("date", format);
			jspPage.add("message", svr.getMessage());
			return jspPage;
		}

		return new RedirectPage(this, String.format("FrmTran?tbNo=%s", tbNo));
	}

	@Override
	public boolean logon() {

		return true;
	}
}
