package cn.cerc.sample.forms;

import cn.cerc.jbean.form.IPage;
import cn.cerc.jbean.sapi.SAPIMessage;
import cn.cerc.jbean.sapi.SAPISecurity;
import cn.cerc.jbean.sapi.SAPIServer;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JsonPage;
import cn.cerc.jmis.page.JspPage;

public class FrmSAPIDemo extends AbstractForm {

    @Override
    public IPage execute() {
        JspPage page = new JspPage(this, "common/FrmSAPIDemo.jsp");
        page.add("appKey", "ytgj");
        page.add("appSecret", "123456");
        page.add("mobile", "13828832477");
        page.add("user", "testUser");
        page.add("deviceId", "abcd");
        return page;
    }

    // 注册服务器，否则后续均无法操作！
    public IPage serverRegister() {
        String appKey = this.getRequest().getParameter("appKey");
        String appSecret = this.getRequest().getParameter("appSecret");
        JsonPage page = new JsonPage(this);
        SAPIServer sapi = new SAPIServer(this.getRequest());
        page.setResultMessage(sapi.register(appKey, appSecret), sapi.getMessage());
        return page;
    }

    // 新用户注册时，发送验证码
    public IPage requestRegister() {
        String mobile = this.getRequest().getParameter("mobile");
        JsonPage page = new JsonPage(this);
        SAPIMessage sapi = new SAPIMessage(this.getRequest());
        page.setResultMessage(sapi.requestRegister(mobile), sapi.getMessage());
        return page;
    }

    // 检查新用户收到的验证码
    public IPage checkRegister() {
        String mobile = this.getRequest().getParameter("mobile");
        String verifyCode = this.getRequest().getParameter("verifyCode");
        JsonPage page = new JsonPage(this);
        SAPIMessage sapi = new SAPIMessage(this.getRequest());
        page.setResultMessage(sapi.checkRegister(mobile, verifyCode), sapi.getMessage());
        return page;
    }

    // 注册用户的密保手机
    public IPage userRegister() {
        String user = this.getRequest().getParameter("user");
        String mobile = this.getRequest().getParameter("mobile");
        JsonPage page = new JsonPage(this);
        SAPISecurity sapi = new SAPISecurity(this.getRequest());
        page.setResultMessage(sapi.register(user, mobile), sapi.getMessage());
        return page;
    }

    // 检查环境是否安全
    public IPage isSecurity() {
        String user = this.getRequest().getParameter("user");
        String deviceId = this.getRequest().getParameter("deviceId");
        this.getRequest().getSession().setAttribute("deviceId", deviceId);
        JsonPage page = new JsonPage(this);
        SAPISecurity sapi = new SAPISecurity(this.getRequest());
        page.setResultMessage(sapi.isSecurity(user), sapi.getMessage());
        return page;
    }

    // 在检查到环境不安全时，请求发送验证码
    public IPage requestVerify() {
        String user = this.getRequest().getParameter("user");
        String deviceId = this.getRequest().getParameter("deviceId");
        this.getRequest().getSession().setAttribute("deviceId", deviceId);
        JsonPage page = new JsonPage(this);
        SAPISecurity sapi = new SAPISecurity(this.getRequest());
        page.setResultMessage(sapi.requestVerify(user), sapi.getMessage());
        return page;
    }

    // 检查收到的验证码是否正确
    public IPage checkVerify() {
        String user = this.getRequest().getParameter("user");
        String verifyCode = this.getRequest().getParameter("verifyCode");
        String deviceId = this.getRequest().getParameter("deviceId");
        this.getRequest().getSession().setAttribute("deviceId", deviceId);
        JsonPage page = new JsonPage(this);
        SAPISecurity sapi = new SAPISecurity(this.getRequest());
        page.setResultMessage(sapi.checkVerify(user, verifyCode), sapi.getMessage());
        return page;
    }

    // 检查当前环境是否安全（整合使用）
    public IPage check() {
        String user = this.getRequest().getParameter("user");
        String deviceId = this.getRequest().getParameter("deviceId");
        SAPISecurity sapi = new SAPISecurity(this.getRequest());
        if (sapi.checkEnvironment(user)) {
            JsonPage page = new JsonPage(this);
            return page.setResultMessage(true, "当前环境安全");
        } else {
            JspPage jspPage = new JspPage(this);
            jspPage.add("user", user);
            jspPage.add("deviceId", deviceId);
            jspPage.add("message", sapi.getMessage());
            jspPage.setJspFile("common/FrmSAPIDemo_check.jsp");
            return jspPage;
        }
    }

    @Override
    public boolean logon() {
        return true;
    }
}
