package cn.cerc.sample.forms;

import cn.cerc.jbean.form.IPage;
import cn.cerc.jdb.core.DataSet;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;
import cn.cerc.jpage.fields.StringField;
import cn.cerc.jpage.grid.DataGrid;

public class TFrmDataGrid extends AbstractForm {

	@Override
	public IPage execute() {
		JspPage jp = new JspPage(this, "common/TFrmDataGrid.jsp");
		DataSet ds = new DataSet();
		ds.append().setField("Name_", "李四").setField("Age_", 18);
		ds.append().setField("Name_", "王五").setField("Age_", 20);
		DataGrid dg = new DataGrid(this,null);
		dg.setDataSet(ds);
		new StringField(dg,"姓名","Name_",8);
		new StringField(dg,"年龄","Age_",8);
		jp.add("dg", dg);
		return jp;
	}

	@Override
	public boolean logon() {
		return true;
	}
}
