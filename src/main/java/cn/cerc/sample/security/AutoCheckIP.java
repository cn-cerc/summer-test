package cn.cerc.sample.security;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.cerc.jbean.cache.Buffer;
import cn.cerc.jdb.mysql.SqlQuery;

@Component
public class AutoCheckIP implements Runnable {

    private QueueHandle handle;

    @Override
    @Scheduled(fixedRate = 1 * 30 * 1000)
    public void run() {
        try {
            handle = new QueueHandle();
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
        } finally {
            handle = null;
        }
    }

}
