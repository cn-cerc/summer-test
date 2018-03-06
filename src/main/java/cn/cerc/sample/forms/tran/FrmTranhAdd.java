package cn.cerc.sample.forms.tran;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.cerc.jbean.form.IPage;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;

public class FrmTranhAdd extends AbstractForm {

	@Override
	public IPage execute() throws Exception {
		JspPage jspPage = new JspPage(this, "tran/FrmTranhAdd.jsp");

		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String format = simple.format(date);
		jspPage.add("date", format);
		return jspPage;
	}

	@Override
	public boolean logon() {

		return true;
	}
}
