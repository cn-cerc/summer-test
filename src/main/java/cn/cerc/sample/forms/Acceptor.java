package cn.cerc.sample.forms;

import org.springframework.stereotype.Component;

import com.google.gson.JsonObject;

import cn.cerc.mis.core.AbstractForm;
import cn.cerc.mis.core.IPage;
import cn.cerc.mis.page.JsonPage;
import cn.cerc.mis.page.JspPage;
import cn.cerc.mis.page.qrcode.WebSocket;

@Component
public class Acceptor extends AbstractForm {

    @Override
    public IPage execute() throws Exception {
        JspPage page = new JspPage(this, "common/acceptor.jsp");
        page.add("sessionId", getRequest().getSession().getId());
        return page;
    }

    public IPage login() throws Exception {
        JsonPage page = new JsonPage(this);
        String sessionId = getRequest().getParameter("sessionId");
        WebSocket ws = WebSocket.getWebSocketSet().get(sessionId);
        if (ws == null) {
            page.put("result", false);
            page.put("message", "没有找到相对应的客户端");
            return page;
        }
        if (ws.sendMessage("登录成功！")) {
            page.put("result", true);
            page.put("message", "登录成功！");
        } else {
            page.put("result", false);
            page.put("message", ws.getMessage());
        }
        return page;
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
        return new JsonPage(this).setResultMessage(true, "已确认");
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
