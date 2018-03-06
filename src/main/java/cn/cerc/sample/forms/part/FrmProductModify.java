package cn.cerc.sample.forms.part;

import cn.cerc.jbean.client.LocalService;
import cn.cerc.jbean.form.IPage;
import cn.cerc.jdb.core.DataSet;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;
import cn.cerc.sample.pojo.Product;

public class FrmProductModify extends AbstractForm {

	@Override
	public IPage execute() throws Exception {

		JspPage jspPage = new JspPage(this, "part/FrmProductModify.jsp");
		String code = getRequest().getParameter("code");
		LocalService svr = new LocalService(this);
		svr.setService("SvrProduct.findById");
		svr.getDataIn().getHead().setField("code", code);
		if (!svr.exec()) {
			jspPage.setMessage(svr.getMessage());
			return jspPage;
		}
		DataSet dataOut = svr.getDataOut();
	    Product product = new Product();
	    product.setCode(dataOut.getString("Code_"));
		product.setCorpNo(dataOut.getString("CorpNo_"));
		product.setSpec(dataOut.getString("Spec_"));
		product.setUnit(dataOut.getString("Unit_"));
		product.setDesc(dataOut.getString("Desc_"));
		product.setStock(dataOut.getDouble("Stock_"));
		jspPage.add("product", product);
		return jspPage;
		
	}

	@Override
	public boolean logon() {

		return true;
	}
}
