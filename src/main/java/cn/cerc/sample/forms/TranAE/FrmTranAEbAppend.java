package cn.cerc.sample.forms.TranAE;

import java.util.HashMap;
import java.util.Map;

import cn.cerc.jbean.client.LocalService;
import cn.cerc.jbean.form.IPage;
import cn.cerc.jdb.core.Record;
import cn.cerc.jdb.mysql.SqlQuery;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JsonPage;
import cn.cerc.jmis.page.JspPage;
import cn.cerc.jmis.page.RedirectPage;
import cn.cerc.sample.common.BaseConfig;

public class FrmTranAEbAppend extends AbstractForm {
    @Override
    public IPage execute() throws Exception {
        JspPage jspPage = new JspPage(this, "TranAE/FrmTranAEbAppend.jsp");
        String tbno = this.getRequest().getParameter("tbno");
        jspPage.add("tbno", tbno);
        return jspPage;
    }

    public IPage TranBPost() {
        JspPage jspPage = new JspPage(this, "TranAE/FrmTranAEbAppend.jsp");

        String submit = this.getRequest().getParameter("submit");
        if (submit != null) {
            String tbno = this.getRequest().getParameter("tbno");
            String code = this.getRequest().getParameter("Code");
            String num = this.getRequest().getParameter("Num");
            LocalService svr = new LocalService(this);
            svr.setService("SvrTranInfo.TranBappend");
            Record headIn = svr.getDataIn().getHead();
            headIn.setField("TBNo_", tbno);
            headIn.setField("Code_", code);
            headIn.setField("Num_", num);
            if (!svr.exec()) {
                jspPage.add("msg", svr.getMessage());
                return jspPage;
            }
            return new RedirectPage(this, "FrmTranAEbInfo?tbno=" + tbno);

        }
        return jspPage;

    }

    public IPage getStock() {
        String code = this.getRequest().getParameter("Code");
        Double stock = 0.0;
        if (!code.equals("")) {
            SqlQuery ds = new SqlQuery(this);
            ds.add("SELECT Stock_ FROM %s ", BaseConfig.product);
            ds.add("where CorpNo_='%s' and Code_='%s' ", BaseConfig.CorpNo, code);
            ds.open();
            stock = ds.getDouble("Stock_");
        }

        Map<String, Double> map = new HashMap<>();
        map.put("stock", stock);
        return new JsonPage(this).setData(map);

    }

    @Override
    public boolean logon() {
        return true;
    }
}
