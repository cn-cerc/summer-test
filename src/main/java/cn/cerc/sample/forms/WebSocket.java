package cn.cerc.sample.forms;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * @ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,
 *                 注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
 */
@ServerEndpoint(value = "/websocket", configurator = GetHttpSessionConfigurator.class)
public class WebSocket {
    // concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    private static Map<String, WebSocket> webSocketSet = new LinkedHashMap<>();

    private String message;

    // 与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    HttpSession httpSession;

    /**
     * 连接建立成功调用的方法
     * 
     * @param session
     *            可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        this.session = session;
        webSocketSet.put(httpSession.getId(), this);
        System.out.println("有新连接加入！当前在线人数为" + webSocketSet.size());
        System.out.println("sessionId:" + httpSession.getId());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(httpSession.getId()); // 从map中删除
        System.out.println("有一连接关闭！当前在线人数为" + webSocketSet.size());
    }

    /**
     * 收到客户端消息后调用的方法
     * 
     * @param message
     *            客户端发送过来的消息
     * @param session
     *            可选的参数
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message);
        // 群发消息
        for (String key : webSocketSet.keySet()) {
            if (!webSocketSet.get(key).sendMessage(message)) {
                System.out.println("消息发送错误信息：" + this.getMessage());
            }
        }
    }

    /**
     * 发生错误时调用
     * 
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        webSocketSet.remove(httpSession.getId());
        error.printStackTrace();
    }

    /**
     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
     * 
     * @param message
     * @throws IOException
     */
    public boolean sendMessage(String message) {
        boolean result = true;
        try {
            this.session.getBasicRemote().sendText(message);
            System.out.println("sendMessage:" + message);
        } catch (IOException e) {
            this.message = e.getMessage();
            result = false;
        }
        return result;
    }

    public static Map<String, WebSocket> getWebSocketSet() {
        return webSocketSet;
    }

    public String getMessage() {
        return message;
    }
}