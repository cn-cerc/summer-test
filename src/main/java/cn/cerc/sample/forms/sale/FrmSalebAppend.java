package cn.cerc.sample.forms.sale;

import java.util.ArrayList;
import java.util.List;

import cn.cerc.jbean.client.LocalService;
import cn.cerc.jbean.form.IPage;
import cn.cerc.jdb.core.DataSet;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;
import cn.cerc.jmis.page.RedirectPage;
import cn.cerc.sample.pojo.Product;

public class FrmSalebAppend extends AbstractForm {

	@Override
	public IPage execute() throws Exception {
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
			DataSet dataOut = svr2.getDataOut();
			List<Product> list = new ArrayList<>();
			while (dataOut.fetch()) {
				Product product = new Product();
				product.setCode(dataOut.getString("Code_"));
				product.setCorpNo(dataOut.getString("CorpNo_"));
				product.setSpec(dataOut.getString("Spec_"));
				product.setUnit(dataOut.getString("Unit_"));
				product.setDesc(dataOut.getString("Desc_"));
				product.setStock(dataOut.getDouble("Stock_"));
				list.add(product);
			}
			jspPage.add("tbNo", tbNo);
			jspPage.add("items", list);
			jspPage.add("message", svr.getMessage());
			return jspPage;
		}

		return new RedirectPage(this, String.format("FrmSaleList?tbNo=%s&code=%s", tbNo, code));

	}

	@Override
	public boolean logon() {

		return true;
	}
}
