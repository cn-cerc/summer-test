package cn.cerc.sample.forms.part;

import cn.cerc.jbean.client.LocalService;
import cn.cerc.jbean.form.IPage;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;
import cn.cerc.jmis.page.RedirectPage;

public class FrmProduct extends AbstractForm {

	@Override
	public IPage execute() throws Exception {
		JspPage page = new JspPage(this, "part/FrmShow.jsp");
		LocalService service = new LocalService(this);
		service.setService("SvrProduct.search");
		if (!service.exec()) {
			page.setMessage(service.getMessage());
			return page;
		}
		page.add("items", service.getDataOut().getRecords());
		return page;
	}

	@Override
	public boolean logon() {
		return true;
	}

	public IPage add() {
		JspPage jspPage = new JspPage(this, "part/FrmProductAdd.jsp");
		return jspPage;
	}

	public IPage append() {
		JspPage jspPage = new JspPage(this, "part/FrmProductAdd.jsp");
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
			jspPage.add("message", svr.getMessage());
			return jspPage;
		}
		return new RedirectPage(this, String.format("FrmProduct.modify?code=%s", code));
	}

	public IPage modify() {
		JspPage jspPage = new JspPage(this, "part/FrmProductModify.jsp");
		String code = getRequest().getParameter("code");
		LocalService svr = new LocalService(this);
		svr.setService("SvrProduct.findById");
		svr.getDataIn().getHead().setField("code", code);
		if (!svr.exec()) {
			jspPage.setMessage(svr.getMessage());
			return jspPage;
		}
		jspPage.add("product", svr.getDataOut().getHead());
		return jspPage;
	}

	public IPage update() {
		JspPage jspPage = new JspPage(this, "part/FrmProductModify.jsp");
		LocalService svr = new LocalService(this);
		String code = getRequest().getParameter("code");
		String desc = getRequest().getParameter("desc");
		String spec = getRequest().getParameter("spec");
		String unit = getRequest().getParameter("unit");
		svr.setService("SvrProduct.update");
		svr.getDataIn().getHead().setField("code", code);
		svr.getDataIn().getHead().setField("desc", desc);
		svr.getDataIn().getHead().setField("spec", spec);
		svr.getDataIn().getHead().setField("unit", unit);
		if (!svr.exec()) {
			jspPage.setMessage(svr.getMessage());
			return jspPage;
		}
		return new RedirectPage(this, "FrmProduct");

	}

	public IPage delete() {
		JspPage jspPage = new JspPage(this, "part/FrmShow.jsp");
		LocalService svr = new LocalService(this);
		svr.setService("SvrProduct.delete");
		svr.getDataIn().getHead().setField("code", getRequest().getParameter("code"));
		if (!svr.exec()) {
			jspPage.setMessage(svr.getMessage());
			return jspPage;
		}
		return new RedirectPage(this, "FrmProduct");
	}
}
