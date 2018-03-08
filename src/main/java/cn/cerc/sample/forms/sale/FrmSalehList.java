package cn.cerc.sample.forms.sale;

import cn.cerc.jbean.client.LocalService;
import cn.cerc.jbean.form.IPage;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;

public class FrmSalehList extends AbstractForm {

	@Override
	public IPage execute() throws Exception {
		JspPage jspPage = new JspPage(this, "sale/FrmSalehList.jsp");
		LocalService svr = new LocalService(this);
		svr.setService("SvrSale.findSaleh");
		if (!svr.exec()) {
			jspPage.add("message", svr.getMessage());
			return jspPage;
		}

		// DataSet dataOut = svr.getDataOut();
		// List<SaleH> list = new ArrayList<>();
		// while (dataOut.fetch()) {
		// SaleH saleh = new SaleH();
		// saleh.setTb(dataOut.getString("TB_"));
		// saleh.setAppDate(dataOut.getDateTime("AppDate_"));
		// saleh.setAppUser(dataOut.getString("AppUser_"));
		// saleh.setTbNo(dataOut.getString("TBNo_"));
		// saleh.setCusName(dataOut.getString("CusName_"));
		// saleh.setTbDate(dataOut.getDate("TBDate_"));
		// list.add(saleh);
		// }
		jspPage.add("salehs", svr.getDataOut());
		return jspPage;
	}

	@Override
	public boolean logon() {

		return true;
	}
}
