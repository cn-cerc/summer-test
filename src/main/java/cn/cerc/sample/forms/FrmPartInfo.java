package cn.cerc.sample.forms;

import cn.cerc.jbean.form.IPage;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;

public class FrmPartInfo extends AbstractForm {

	@Override
	public IPage execute() throws Exception {
		JspPage jspPage=new JspPage(this, "parts/FrmPartInfo.jsp");
		return jspPage;
	}
	@Override
	public boolean logon() {
		return true;
	}

}
