package cn.cerc.sample.forms.sale;

import java.util.ArrayList;
import java.util.List;

import cn.cerc.jbean.client.LocalService;
import cn.cerc.jbean.form.IPage;
import cn.cerc.jdb.core.DataSet;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;
import cn.cerc.sample.pojo.SaleH;

public class FrmSalehList extends AbstractForm {

	@Override
	public IPage execute() throws Exception {
		JspPage jspPage = new JspPage(this, "sale/FrmSalehList.jsp");
		LocalService svr = new LocalService(this);
		svr.setService("SvrSale.findSaleh");
		DataSet dataOut = svr.getDataOut();
		if (!svr.exec()) {
			jspPage.setMessage(svr.getMessage());
			return jspPage;
		}
		List<SaleH> list = new ArrayList<>();
		while (dataOut.fetch()) {
			SaleH saleh = new SaleH();
			saleh.setTb(dataOut.getString("TB_"));
			saleh.setAppDate(dataOut.getDateTime("AppDate_"));
			saleh.setAppUser(dataOut.getString("AppUser_"));
			saleh.setTbNo(dataOut.getString("TBNo_"));
			saleh.setCusName(dataOut.getString("CusName_"));
			saleh.setTbDate(dataOut.getDate("TBDate_"));
			list.add(saleh);
		}
		jspPage.add("salehs", list);
		return jspPage;
	}

	@Override
	public boolean logon() {

		return true;
	}
}
