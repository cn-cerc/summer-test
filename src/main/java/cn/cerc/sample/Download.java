package cn.cerc.sample;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import cn.cerc.jmis.page.JsonPage;

/**
 * Servlet implementation class Download
 */
public class Download extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String ver = "master";

    public Download() {
        super();
    }

    @Override
    public void init() {
        String ver = this.getServletConfig().getInitParameter("version");
        System.err.println("ver: " + ver);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String ip = getIpAddr(request);
        // System.out.println("ip：" + ip);
        // ip = "202.96.134.133";
        // ip= "1.1.1.1";
        String jspfile = "/WEB-INF/u.jsp";
        String apkfile = "http://uzu-down-oss.oss-us-west-1.aliyuncs.com/beta/user/miuFamily.apk";
        String url = String.format("http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js&ip=%s", ip);
        String data;
        try {
            data = postHttp(url, "");
            if (data.indexOf("}") > 1)//////////
                data = data.substring(data.indexOf("{"));
            System.out.println(data);
            if (data.contains("\\u4e2d\\u56fd")) {
                apkfile = "http://miufamily-down-oss.oss-cn-hongkong.aliyuncs.com/release/user/miuFamily.apk";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        request.setAttribute("apkFile", apkfile);
        request.getServletContext().getRequestDispatcher(jspfile).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private String postHttp(String url, String params) throws ClientProtocolException, IOException {
        HttpPost httpPost = new HttpPost(url);
        StringEntity entity = new StringEntity(params.toString(), "UTF-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);

        HttpClient client = HttpClientBuilder.create().build();

        HttpResponse response = client.execute(httpPost);
        // 如果请求成功
        if (response.getStatusLine().getStatusCode() != 200) {
            System.out.println("请求服务器失败，错误代码为：" + response.getStatusLine().getStatusCode());
            return null;
        }

        // 获取响应实体
        HttpEntity entity2 = response.getEntity();
        return EntityUtils.toString(entity2, "UTF-8");
    }

    public String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader(" x-forwarded-for ");
        if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
            ip = request.getHeader(" Proxy-Client-IP ");
        }
        if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
            ip = request.getHeader(" WL-Proxy-Client-IP ");
        }
        if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}