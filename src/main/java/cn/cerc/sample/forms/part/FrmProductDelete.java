package cn.cerc.sample.forms.part;

import cn.cerc.jbean.client.LocalService;
import cn.cerc.jbean.form.IPage;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;
import cn.cerc.jmis.page.RedirectPage;

public class FrmProductDelete extends AbstractForm {

	@Override
	public IPage execute() throws Exception {
         JspPage jspPage = new JspPage(this,"part/FrmShow.jsp");
         LocalService svr = new LocalService(this);
         svr.setService("SvrProduct.delete");
         svr.getDataIn().getHead().setField("code", getRequest().getParameter("code"));
         if (!svr.exec()) {
 			jspPage.setMessage(svr.getMessage());
 			return jspPage;
 		}
         return new RedirectPage(this,"FrmProduct");
	}

	@Override
	public boolean logon() {
		
		return true;
	}
}
