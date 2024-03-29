package cn.cerc.sample.forms;

import org.springframework.stereotype.Component;

import cn.cerc.core.DataSet;
import cn.cerc.mis.core.AbstractForm;
import cn.cerc.mis.core.IPage;
import cn.cerc.mis.page.JspPage;
import cn.cerc.ui.fields.AbstractField;
import cn.cerc.ui.fields.DoubleField;
import cn.cerc.ui.fields.ItField;
import cn.cerc.ui.fields.OperaField;
import cn.cerc.ui.fields.StringField;
import cn.cerc.ui.grid.AbstractGrid;
import cn.cerc.ui.grid.DataGrid;
import cn.cerc.ui.other.OperaPages;

@Component
public class GridSample extends AbstractForm {

    @Override
    public IPage execute() {
        JspPage jspPage = new JspPage(this, "common/gridSample.jsp");
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
            url.setSite("gridSample");
            url.putParam("name", ds.getString("Name_"));
        });
        new DoubleField(grid, "年龄", "Age_", 3);

        OperaField opera = new OperaField(grid);
        opera.setShortName("");
        opera.setWidth(3);
        opera.createUrl((ds, url) -> {
            url.setSite("gridSample");
            url.putParam("age", ds.getString("Age_"));
        });
        OperaField opera1 = new OperaField(grid);
        opera1.setShortName("");
        opera1.setWidth(3);
        opera1.setName("查看");
        opera1.setValue("资料");
        opera1.createUrl((ds, url) -> {
            url.setSite("gridSample");
            url.putParam("age", ds.getString("Age_"));
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
