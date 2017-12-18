package cn.cerc.sample.forms;

import cn.cerc.jbean.form.IPage;
import cn.cerc.jdb.core.DataSet;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;
import cn.cerc.jpage.fields.AbstractField;
import cn.cerc.jpage.fields.DoubleField;
import cn.cerc.jpage.fields.ItField;
import cn.cerc.jpage.fields.OperaField;
import cn.cerc.jpage.fields.StringField;
import cn.cerc.jpage.grid.AbstractGrid;
import cn.cerc.jpage.grid.DataGrid;
import cn.cerc.jpage.other.OperaPages;

public class FrmDataGrid extends AbstractForm {

    @Override
    public IPage execute() {
        JspPage jspPage = new JspPage(this, "common/TFrmDataGrid.jsp");
        DataSet dataSet = new DataSet();
        for (int i = 1; i < 150; i++)
            dataSet.append().setField("Name_", "王五").setField("Age_", i).setField("Sex_", i % 2 == 0 ? "男" : "女");

        AbstractGrid grid = new DataGrid(this, jspPage);

        grid.setDataSet(dataSet);
        new ItField(grid);
        new StringField(grid, "姓名", "Name_", 8);
        AbstractField col2 = new StringField(grid, "性别", "Sex_", 3);
        col2.createText((ds, html) -> {
            if ("男".equals(ds.getString("Sex_")))
                html.print("<img src='#'/>%s", ds.getString("Sex_"));
            else
                html.print(ds.getString("Sex_"));
        });
        col2.createUrl((ds, url) -> {
            url.setSite("TFrmDataGrid");
            url.addParam("name", ds.getString("Name_"));
        });
        new DoubleField(grid, "年龄", "Age_", 3);

        OperaField opera = new OperaField(grid);
        opera.setShortName("");
        opera.setWidth(3);
        opera.createUrl((ds, url) -> {
            url.setSite("TFrmDataGrid");
            url.addParam("age", ds.getString("Age_"));
        });
        OperaField opera1 = new OperaField(grid);
        opera1.setShortName("");
        opera1.setWidth(3);
        opera1.setName("查看");
        opera1.setValue("资料");
        opera1.createUrl((ds, url) -> {
            url.setSite("TFrmDataGrid");
            url.addParam("age", ds.getString("Age_"));
        });
        grid.getPages().setPageSize(20);
        jspPage.add("dataGrid", grid);
        this.getRequest().setAttribute("page", new OperaPages(grid, this, grid.getPages()));
        return jspPage;
    }

    @Override
    public boolean logon() {
        return true;
    }
}
