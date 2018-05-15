package cn.cerc.sample.security;

import org.springframework.stereotype.Component;

import cn.cerc.jbean.core.ServerConfig;
import cn.cerc.jdb.core.IConfig;
import cn.cerc.jdb.core.IHandle;
import cn.cerc.jdb.mysql.SqlConnection;
import cn.cerc.jdb.mysql.SqlSession;

@Component
public class QueueHandle implements IHandle, AutoCloseable {
    private SqlSession mysqlSession;
    private String corpNo;

    public QueueHandle() {
        super();
        IConfig config = new IConfig() {

            @Override
            public String getProperty(String key) {
                ServerConfig config = ServerConfig.getInstance();
                if (key.equals(SqlSession.rds_site)) {
                    return config.getProperty(SqlSession.rds_site);
                } else if (key.equals(SqlSession.rds_database)) {
                    return config.getProperty(SqlSession.rds_database);
                } else if (key.equals(SqlSession.rds_username))
                    return config.getProperty(SqlSession.rds_username);
                else if (key.equals(SqlSession.rds_password))
                    return config.getProperty(SqlSession.rds_password);
                else
                    return null;
            }

            @Override
            public String getProperty(String key, String def) {
                String result = getProperty(key);
                if (result == null)
                    return def;
                return result;
            }
        };

        // mysql
        SqlConnection conn = new SqlConnection();
        conn.setConfig(config);
        mysqlSession = conn.getSession();
    }

    @Override
    public String getCorpNo() {
        if (this.corpNo == null)
            throw new RuntimeException("corpNo is null");
        return this.corpNo;
    }

    @Override
    public String getUserCode() {
        throw new RuntimeException("userCode is null");
    }

    @Override
    public Object getProperty(String key) {
        if (SqlSession.sessionId.equals(key))
            return mysqlSession;
        return null;
    }

    // 关闭资源
    @Override
    public void closeConnections() {
        mysqlSession.closeSession();
    }

    @Override
    public void close() {
        closeConnections();
    }

    // 用户姓名
    @Override
    public String getUserName() {
        return getUserCode();
    }

    // 设置自定义参数
    @Override
    public void setProperty(String key, Object value) {
        throw new RuntimeException("调用了未被实现的接口");
    }

    // 直接设置成登录成功状态，用于定时服务时初始化等，会生成内存临时的token
    @Override
    public boolean init(String bookNo, String userCode, String clientCode) {
        throw new RuntimeException("调用了未被实现的接口");
    }

    // 在登录成功并生成token后，传递token值进行初始化
    @Override
    public boolean init(String token) {
        throw new RuntimeException("调用了未被实现的接口");
    }

    // 返回当前是否为已登入状态
    @Override
    public boolean logon() {
        return false;
    }

    public void setCorpNo(String corpNo) {
        this.corpNo = corpNo;
    }
}
