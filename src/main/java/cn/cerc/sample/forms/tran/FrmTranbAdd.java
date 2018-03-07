package cn.cerc.sample.forms.tran;

import java.util.ArrayList;
import java.util.List;

import cn.cerc.jbean.client.LocalService;
import cn.cerc.jbean.form.IPage;
import cn.cerc.jdb.core.DataSet;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;
import cn.cerc.sample.pojo.Product;

public class FrmTranbAdd extends AbstractForm {

	@Override
	public IPage execute() throws Exception {

		JspPage jspPage = new JspPage(this, "tran/FrmTranbAdd.jsp");

		LocalService svr = new LocalService(this);
		String tbNo = getRequest().getParameter("tbNo");

		svr.setService("SvrProduct.search");

		LocalService service = new LocalService(this);
		service.setService("SvrProduct.search");
		DataSet dataOut = service.getDataOut();
		if (!service.exec()) {
			jspPage.setMessage(service.getMessage());
			return jspPage;
		}
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
		return jspPage;
	}

	@Override
	public boolean logon() {

		return true;
	}
}
