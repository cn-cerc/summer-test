package cn.cerc.sample.forms.sale;

import cn.cerc.jbean.client.LocalService;
import cn.cerc.jbean.form.IPage;
import cn.cerc.jdb.core.Record;
import cn.cerc.jdb.core.TDateTime;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;
import cn.cerc.sample.pojo.SaleH;

public class FrmSalehAdd extends AbstractForm {

	@Override
	public IPage execute() throws Exception {
		JspPage jspPage = new JspPage(this, "sale/FrmSalehAdd.jsp");

		String tbNo = getRequest().getParameter("tbNo");
		if (tbNo != null && !"".equals(tbNo)) {
			LocalService svr = new LocalService(this);
			svr.setService("SvrSale.findSalehByThno");
			svr.getDataIn().getHead().setField("tbNo", tbNo);
			if (!svr.exec()) {
				jspPage.setMessage(svr.getMessage());
				return jspPage;
			}
			Record head = svr.getDataOut().getHead();
			SaleH saleh = new SaleH();
			saleh.setTbNo(tbNo);
			saleh.setCusName(head.getString("CusName_"));
			saleh.setTbDate(head.getDate("TBDate_"));
			saleh.setAppUser(head.getString("AppUser_"));
			jspPage.add("saleh", saleh);

		}
		jspPage.add("date", TDateTime.Now().getDate());
		return jspPage;
	}

	@Override
	public boolean logon() {

		return true;
	}
}
