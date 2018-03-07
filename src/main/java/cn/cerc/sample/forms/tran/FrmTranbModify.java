package cn.cerc.sample.forms.tran;

import cn.cerc.jbean.client.LocalService;
import cn.cerc.jbean.form.IPage;
import cn.cerc.jdb.core.DataSet;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;
import cn.cerc.sample.pojo.TranB;

public class FrmTranbModify extends AbstractForm {
	@Override
	public IPage execute() throws Exception {
		JspPage jspPage = new JspPage(this, "tran/FrmTranbModify.jsp");
		LocalService svr = new LocalService(this);
		svr.setService("SvrTran.tranbModify");
		String code = getRequest().getParameter("code");
		svr.getDataIn().getHead().setField("code", code);

		if (!svr.exec()) {
			jspPage.setMessage(svr.getMessage());
			return jspPage;
		}
		DataSet dataOut = svr.getDataOut();
		TranB tranb = new TranB();
		while (dataOut.fetch()) {
			tranb.setTbNo(dataOut.getString("TBNo_"));
			tranb.setIt(dataOut.getInt("It_"));
			tranb.setCode(dataOut.getString("Code_"));
			tranb.setDesc(dataOut.getString("Desc_"));
			tranb.setSpec(dataOut.getString("Spec_"));
			tranb.setUnit(dataOut.getString("Unit_"));
			tranb.setNum(dataOut.getDouble("Num_"));
		}
		jspPage.add("tranb", tranb);
		return jspPage;
	}

	@Override
	public boolean logon() {

		return true;
	}
}
