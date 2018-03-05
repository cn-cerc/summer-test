package cn.cerc.sample.forms;

import cn.cerc.jbean.form.IPage;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;

public class FrmProduct extends AbstractForm{

	@Override
	public IPage execute() throws Exception {
		
		JspPage page = new JspPage(this,"parts/FrmProduct.jsp");
		
		return page;
	}
	
	
	@Override
	public boolean logon() {
		
		return true;
	}

}
