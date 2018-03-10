package cn.cerc.sample.forms.inAndOut;

import cn.cerc.jbean.client.LocalService;
import cn.cerc.jbean.form.IPage;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;

public class FrmInAndOut extends AbstractForm {

	@Override
	public IPage execute() throws Exception {
		JspPage jspPage = new JspPage(this, "inAndOut/FrmInAndOutList.jsp");

		return jspPage;
	}

	@Override
	public boolean logon() {

		return true;
	}

	public IPage search() {
		JspPage jspPage = new JspPage(this, "inAndOut/FrmInAndOutList.jsp");
		String tbNo = getRequest().getParameter("tbNo");
		String productName = getRequest().getParameter("productName");
		String object = getRequest().getParameter("object");
		String startTime = getRequest().getParameter("startTime");
		String endTime = getRequest().getParameter("endTime");
		LocalService svr = new LocalService(this);
		svr.setService("SvrInAndOut.search");
		svr.getDataIn().getHead().setField("tbNo", tbNo);
		svr.getDataIn().getHead().setField("productName", productName);
		svr.getDataIn().getHead().setField("object", object);
		svr.getDataIn().getHead().setField("startTime", startTime);
		svr.getDataIn().getHead().setField("endTime", endTime);
		if (!svr.exec()) {
			jspPage.add("message", svr.getMessage());
			return jspPage;
		}
		jspPage.add("tbNo", tbNo);
		jspPage.add("productName", productName);
		jspPage.add("object", object);
		jspPage.add("startTime", startTime);
		jspPage.add("endTime", endTime);
		jspPage.add("dataSet", svr.getDataOut().getRecords());
		return jspPage;
	}
}
