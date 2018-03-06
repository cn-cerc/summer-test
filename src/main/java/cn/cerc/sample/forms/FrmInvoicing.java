package cn.cerc.sample.forms;

import cn.cerc.jbean.form.IPage;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;

public class FrmInvoicing extends AbstractForm{

	@Override
	public IPage execute() throws Exception {
		
		JspPage page = new JspPage(this,"parts/FrmInvoicing.jsp");
		
		return page;
	}
	
	
	@Override
	public boolean logon() {
		
		return true;
	}

}
