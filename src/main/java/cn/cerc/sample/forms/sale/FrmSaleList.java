package cn.cerc.sample.forms.sale;

import java.util.ArrayList;
import java.util.List;

import cn.cerc.jbean.client.LocalService;
import cn.cerc.jbean.form.IPage;
import cn.cerc.jdb.core.DataSet;
import cn.cerc.jdb.core.Record;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;
import cn.cerc.sample.pojo.SaleB;
import cn.cerc.sample.pojo.SaleH;

public class FrmSaleList extends AbstractForm {

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
		Record head = svr.getDataOut().getHead();
		SaleH saleh = new SaleH();
		saleh.setAppUser(head.getString("AppUser_"));
		saleh.setTbNo(head.getString("TBNo_"));
		saleh.setCusName(head.getString("CusName_"));
		saleh.setTbDate(head.getDate("TBDate_"));
		jspPage.add("saleh", saleh);

		DataSet dataOut = svr.getDataOut();
		List<SaleB> list = new ArrayList<>();
		if (dataOut != null) {
			while (dataOut.fetch()) {
				SaleB saleb = new SaleB();
				saleb.setIt(dataOut.getInt("It_"));
				saleb.setCode(dataOut.getString("Code_"));
				saleb.setDesc(dataOut.getString("Desc_"));
				saleb.setSpec(dataOut.getString("Spec_"));
				saleb.setUnit(dataOut.getString("Unit_"));
				saleb.setNum(dataOut.getDouble("Num_"));
				saleb.setTbNo(dataOut.getString("TBNo_"));
				list.add(saleb);
			}
		}
		jspPage.add("salebs", list);

		return jspPage;
	}

	@Override
	public boolean logon() {

		return true;
	}
}
