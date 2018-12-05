package cn.cerc.sample.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.cerc.db.core.IAppConfig;
import cn.cerc.jbean.client.LocalService;
import cn.cerc.jbean.form.IForm;
import cn.cerc.jbean.other.BufferType;
import cn.cerc.jbean.other.MemoryBuffer;
import cn.cerc.jbean.tools.IAppLoginManage;
import cn.cerc.jdb.core.IHandle;
import cn.cerc.jdb.core.Utils;
import cn.cerc.jmis.core.RequestData;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.AbstractJspPage;

@Component
public class AppLoginManage extends AbstractJspPage implements IAppLoginManage {
    private static final Logger log = Logger.getLogger(AppLoginManage.class);
    @Autowired
    private IAppConfig appConfig;

    public AppLoginManage() {
        super(null);
    }

    public AppLoginManage(IForm form) {
        super(form);
    }

    @Override
    public void init(IForm form) {
        this.setForm(form);
        this.setJspFile(appConfig.getJspLoginFile());
        this.add("homePage", appConfig.getFormWelcome());
        this.add("needVerify", "false");
    }

    @Override
    public String checkToken(String token) throws IOException, ServletException {
        IForm form = this.getForm();
        String password = null;
        String userCode = null;
        try {
            if (form.getRequest().getParameter("login_usr") != null) {
                userCode = getRequest().getParameter("login_usr");
                password = getRequest().getParameter("login_pwd");
                String loginType = getRequest().getParameter("loginType");
                String result = checkLogin(userCode, password, loginType);
                if (result == null) { // 登录成功保存session
                    getRequest().getSession().setAttribute("userCode", userCode);
                }
                return result;
            }
            log.debug(String.format("根据 token(%s) 创建 Session", token));
            IHandle sess = (IHandle) form.getHandle().getProperty(null);
            if (sess.init(token))
                return null;
            if (form.logon())
                return null;
        } catch (Exception e) {
            if (!e.getMessage().contains("</a>")) {
                if (password == null || "".equals(password)) {
                    return "redirect:TFrmEasyReg?phone=" + userCode;
                }
            }
            this.add("loginMsg", e.getMessage());
        }
        return this.execute();
    }

    public String checkLogin(String userCode, String password, String loginType) throws ServletException, IOException {
        IForm form = this.getForm();
        HttpServletRequest req = this.getRequest();

        log.debug(String.format("校验用户帐号(%s)与密码", userCode));

        // 进行设备首次登记
        String deviceId = form.getClient().getId();
        req.setAttribute("userCode", userCode);
        req.setAttribute("password", password);
        req.setAttribute("needVerify", "false");

        log.debug(String.format("进行用户帐号(%s)与密码认证", userCode));
        // 进行用户名、密码认证
        LocalService app;
        if (form instanceof AbstractForm)
            app = new LocalService((AbstractForm) form);
        else
            app = new LocalService(form.getHandle());
        app.setService("SvrUserLogin.check");
        if (app.exec("Account_", userCode, "Password_", password, "MachineID_", deviceId, "loginType", loginType)) {
            String sid = app.getDataOut().getHead().getString("SessionID_");
            if (sid != null && !sid.equals("")) {
                log.debug(String.format("认证成功，取得sid(%s)", sid));
                req.setAttribute(RequestData.appSession_Key, sid);
                req.getSession().setAttribute(RequestData.appSession_Key, sid);
            }
        } else {
            // 登录验证失败，进行判断，手机号为空，则回到登录页，手机不为空，密码为空，则跳到发送验证码页面
            if ("needfirstLogin".equals(app.getMessage())) {
                try (MemoryBuffer buffer = new MemoryBuffer(BufferType.getGrid, "userName")) {
                    buffer.setField("userName", userCode);
                }
                return "redirect:FrmFirstLogin";
            }
            String mobile = Utils.safeString(app.getDataOut().getHead().getString("Mobile_"));
            if (mobile == null || "".equals(mobile)) {
                log.debug(String.format("用户帐号(%s)与密码认证失败", userCode));
                req.setAttribute("loginMsg", app.getMessage());
                this.execute();
            } else if (password == null || "".equals(password)) {
                return "redirect:TFrmEasyReg?phone=" + mobile;
            } else {
                log.debug(String.format("用户帐号(%s)与密码认证失败", userCode));
                req.setAttribute("loginMsg", app.getMessage());
                return this.execute();
            }
        }
        return null;
    }

    @Override
    public String checkLogin(String userCode, String password) throws IOException, ServletException {
        return null;
    }

}
