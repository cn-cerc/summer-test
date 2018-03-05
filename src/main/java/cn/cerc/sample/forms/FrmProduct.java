package cn.cerc.sample.forms;

import java.util.ArrayList;
import java.util.List;

import cn.cerc.jbean.client.LocalService;
import cn.cerc.jbean.form.IPage;
import cn.cerc.jdb.core.DataSet;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;
import cn.cerc.sample.entity.Product;

public class FrmProduct extends AbstractForm {

	@Override
	public IPage execute() throws Exception {
		JspPage jspPage = new JspPage(this, "parts/ProductList.jsp");// common/FrmProduct.jsp
		return jspPage;
	}

	public IPage getDate() {
		JspPage page = new JspPage(this, "parts/ProductList.jsp");
		LocalService srv = new LocalService(this);
		srv.setService("SrvProductInfo.getData");
		DataSet dataOut = srv.getDataOut();
		if (!srv.exec()) {
			page.setMessage(srv.getMessage());
			return page;
		}
		List<Product> list = new ArrayList<>();
		while (dataOut.fetch()) {
			Product product = new Product();
			product.setCode(dataOut.getString("Code_"));
			product.setCorpNo(dataOut.getString("CorpNo_"));
			product.setSpec(dataOut.getString("Spec_"));
			product.setUnit(dataOut.getString("Unit_"));
			product.setStock(dataOut.getString("Stock_"));
			list.add(product);
		}
		page.add("item", list);
		return page;
	}

	@Override
	public boolean logon() {
		return true;
	}

}
