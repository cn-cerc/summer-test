package cn.cerc.sample.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.cerc.db.cache.Buffer;
import cn.cerc.db.mysql.SqlQuery;
import cn.cerc.sample.config.Handle;

@Component
public class AutoCheckIP implements Runnable {
    @Autowired
    private Handle handle;

    @Override
//    @Scheduled(fixedRate = 1 * 30 * 1000)
    public void run() {
        SqlQuery ds = new SqlQuery(handle);
        ds.add("select * from ip_blacklist");
        ds.open();
        while (ds.fetch()) {
            boolean white = ds.getBoolean("white_");
            Buffer buff = new Buffer("ip" + ds.getString("ip_"));
            buff.setField("t", !white);
            buff.setField("w", white);
            buff.setExpires(60);// 每1分钟重刷缓存
            buff.post();
        }
    }

}
