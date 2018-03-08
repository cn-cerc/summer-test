package cn.cerc.sample.forms.sale;

import cn.cerc.jbean.client.LocalService;
import cn.cerc.jbean.form.IPage;
import cn.cerc.jdb.core.DataSet;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;
import cn.cerc.sample.pojo.SaleB;

public class FrmSalebModify extends AbstractForm {

	@Override
	public IPage execute() throws Exception {
		JspPage jspPage = new JspPage(this, "sale/FrmSalebModify.jsp");
		LocalService svr = new LocalService(this);
		svr.setService("SvrSale.salebModify");
		String code = getRequest().getParameter("code");
		svr.getDataIn().getHead().setField("code", code);

		if (!svr.exec()) {
			jspPage.add("message", svr.getMessage());
			return jspPage;
		}
		DataSet dataOut = svr.getDataOut();
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
		return jspPage;
	}

	@Override
	public boolean logon() {

		return true;
	}
}
