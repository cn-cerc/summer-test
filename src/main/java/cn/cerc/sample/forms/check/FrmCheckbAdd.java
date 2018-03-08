package cn.cerc.sample.forms.check;

import cn.cerc.jbean.client.LocalService;
import cn.cerc.jbean.form.IPage;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;

public class FrmCheckbAdd extends AbstractForm {

	@Override
	public IPage execute() throws Exception {

		JspPage jspPage = new JspPage(this, "check/FrmCheckbAdd.jsp");
		String tbNo = getRequest().getParameter("tbNo");

		LocalService svr = new LocalService(this);
		svr.setService("SvrProduct.search");
		if (!svr.exec()) {
			jspPage.setMessage(svr.getMessage());
			return jspPage;
		}

		/*
		 * List<Product> list = new ArrayList<>(); while (dataOut.fetch()) { Product
		 * product = new Product(); product.setCode(dataOut.getString("Code_"));
		 * product.setCorpNo(dataOut.getString("CorpNo_"));
		 * product.setSpec(dataOut.getString("Spec_"));
		 * product.setUnit(dataOut.getString("Unit_"));
		 * product.setDesc(dataOut.getString("Desc_"));
		 * product.setStock(dataOut.getDouble("Stock_")); list.add(product); }
		 */
		jspPage.add("tbNo", tbNo);
		jspPage.add("items", svr.getDataOut().getRecords());
		return jspPage;
	}

	@Override
	public boolean logon() {
		return true;
	}

}
