package cn.cerc.sample.forms;

import com.google.gson.JsonObject;

import cn.cerc.jbean.form.IPage;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JsonPage;
import cn.cerc.jmis.page.JspPage;

public class FrmAcceptor extends AbstractForm {

    @Override
    public IPage execute() throws Exception {
        JspPage page = new JspPage(this, "common/FrmAcceptor.jsp");
        page.add("sessionId", getRequest().getSession().getId());
        return page;
    }

    public IPage login() throws Exception {
        JsonPage jspPage = new JsonPage(this);
        String sessionId = getRequest().getParameter("sessionId");
        WebSocket ws = WebSocket.getWebSocketSet().get(sessionId);
        if (ws == null) {
            jspPage.put("result", false);
            jspPage.put("message", "没有找到相对应的客户端");
            return jspPage;
        }
        if (ws.sendMessage("登录成功！")) {
            jspPage.put("result", true);
            jspPage.put("message", "登录成功！");
        } else {
            jspPage.put("result", false);
            jspPage.put("message", ws.getMessage());
        }
        return jspPage;
    }

    public IPage scanQrCode() {
        String sessionId = this.getRequest().getParameter("sessionId");
        JsonObject json = new JsonObject();
        json.addProperty("result", true);
        json.addProperty("message", "扫码成功");
        WebSocket.getWebSocketSet().get(sessionId).sendMessage(json.toString());
        return new JsonPage(this).setResultMessage(true, "扫码成功");
    }

    public IPage acceptor() {
        String sessionId = this.getRequest().getParameter("sessionId");
        JsonObject json = new JsonObject();
        json.addProperty("result", true);
        json.addProperty("message", "已确认");
        WebSocket.getWebSocketSet().get(sessionId).sendMessage(json.toString());
        return new JsonPage(this).setResultMessage(true, "应用服务器处理自动登录");
    }

    @Override
    public boolean logon() {
        return true;
    }

    public static void main(String[] args) {
        JsonObject json = new JsonObject();
        json.addProperty("result", true);
        json.addProperty("message", "扫码成功");
        System.out.println(json);
    }
}
