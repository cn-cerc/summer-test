package cn.cerc.sample.forms.tranae;

import cn.cerc.jbean.client.LocalService;
import cn.cerc.jbean.form.IPage;
import cn.cerc.jdb.core.DataSet;
import cn.cerc.jdb.core.Record;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;
import cn.cerc.jmis.page.RedirectPage;
import cn.cerc.sample.forms.tran.TranB_Record;

public class FrmTranAEdetail extends AbstractForm {
    @Override
    public IPage execute() {
        JspPage jspPage = new JspPage(this, "tranae/FrmTranAEdetail.jsp");
        String tbno = this.getRequest().getParameter("tbno");
        String it = this.getRequest().getParameter("it");

        LocalService svr = new LocalService(this);
        svr.setService("SvrTranAE.searchDetailTranB");
        Record headIn = svr.getDataIn().getHead();
        headIn.setField("TBNo_", tbno);
        headIn.setField("It_", it);

        if (!svr.exec()) {
            jspPage.setMessage(svr.getMessage());
            return jspPage;
        }
        DataSet ds = svr.getDataOut();
        if (!ds.eof()) {
            TranB_Record item = new TranB_Record();
            item.setTbno(ds.getString("TBNo_"));
            item.setIt(ds.getString("It_"));
            item.setCode(ds.getString("Code_"));
            item.setDesc(ds.getString("Desc_"));
            item.setSpec(ds.getString("Spec_"));
            item.setUnit(ds.getString("Unit_"));
            item.setStock(ds.getDouble("Stock_"));
            item.setNum(ds.getDouble("Num_"));
            jspPage.add("item", item);
        }
        return jspPage;
    }

    public IPage modify() {
        // 新增加商品资料
        JspPage jspPage = new JspPage(this, "tranae/FrmTranAEdetail.jsp");
        String tbno = this.getRequest().getParameter("TBNo");
        String it = this.getRequest().getParameter("It");
        String code = this.getRequest().getParameter("Code");
        String num = this.getRequest().getParameter("Num");

        String delete = this.getRequest().getParameter("delete");
        String save = this.getRequest().getParameter("save");

        if (save != null && save.equals("保存")) {
            LocalService svr = new LocalService(this);
            svr.setService("SvrTranAE.modifyTranB");

            Record headIn = svr.getDataIn().getHead();
            headIn.setField("TBNo_", tbno);
            headIn.setField("It_", it);
            headIn.setField("Code_", code);
            headIn.setField("Num_", num);

            if (!svr.exec()) {
                jspPage.setMessage(svr.getMessage());
                return jspPage;
            }
            return new RedirectPage(this, "FrmTranAEInfo?tbno=" + tbno);
        }
        if (delete != null && delete.equals("删除")) {
            LocalService svr = new LocalService(this);
            svr.setService("SvrTranAE.deleteTranB");

            Record headIn = svr.getDataIn().getHead();
            headIn.setField("TBNo_", tbno);
            headIn.setField("It_", it);
            headIn.setField("Code_", code);
            headIn.setField("Num_", num);

            if (!svr.exec()) {
                jspPage.setMessage(svr.getMessage());
                return jspPage;
            }
            return new RedirectPage(this, "FrmTranAEInfo?tbno=" + tbno);
        }

        return jspPage;
    }

    public IPage delete() {
        JspPage jspPage = new JspPage(this, "tranae/FrmTranAEs.jsp");
        /*
         * LocalService svr = new LocalService(this);
         * svr.setService("SvrTranAE.delete"); String tbno =
         * this.getRequest().getParameter("tbno"); Record headIn =
         * svr.getDataIn().getHead(); headIn.setField("TBNo_", tbno);
         * 
         * if (!svr.exec()) { jspPage.setMessage(svr.getMessage()); return jspPage; } //
         * return jspPage;
         * 
         */
        return new RedirectPage(this, "FrmTranAEs");
    }

    @Override
    public boolean logon() {
        return true;
    }
}
