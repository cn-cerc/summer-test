package cn.cerc.sample.forms;

import cn.cerc.jbean.form.IPage;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JsonPage;
import cn.cerc.jmis.page.JspPage;

public class FrmClientTest extends AbstractForm {

    @Override
    public IPage execute() throws Exception {
        return new JspPage(this, "common/FrmClientTest.jsp");
    }

    //拨打客户电话页面
    public IPage callCustomer() {
        String list = "1,2,3,4";
        JspPage jspPage = new JspPage(this, "parts/FrmCallCustomer.jsp");
        jspPage.add("phoneNumber", "15191048958");
        jspPage.add("customerName", "css");
        jspPage.add("userName", "lucy");
        jspPage.add("list", list.split(","));
        return jspPage;
    }
    
    //获取扫描二维码结果并返回跳转页面
    public IPage zxingTest() {
        JsonPage jsonPage = new JsonPage(this);
        String result = this.getRequest().getParameter("resultString");
        System.out.println(result);
        ResultBean resultBean = new ResultBean();
        resultBean.setResult(true);
        resultBean.setMessage("操作成功");
        resultBean.add("forms", "FrmClientTest.callCustomer");
        jsonPage.setData(resultBean);
        return jsonPage;
    }
    

    @Override
    public boolean logon() {
        return true;
    }
}
