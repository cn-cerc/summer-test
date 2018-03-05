package cn.cerc.sample.forms;

import java.util.ArrayList;
import java.util.List;

import cn.cerc.jbean.client.LocalService;
import cn.cerc.jbean.form.IPage;
import cn.cerc.jdb.core.DataSet;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;
import cn.cerc.sample.model.Product;

public class FrmPartInfoShow extends AbstractForm {

	@Override
	public IPage execute() throws Exception {
		JspPage jspPage =new JspPage(this, "parts/FrmPartInfoShow.jsp");
		LocalService service=new LocalService(this);
		service.setService("ServicePartInfo.search");
		DataSet dataSet=service.getDataOut();
		if(!service.exec()) {
			jspPage.setMessage(service.getMessage());
			return jspPage;
		}
		List<Product> list=new ArrayList<>();
		while(dataSet.fetch()) {
			Product product=new Product();
			product.setCode(dataSet.getString("Code_"));
			product.setCorpNo(dataSet.getString("CorpNo_"));
			product.setCode(dataSet.getString("Spec_"));
			product.setCode(dataSet.getString("Unit_"));
			product.setCode(dataSet.getString("Stock_"));
			list.add(product);
			
		}
		jspPage.add("item", list);
		
		return jspPage;
	}
	
	@Override
	public boolean logon() {
		
		return true;
	}

}
