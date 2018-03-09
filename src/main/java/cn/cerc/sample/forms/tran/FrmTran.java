package cn.cerc.sample.forms.tran;

import cn.cerc.jbean.client.LocalService;
import cn.cerc.jbean.form.IPage;
import cn.cerc.jdb.core.TDate;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;
import cn.cerc.jmis.page.RedirectPage;

public class FrmTran extends AbstractForm {

	@Override
	public IPage execute() throws Exception {
		JspPage jspPage = new JspPage(this, "tran/FrmTran.jsp");
		String tbNo = getRequest().getParameter("tbNo");
		LocalService svr = new LocalService(this);
		svr.setService("SvrTran.tranhFindByTbNo");
		svr.getDataIn().getHead().setField("tbNo", tbNo);
		if (!svr.exec()) {
			jspPage.setMessage(svr.getMessage());
			return jspPage;
		}
		jspPage.add("tranh", svr.getDataOut().getHead());
		jspPage.add("tranbs", svr.getDataOut().getRecords());
		return jspPage;
	}

	@Override
	public boolean logon() {
		return true;
	}

	public IPage addTranb() {
		JspPage jspPage = new JspPage(this, "tran/FrmTranbAdd.jsp");
		String tbNo = getRequest().getParameter("tbNo");
		LocalService svr = new LocalService(this);
		svr.setService("SvrProduct.search");
		if (!svr.exec()) {
			jspPage.setMessage(svr.getMessage());
			return jspPage;
		}
		jspPage.add("tbNo", tbNo);
		jspPage.add("items", svr.getDataOut().getRecords());
		return jspPage;
	}

	public IPage appendTranb() {
		JspPage jspPage = new JspPage(this, "tran/FrmTranbAdd.jsp");
		LocalService svr = new LocalService(this);
		svr.setService("SvrTran.tranbAdd");
		String code = getRequest().getParameter("code");
		String stock = getRequest().getParameter("stock");
		String tbNo = getRequest().getParameter("tbNo");
		svr.getDataIn().getHead().setField("code", code);
		svr.getDataIn().getHead().setField("stock", stock);
		svr.getDataIn().getHead().setField("tbNo", tbNo);
		if (!svr.exec()) {
			jspPage.add("message", svr.getMessage());
			return jspPage;
		}
		return new RedirectPage(this, String.format("FrmTran?tbNo=%s&code=%s", tbNo, code));

	}

	public IPage deleteTranb() {
		JspPage jspPage = new JspPage(this, "tran/FrmTranbModify.jsp");
		LocalService svr = new LocalService(this);
		String tbNo = getRequest().getParameter("tbNo");
		String code = getRequest().getParameter("code");
		String num = getRequest().getParameter("num");
		svr.setService("SvrTran.tranbDelete");
		svr.getDataIn().getHead().setField("code", code);
		svr.getDataIn().getHead().setField("num", num);
		svr.getDataIn().getHead().setField("tbNo", tbNo);
		if (!svr.exec()) {
			jspPage.setMessage(svr.getMessage());
			return jspPage;
		}
		return new RedirectPage(this, String.format("FrmTran?tbNo=%s&code=%s", tbNo, code));
	}

	public IPage modifyTranb() {
		JspPage jspPage = new JspPage(this, "tran/FrmTranbModify.jsp");
		LocalService svr = new LocalService(this);
		svr.setService("SvrTran.tranbModify");
		String tbNo = getRequest().getParameter("tbNo");
		svr.getDataIn().getHead().setField("tbNo", tbNo);
		String code = getRequest().getParameter("code");
		svr.getDataIn().getHead().setField("code", code);
		if (!svr.exec()) {
			jspPage.setMessage(svr.getMessage());
			return jspPage;
		}
		jspPage.add("tranb", svr.getDataOut().getHead());
		return jspPage;
	}

	public IPage updateTranb() {
		JspPage jspPage = new JspPage(this, "tran/FrmTranbModify.jsp");
		LocalService svr = new LocalService(this);
		String tbNo = getRequest().getParameter("tbNo");
		String code = getRequest().getParameter("code");
		String num = getRequest().getParameter("num");
		svr.setService("SvrTran.tranbUpdate");
		svr.getDataIn().getHead().setField("code", code);
		svr.getDataIn().getHead().setField("num", num);
		svr.getDataIn().getHead().setField("tbNo", tbNo);
		if (!svr.exec()) {
			jspPage.setMessage(svr.getMessage());
			return jspPage;
		}
		return new RedirectPage(this, String.format("FrmTran?tbNo=%s&code=%s", tbNo, code));
	}

	public IPage addTranh() {
		JspPage jspPage = new JspPage(this, "tran/FrmTranhAdd.jsp");
		jspPage.add("date", TDate.Now().getDate());
		return jspPage;
	}

	public IPage appendTranh() {
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
			jspPage.add("date", TDate.Now().getDate());
			jspPage.add("message", svr.getMessage());
			return jspPage;
		}
		return new RedirectPage(this, String.format("FrmTran?tbNo=%s", tbNo));
	}

	public IPage listTranh() {
		JspPage jspPage = new JspPage(this, "tran/FrmTranhList.jsp");
		LocalService svr = new LocalService(this);
		svr.setService("SvrTran.search");
		if (!svr.exec()) {
			jspPage.setMessage(svr.getMessage());
			return jspPage;
		}
		jspPage.add("tranhs", svr.getDataOut().getRecords());
		return jspPage;
	}

	public IPage modifyTranh() {
		JspPage jspPage = new JspPage(this, "tran/FrmTran.jsp");
		LocalService svr = new LocalService(this);
		svr.setService("SvrTran.tranhModify");
		String tbNo = getRequest().getParameter("tbNo");
		String supName = getRequest().getParameter("supName");
		svr.getDataIn().getHead().setField("tbNo", tbNo);
		svr.getDataIn().getHead().setField("supName", supName);
		if (!svr.exec()) {
			jspPage.setMessage(svr.getMessage());
			return jspPage;
		}
		return new RedirectPage(this, String.format("FrmTran?tbNo=%s", tbNo));
	}

}
