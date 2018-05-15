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
    @Scheduled(fixedRate = 1 * 30 * 1000)
    public void run() {
        SqlQuery ds = new SqlQuery(handle);
        ds.add("select distinct ip_ from ip_blacklist");
        ds.open();
        while (ds.fetch()) {
            Buffer buff = new Buffer("ip" + ds.getString("ip"));
            buff.setField("t", true);
            buff.post();
        }
    }

}
