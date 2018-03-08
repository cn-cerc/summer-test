package cn.cerc.sample.forms.tranab;

import cn.cerc.jbean.client.LocalService;
import cn.cerc.jbean.form.IPage;
import cn.cerc.jdb.core.Record;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;
import cn.cerc.jmis.page.RedirectPage;
import cn.cerc.sample.forms.tran.TranB_Record;

public class FrmTranABb extends AbstractForm {
    @Override
    public IPage execute() {
        // 新增加商品资料
        JspPage jspPage = new JspPage(this, "tranab/FrmTranABb.jsp");
        String tbno = this.getRequest().getParameter("tbno");

        TranB_Record item = new TranB_Record();
        item.setTbno(tbno);
        jspPage.add("item", item);

        return jspPage;
    }

    public IPage appendTranB() {
        // 新增加商品资料
        JspPage jspPage = new JspPage(this, "tranab/FrmTranABb.jsp");
        String tbno = this.getRequest().getParameter("TBNo");
        String it = this.getRequest().getParameter("It");
        String code = this.getRequest().getParameter("Code");
        String num = this.getRequest().getParameter("Num");

        String submit = this.getRequest().getParameter("submit");

        if (submit != null) {
            LocalService svr = new LocalService(this);
            svr.setService("SvrTranAB.appendTranB");

            Record headIn = svr.getDataIn().getHead();
            headIn.setField("TBNo_", tbno);
            headIn.setField("It_", it);
            headIn.setField("Code_", code);
            headIn.setField("Num_", num);

            if (!svr.exec()) {
                jspPage.setMessage(svr.getMessage());
                return jspPage;
            }
            return new RedirectPage(this, "FrmTranABInfo?tbno=" + tbno);
        }

        return jspPage;
    }

    public IPage delete() {
        JspPage jspPage = new JspPage(this, "tranab/FrmTranABb.jsp");
        /*
         * LocalService svr = new LocalService(this);
         * svr.setService("SvrTranAB.delete"); String tbno =
         * this.getRequest().getParameter("tbno"); Record headIn =
         * svr.getDataIn().getHead(); headIn.setField("TBNo_", tbno);
         * 
         * if (!svr.exec()) { jspPage.setMessage(svr.getMessage()); return jspPage; } //
         * return jspPage;
         */
        return new RedirectPage(this, "FrmTranABb");
    }

    @Override
    public boolean logon() {
        return true;
    }
}
