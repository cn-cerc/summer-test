package cn.cerc.sample.forms;

import cn.cerc.jbean.form.IPage;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JsonPage;
import cn.cerc.jmis.page.JspPage;
import cn.cerc.jmis.page.ResultMessage;

public class FrmScanProduct extends AbstractForm {

    @Override
    public IPage execute() throws Exception {
        JspPage jspPage = new JspPage(this);
        jspPage.setJspFile("parts/FrmScanProduct.jsp");
        jspPage.add("message", "请进行扫描！");
        return jspPage;
    }

    public IPage view() throws Exception {
        String barcode = this.getRequest().getParameter("barcode");
        JspPage jspPage = new JspPage(this);
        jspPage.setJspFile("parts/FrmScanProduct.jsp");
        jspPage.add("message", "显示记录: " + barcode);
        return jspPage;
    }

    public IPage save() throws Exception {
        String barcode = this.getRequest().getParameter("barcode");
        String num = this.getRequest().getParameter("num");
        ResultMessage rm = new ResultMessage(true, "FrmScanProudct ok");
        System.out.println(String.format("FrmScanProudct ok, barcode: %s, num: %s", barcode, num));
        return new JsonPage(this).setData(rm);
    }

    @Override
    public boolean logon() {
        return true;
    }
}
