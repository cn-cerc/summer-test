package cn.cerc.sample.forms.tran;

import java.util.ArrayList;
import java.util.List;

import cn.cerc.jbean.client.LocalService;
import cn.cerc.jbean.form.IPage;
import cn.cerc.jdb.core.DataSet;
import cn.cerc.jdb.core.Record;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;
import cn.cerc.sample.pojo.TranB;
import cn.cerc.sample.pojo.TranH;

public class FrmTran extends AbstractForm {

	@Override
	public IPage execute() throws Exception {
		JspPage jspPage = new JspPage(this, "tran/FrmTran.jsp");

		String tbNo = getRequest().getParameter("tbNo");
		LocalService svr = new LocalService(this);
		svr.setService("SvrTran.tranhFindByTbNo");
		svr.getDataIn().getHead().setField("tbNo", tbNo);
		if (!svr.exec()) {
			jspPage.setMessage(svr.getMessage());
			return jspPage;
		}

		Record head = svr.getDataOut().getHead();
		TranH tranh = new TranH();
		tranh.setAppUser(head.getString("AppUser_"));
		tranh.setTbNo(head.getString("TBNo_"));
		tranh.setSupName(head.getString("SupName_"));
		tranh.setTbDate(head.getDate("TBDate_"));
		jspPage.add("tranh", tranh);

		DataSet dataOut = svr.getDataOut();
		List<TranB> list = new ArrayList<>();
		if (dataOut != null) {
			while (dataOut.fetch()) {
				TranB tranb = new TranB();
				tranb.setIt(dataOut.getInt("It_"));
				tranb.setCode(dataOut.getString("Code_"));
				tranb.setDesc(dataOut.getString("Desc_"));
				tranb.setSpec(dataOut.getString("Spec_"));
				tranb.setUnit(dataOut.getString("Unit_"));
				tranb.setNum(dataOut.getDouble("Num_"));
				tranb.setTbNo(dataOut.getString("TBNo_"));
				list.add(tranb);
			}
		}
		jspPage.add("tranbs", list);
		return jspPage;
	}

	@Override
	public boolean logon() {

		return true;
	}
}
