package cn.cerc.sample.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import cn.cerc.jbean.form.IPage;
import cn.cerc.jbean.other.SystemTable;
import cn.cerc.jdb.mysql.SqlQuery;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JsonPage;

public class FrmErpRegister extends AbstractForm {
    private static final Logger log = Logger.getLogger(FrmErpRegister.class);
    private static final String HWA_AppProtect = "HWA_AppProtect";

    @Override
    public IPage execute() throws Exception {
        JsonPage page = new JsonPage(this);
        HttpServletRequest req = this.getRequest();
        // 取地藤帐号
        String account = req.getParameter("account");
        if (account == null || "".equals(account)) {
            page.put("error", "用户帐号不允许为空!");
            return page;
        }
        // 取地藤密码
        String password = req.getParameter("password");
        if (password == null || "".equals(password)) {
            page.put("error", "登录密码不允许为空!");
            return page;
        }
        // 取认证码
        String appkey = req.getParameter("appkey");
        if (appkey == null || "".equals(appkey)) {
            page.put("error", "传入非法的主机认证码!");
            return page;
        }

        // if (true) {
        // page.put("regNum", 100); // 用户数
        // page.put("regCycle", 7); // 多少天重取一次
        // page.put("nextTime", "03:00");
        // page.put("disableDate", ""); // 失效时间
        // page.put("menu", ""); // 此项暂未使用
        // return page;
        // }

        try {
            String corpNo = getCorpNo(account, password);
            if (corpNo == null || "".equals(corpNo)) {
                page.put("error", "帐号或密码错误，登录失败!");
                return page;
            }
            SqlQuery ds = new SqlQuery(this);
            ds.add("select RegNum_,RegCycle_,NextUpdate_,DisableDate_,UpdateKey_ from %s", HWA_AppProtect);
            ds.add("where CorpCode_='%s' and AppKey_='%s' and Enabled_=1 ", corpNo, appkey);
            ds.add("and (DisableDate_>curdate() or DisableDate_ is null)");
            ds.open();
            if (ds.eof()) {
                page.put("error", "当前主机没有注册或服务已过期!");
                return page;
            }
            page.put("regNum", ds.getInt("RegNum_"));
            page.put("regCycle", ds.getInt("RegCycle_"));
            page.put("nextTime", ds.getString("NextUpdate_"));
            page.put("disableDate", ds.getString("DisableDate_"));
            page.put("menu", ""); // 此项暂未使用

            // 更新时间
            String sql = "update %s set LastRegDate_=curdate() where UpdateKey_='%s'";
            this.getConnection().execute(String.format(sql, HWA_AppProtect, ds.getString("UpdateKey_")));
        } catch (Exception e) {
            page.put("error", e.getMessage());
            log.error(e.getMessage());
        }
        return page;
    }

    // FIXME: 此处需要继续完成
    private String getCorpNo(String account, String password) {
        SqlQuery ds = new SqlQuery(this);
        ds.add("select * from %s", SystemTable.getUserInfo);
        ds.add("where Code_='%s'", account);
        ds.open();
        if (ds.eof())
            return null;
        return ds.getString("CorpNo_");
    }

    @Override
    public boolean logon() {
        return true;
    }
}
