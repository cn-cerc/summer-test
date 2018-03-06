package cn.cerc.sample.forms.tran;

import cn.cerc.jbean.client.LocalService;
import cn.cerc.jbean.form.IPage;
import cn.cerc.jdb.core.DataSet;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;
import cn.cerc.sample.pojo.TranH;

public class FrmTran extends AbstractForm {

	@Override
	public IPage execute() throws Exception {
		JspPage jspPage = new JspPage(this, "tran/Frmtran.jsp");

		String tbNo = getRequest().getParameter("tbNo");
		LocalService svr = new LocalService(this);
		svr.setService("SvrTran.findByTbNo");
		svr.getDataIn().getHead().setField("tbNo", tbNo);
		if (!svr.exec()) {
			jspPage.setMessage(svr.getMessage());
			return jspPage;
		}
		DataSet dataOut = svr.getDataOut();
		TranH tranh = new TranH();
		tranh.setAppUser(dataOut.getString("AppUser_"));
		tranh.setTbNo(dataOut.getString("TBNo_"));
		tranh.setSupName(dataOut.getString("SupName_"));
		tranh.setTbDate(dataOut.getDate("TBDate_"));
		jspPage.add("tranh", tranh);
		return jspPage;
	}

	@Override
	public boolean logon() {

		return true;
	}
}
