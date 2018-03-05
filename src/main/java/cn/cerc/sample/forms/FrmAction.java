package cn.cerc.sample.forms;

import java.util.ArrayList;
import java.util.List;

import cn.cerc.jbean.client.LocalService;
import cn.cerc.jbean.form.IPage;
import cn.cerc.jdb.core.DataSet;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;
import cn.cerc.sample.entity.Product;

public class FrmAction extends AbstractForm{

	@Override
	public IPage execute() throws Exception {
		JspPage page = new JspPage(this,"part/FromShow.jsp");
		LocalService service = new LocalService(this);
		service.setService("ProductService.search");
		DataSet dataOut = service.getDataOut();
		if(!service.exec()){
			page.setMessage(service.getMessage());
			return page;
		}
		List<Product> list = new ArrayList<>();
		while(dataOut.fetch()){
			Product product = new Product();
			product.setCode(dataOut.getString("Code_"));
			product.setSpec(dataOut.getString("Spec_"));
			product.setUnit(dataOut.getString("Unit_"));
			product.setDesc(dataOut.getString("Desc_"));
			product.setStock(dataOut.getDouble("Stock_"));
			list.add(product);
		}
		return null;
	}
	
}
