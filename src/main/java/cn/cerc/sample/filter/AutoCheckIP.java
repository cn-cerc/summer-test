package cn.cerc.sample.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.cerc.jbean.cache.Buffer;
import cn.cerc.jdb.mysql.SqlQuery;

@Component
public class AutoCheckIP implements Runnable {

    @Autowired
    private QueueHandle handle;

    @Override
    // 不管执行多久，执行后一定会休息1分钟启动
    @Scheduled(fixedRate = 5 * 1000)
    public void run() {
        SqlQuery ds = new SqlQuery(handle);
        ds.add("select * from ip_blacklist");
        ds.open();
        while (ds.fetch()) {
            Buffer buff = new Buffer("ip" + ds.getString("ip"));
            buff.setField("t", true);
            buff.post();
        }
    }

}
