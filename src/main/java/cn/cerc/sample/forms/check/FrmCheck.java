package cn.cerc.sample.forms.check;

import cn.cerc.jbean.client.LocalService;
import cn.cerc.jbean.form.IPage;
import cn.cerc.jdb.core.TDateTime;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;
import cn.cerc.jmis.page.RedirectPage;

public class FrmCheck extends AbstractForm {

	@Override
	public IPage execute() throws Exception {
		JspPage jspPage = new JspPage(this, "check/FrmCheckList.jsp");
		String tbNo = getRequest().getParameter("tbNo");
		LocalService svr = new LocalService(this);
		svr.setService("SvrCheck.findCheck");
		svr.getDataIn().getHead().setField("tbNo", tbNo);
		if (!svr.exec()) {
			jspPage.add("message", svr.getMessage());
			return jspPage;
		}
		jspPage.add("checkh", svr.getDataOut().getHead());
		jspPage.add("checkbs", svr.getDataOut().getRecords());
		return jspPage;
	}

	@Override
	public boolean logon() {
		return true;
	}

	public IPage addCheckb() {
		JspPage jspPage = new JspPage(this, "check/FrmCheckbAdd.jsp");
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

	public IPage appendCheckb() {
		JspPage jspPage = new JspPage(this, "check/FrmCheckbAdd.jsp");
		String tbNo = getRequest().getParameter("tbNo");
		String code = getRequest().getParameter("code");
		String stock = getRequest().getParameter("stock");
		LocalService svr = new LocalService(this);
		svr.setService("SvrCheck.saveCheckb");
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
			jspPage.add("items", svr2.getDataOut().getRecords());
			jspPage.add("message", svr.getMessage());
			return jspPage;
		}
		return new RedirectPage(this, String.format("FrmCheck?tbNo=%s", tbNo));
	}

	public IPage deleteCheckb() {
		JspPage jspPage = new JspPage(this, "check/FrmCheckbModify.jsp");
		LocalService svr = new LocalService(this);
		String tbNo = getRequest().getParameter("tbNo");
		String code = getRequest().getParameter("code");
		String num = getRequest().getParameter("num");
		svr.setService("SvrCheck.checkbDelete");
		svr.getDataIn().getHead().setField("code", code);
		svr.getDataIn().getHead().setField("num", num);
		svr.getDataIn().getHead().setField("tbNo", tbNo);
		if (!svr.exec()) {
			jspPage.add("message", svr.getMessage());
			return jspPage;
		}
		return new RedirectPage(this, String.format("FrmCheck?tbNo=%s", tbNo));
	}

	public IPage modifyCheckb() {
		JspPage jspPage = new JspPage(this, "check/FrmCheckbModify.jsp");
		LocalService svr = new LocalService(this);
		svr.setService("SvrCheck.checkbModify");
		String code = getRequest().getParameter("code");
		svr.getDataIn().getHead().setField("code", code);
		String tbNo = getRequest().getParameter("tbNo");
		svr.getDataIn().getHead().setField("tbNo", tbNo);
		if (!svr.exec()) {
			jspPage.add("message", svr.getMessage());
			return jspPage;
		}
		jspPage.add("checkb", svr.getDataOut().getHead());
		return jspPage;
	}

	public IPage updateCheckb() {
		JspPage jspPage = new JspPage(this, "check/FrmCheckbModify.jsp");
		LocalService svr = new LocalService(this);
		String tbNo = getRequest().getParameter("tbNo");
		String code = getRequest().getParameter("code");
		String num = getRequest().getParameter("num");
		svr.setService("SvrCheck.checkbUpdate");
		svr.getDataIn().getHead().setField("code", code);
		svr.getDataIn().getHead().setField("num", num);
		svr.getDataIn().getHead().setField("tbNo", tbNo);
		if (!svr.exec()) {
			LocalService svr2 = new LocalService(this);
			svr2.setService("SvrCheck.checkbModify");
			svr2.getDataIn().getHead().setField("code", code);
			if (!svr2.exec()) {
				jspPage.setMessage(svr.getMessage());
				return jspPage;
			}
			jspPage.add("check", svr2.getDataOut().getHead());
			jspPage.add("message", svr.getMessage());
			return jspPage;
		}
		return new RedirectPage(this, String.format("FrmCheck?tbNo=%s", tbNo));
	}

	public IPage addCheckh() {
		JspPage jspPage = new JspPage(this, "check/FrmCheckhAdd.jsp");
		jspPage.add("date", TDateTime.Now().getDate());
		return jspPage;
	}

	public IPage appendCheckh() {
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
		return new RedirectPage(this, String.format("FrmCheck?tbNo=%s", tbNo));
	}

	public IPage listCheckh() {
		JspPage jspPage = new JspPage(this, "check/FrmCheckhList.jsp");
		LocalService svr = new LocalService(this);
		svr.setService("SvrCheck.findCheckh");
		if (!svr.exec()) {
			jspPage.add("message", svr.getMessage());
			return jspPage;
		}
		jspPage.add("checkhs", svr.getDataOut().getRecords());
		return jspPage;
	}

	public IPage modifyCheckh() {
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
		return new RedirectPage(this, String.format("FrmSale?tbNo=%s", tbNo));
	}
}
