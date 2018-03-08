package cn.cerc.sample.forms.tran;

import java.util.ArrayList;
import java.util.List;

import cn.cerc.jbean.client.LocalService;
import cn.cerc.jbean.form.IPage;
import cn.cerc.jdb.core.DataSet;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;
import cn.cerc.sample.pojo.TranH;

public class FrmTranhList extends AbstractForm {

	@Override
	public IPage execute() throws Exception {
		JspPage jspPage = new JspPage(this, "tran/FrmTranhList.jsp");
		LocalService svr = new LocalService(this);
		svr.setService("SvrTran.search");
		DataSet dataOut = svr.getDataOut();
		if (!svr.exec()) {
			jspPage.setMessage(svr.getMessage());
			return jspPage;
		}
		List<TranH> list = new ArrayList<>();
		while (dataOut.fetch()) {
			TranH tranh = new TranH();
			tranh.setTb(dataOut.getString("TB_"));
			tranh.setAppDate(dataOut.getDateTime("AppDate_"));
			tranh.setAppUser(dataOut.getString("AppUser_"));
			tranh.setTbNo(dataOut.getString("TBNo_"));
			tranh.setTb(dataOut.getString("TB_"));
			tranh.setSupName(dataOut.getString("SupName_"));
			tranh.setTbDate(dataOut.getDate("TBDate_"));
			list.add(tranh);
		}
		jspPage.add("tranhs", list);
		return jspPage;
	}

	@Override
	public boolean logon() {

		return true;
	}
}
