package cn.cerc.sample.forms;

import cn.cerc.jbean.form.IPage;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;

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
        JspPage jspPage = new JspPage(this);
        jspPage.setJspFile("parts/FrmScanProduct.jsp");
        jspPage.add("message", String.format("保存条码: %s, 数量：%s", barcode, num));
        return jspPage;
    }

    @Override
    public boolean logon() {
        return true;
    }
}
