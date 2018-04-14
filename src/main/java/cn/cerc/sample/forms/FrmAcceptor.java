package cn.cerc.sample.forms;

import cn.cerc.jbean.form.IPage;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JsonPage;
import cn.cerc.jmis.page.JspPage;

public class FrmAcceptor extends AbstractForm {

    @Override
    public IPage execute() throws Exception {
        return new JspPage(this, "common/FrmAcceptor.jsp");
    }

    public IPage login() throws Exception {
        JsonPage jspPage = new JsonPage(this);
        String sessionId = getRequest().getParameter("id");
        WebSocket ws = WebSocket.getWebSocketSet().get(sessionId);
        if (ws == null) {
            jspPage.put("result", false);
            jspPage.put("message", "没有找到相对应的客户端");
            return jspPage;
        }
        if (ws.sendMessage("登陆成功！")) {
            jspPage.put("result", true);
            jspPage.put("message", "登陆成功！");
        } else {
            jspPage.put("result", false);
            jspPage.put("message", ws.getMessage());
        }
        return jspPage;
    }


    @Override
    public boolean logon() {
        return true;
    }
}
