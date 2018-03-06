package cn.cerc.sample.forms.part;

import cn.cerc.jbean.form.IPage;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;

public class FrmProductAdd extends AbstractForm {

	@Override
	public IPage execute() throws Exception {
		
		JspPage page = new JspPage(this,"part/FrmProductAdd.jsp");
		
		return page;
	}

	@Override
	public boolean logon() {
		
		return true;
	}
}
