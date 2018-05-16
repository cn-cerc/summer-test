package cn.cerc.sample.forms;

import cn.cerc.jbean.form.IPage;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JsonPage;
import cn.cerc.jmis.page.JspPage;
import cn.cerc.security.sapi.JayunMessage;
import cn.cerc.security.sapi.JayunSecurity;
import cn.cerc.security.sapi.JayunServer;

public class FrmSAPIDemo extends AbstractForm {

    @Override
    public IPage execute() {
        JspPage page = new JspPage(this, "common/FrmSAPIDemo.jsp");
        page.add("appKey", "test");
        page.add("appSecret", "123456");
        page.add("mobile", "18566767108");
        page.add("user", "testUser");
        page.add("deviceId", "abcd");
        return page;
    }

    // 注册服务器，否则后续均无法操作！
    public IPage serverRegister() {
        String appKey = this.getRequest().getParameter("appKey");
        String appSecret = this.getRequest().getParameter("appSecret");
        JsonPage page = new JsonPage(this);
        JayunServer Jayun = new JayunServer(this.getRequest());
        page.setResultMessage(Jayun.register(appKey, appSecret), Jayun.getMessage());
        return page;
    }

    // 新用户注册时，发送验证码
    public IPage requestRegister() {
        String mobile = this.getRequest().getParameter("mobile");
        JsonPage page = new JsonPage(this);
        JayunMessage Jayun = new JayunMessage(this.getRequest());
        page.setResultMessage(Jayun.requestRegister(mobile), Jayun.getMessage());
        return page;
    }

    // 检查新用户收到的验证码
    public IPage checkRegister() {
        String mobile = this.getRequest().getParameter("mobile");
        String verifyCode = this.getRequest().getParameter("verifyCode");
        JsonPage page = new JsonPage(this);
        JayunMessage Jayun = new JayunMessage(this.getRequest());
        page.setResultMessage(Jayun.checkRegister(mobile, verifyCode), Jayun.getMessage());
        return page;
    }

    // 注册用户的密保手机
    public IPage userRegister() {
        String user = this.getRequest().getParameter("user");
        String mobile = this.getRequest().getParameter("mobile");
        JsonPage page = new JsonPage(this);
        JayunSecurity Jayun = new JayunSecurity(this.getRequest());
        page.setResultMessage(Jayun.register(user, mobile), Jayun.getMessage());
        return page;
    }

    // 检查环境是否安全
    public IPage isSecurity() {
        String user = this.getRequest().getParameter("user");
        String deviceId = this.getRequest().getParameter("deviceId");
        this.getRequest().getSession().setAttribute("deviceId", deviceId);
        JsonPage page = new JsonPage(this);
        JayunSecurity Jayun = new JayunSecurity(this.getRequest());
        page.setResultMessage(Jayun.isSecurity(user), Jayun.getMessage());
        return page;
    }

    // 在检查到环境不安全时，请求发送验证码
    public IPage requestVerify() {
        String user = this.getRequest().getParameter("user");
        String deviceId = this.getRequest().getParameter("deviceId");
        this.getRequest().getSession().setAttribute("deviceId", deviceId);
        JsonPage page = new JsonPage(this);
        JayunSecurity Jayun = new JayunSecurity(this.getRequest());
        page.setResultMessage(Jayun.requestVerify(user), Jayun.getMessage());
        return page;
    }

    // 检查收到的验证码是否正确
    public IPage checkVerify() {
        String user = this.getRequest().getParameter("user");
        String verifyCode = this.getRequest().getParameter("verifyCode");
        String deviceId = this.getRequest().getParameter("deviceId");
        this.getRequest().getSession().setAttribute("deviceId", deviceId);
        JsonPage page = new JsonPage(this);
        JayunSecurity Jayun = new JayunSecurity(this.getRequest());
        page.setResultMessage(Jayun.checkVerify(user, verifyCode), Jayun.getMessage());
        return page;
    }

    // 检查当前环境是否安全（整合使用）
    public IPage check() {
        String user = this.getRequest().getParameter("user");
        String deviceId = this.getRequest().getParameter("deviceId");
        JayunSecurity api = new JayunSecurity(this.getRequest());
        if (api.checkEnvironment(user)) {
            JsonPage page = new JsonPage(this);
            return page.setResultMessage(true, "当前环境安全");
        } else {
            JspPage jspPage = new JspPage(this);
            jspPage.add("user", user);
            jspPage.add("deviceId", deviceId);
            jspPage.add("message", api.getMessage());
            jspPage.setJspFile("common/FrmSAPIDemo_check.jsp");
            return jspPage;
        }
    }

    @Override
    public boolean logon() {
        return true;
    }
}
