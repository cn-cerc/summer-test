package cn.cerc.sample.forms.sale;

import cn.cerc.jbean.client.LocalService;
import cn.cerc.jbean.form.IPage;
import cn.cerc.jdb.core.DataSet;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;
import cn.cerc.jmis.page.RedirectPage;
import cn.cerc.sample.pojo.SaleB;

public class FrmSalebUpdate extends AbstractForm {

	@Override
	public IPage execute() throws Exception {
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

			DataSet dataOut = svr2.getDataOut();
			SaleB saleb = new SaleB();
			while (dataOut.fetch()) {
				saleb.setTbNo(dataOut.getString("TBNo_"));
				saleb.setIt(dataOut.getInt("It_"));
				saleb.setCode(dataOut.getString("Code_"));
				saleb.setDesc(dataOut.getString("Desc_"));
				saleb.setSpec(dataOut.getString("Spec_"));
				saleb.setUnit(dataOut.getString("Unit_"));
				saleb.setNum(dataOut.getDouble("Num_"));
			}
			jspPage.add("saleb", saleb);

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
