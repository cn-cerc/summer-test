package cn.cerc.sample.forms.sale;

import cn.cerc.jbean.client.LocalService;
import cn.cerc.jbean.form.IPage;
import cn.cerc.jdb.core.TDateTime;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;
import cn.cerc.jmis.page.RedirectPage;

public class FrmSale extends AbstractForm {

	@Override
	public IPage execute() throws Exception {
		JspPage jspPage = new JspPage(this, "sale/FrmSaleList.jsp");
		String tbNo = getRequest().getParameter("tbNo");
		LocalService svr = new LocalService(this);
		svr.setService("SvrSale.findSale");
		svr.getDataIn().getHead().setField("tbNo", tbNo);
		if (!svr.exec()) {
			jspPage.add("message", svr.getMessage());
			return jspPage;
		}
		jspPage.add("saleh", svr.getDataOut().getHead());
		jspPage.add("salebs", svr.getDataOut().getRecords());
		return jspPage;
	}

	@Override
	public boolean logon() {
		return true;
	}

	public IPage addSaleb() {
		JspPage jspPage = new JspPage(this, "sale/FrmSalebAdd.jsp");
		String tbNo = getRequest().getParameter("tbNo");
		LocalService service = new LocalService(this);
		service.setService("SvrProduct.search");
		if (!service.exec()) {
			jspPage.setMessage(service.getMessage());
			return jspPage;
		}
		jspPage.add("tbNo", tbNo);
		jspPage.add("items", service.getDataOut().getRecords());
		return jspPage;
	}

	public IPage addSaleh() {
		JspPage jspPage = new JspPage(this, "sale/FrmSalehAdd.jsp");
		jspPage.add("date", TDateTime.Now().getDate());
		return jspPage;
	}

	public IPage appendSaleh() {
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
		return new RedirectPage(this, String.format("FrmSale?tbNo=%s", tbNo));
	}

	public IPage appendSaleb() {
		JspPage jspPage = new JspPage(this, "sale/FrmSalebAdd.jsp");
		String tbNo = getRequest().getParameter("tbNo");
		String code = getRequest().getParameter("code");
		String stock = getRequest().getParameter("stock");
		LocalService svr = new LocalService(this);
		svr.setService("SvrSale.salebAdd");
		svr.getDataIn().getHead().setField("tbNo", tbNo);
		svr.getDataIn().getHead().setField("code", code);
		svr.getDataIn().getHead().setField("stock", stock);
		if (!svr.exec()) {
			LocalService svr2 = new LocalService(this);
			svr2.setService("SvrProduct.search");
			if (!svr2.exec()) {
				jspPage.setMessage(svr.getMessage());
				return jspPage;
			}
			jspPage.add("tbNo", tbNo);
			jspPage.add("items", svr.getDataOut().getRecords());
			jspPage.add("message", svr.getMessage());
			return jspPage;
		}
		return new RedirectPage(this, String.format("FrmSale?tbNo=%s", tbNo));
	}

	public IPage deleteSaleb() {
		JspPage jspPage = new JspPage(this, "sale/FrmSalebModify.jsp");
		LocalService svr = new LocalService(this);
		String tbNo = getRequest().getParameter("tbNo");
		String code = getRequest().getParameter("code");
		String num = getRequest().getParameter("num");
		svr.setService("SvrSale.salebDelete");
		svr.getDataIn().getHead().setField("code", code);
		svr.getDataIn().getHead().setField("num", num);
		svr.getDataIn().getHead().setField("tbNo", tbNo);
		if (!svr.exec()) {
			jspPage.add("message", svr.getMessage());
			return jspPage;
		}
		return new RedirectPage(this, String.format("FrmSale?tbNo=%s", tbNo));
	}

	public IPage listSaleh() {
		JspPage jspPage = new JspPage(this, "sale/FrmSalehList.jsp");
		LocalService svr = new LocalService(this);
		svr.setService("SvrSale.findSaleh");
		if (!svr.exec()) {
			jspPage.add("message", svr.getMessage());
			return jspPage;
		}
		jspPage.add("salehs", svr.getDataOut().getRecords());
		return jspPage;
	}

	public IPage modifySaleb() {
		JspPage jspPage = new JspPage(this, "sale/FrmSalebModify.jsp");
		LocalService svr = new LocalService(this);
		svr.setService("SvrSale.salebModify");
		String code = getRequest().getParameter("code");
		svr.getDataIn().getHead().setField("code", code);
		String tbNo = getRequest().getParameter("tbNo");
		svr.getDataIn().getHead().setField("tbNo", tbNo);
		if (!svr.exec()) {
			jspPage.add("message", svr.getMessage());
			return jspPage;
		}
		jspPage.add("saleb", svr.getDataOut().getHead());
		return jspPage;
	}

	public IPage modifySaleh() {
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
		return new RedirectPage(this, String.format("FrmSale?tbNo=%s", tbNo));
	}

	public IPage updateSaleb() {
		JspPage jspPage = new JspPage(this, "sale/FrmSalebModify.jsp");
		LocalService svr = new LocalService(this);
		String tbNo = getRequest().getParameter("tbNo");
		String code = getRequest().getParameter("code");
		String num = getRequest().getParameter("num");
		svr.setService("SvrSale.salebUpdate");
		svr.getDataIn().getHead().setField("code", code);
		svr.getDataIn().getHead().setField("num", num);
		svr.getDataIn().getHead().setField("tbNo", tbNo);
		if (!svr.exec()) {
			LocalService svr2 = new LocalService(this);
			svr2.setService("SvrSale.salebModify");
			svr2.getDataIn().getHead().setField("code", code);
			if (!svr2.exec()) {
				jspPage.setMessage(svr.getMessage());
				return jspPage;
			}
			jspPage.add("saleb", svr.getDataOut().getHead());
			jspPage.add("message", svr.getMessage());
			return jspPage;
		}

		return new RedirectPage(this, String.format("FrmSale?tbNo=%s", tbNo));
	}
}
