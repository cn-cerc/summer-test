package cn.cerc.sample.filter;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.cerc.jbean.cache.Buffer;
import cn.cerc.jbean.core.AppHandle;
import cn.cerc.jdb.core.TDateTime;
import cn.cerc.jdb.mysql.SqlQuery;
import cn.cerc.jdb.other.utils;

public class SecurityFilter implements Filter {
    private static int MC = 100;
    private static int SC = 10;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String ip = utils.getRemoteAddr(req);
        if (filterIp(ip)) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        chain.doFilter(request, response);
    }

    public boolean filterIp(String ip) {
        Buffer buff = new Buffer("ip" + ip);
        if (buff.isNull()) {
            buff.setField("m", TDateTime.Now());
            buff.setField("mc", 1);
            buff.setField("s", TDateTime.Now());
            buff.setField("sc", 1);
        }

        if (buff.getBoolean("t")) {
            return true;
        }

        TDateTime now = TDateTime.Now();
        TDateTime s = buff.getDateTime("s");
        if (timeAllowed(now.toString(), s.toString())) {
            if (buff.getInt("mc") >= MC) {
                buff.setField("t", true);
                buff.post();
                saveToMysql(ip);
                return true;
            }
            if (buff.getInt("sc") >= SC) {
                buff.setField("t", true);
                buff.post();
                saveToMysql(ip);
                return true;
            }
            buff.setField("mc", buff.getInt("mc") + 1);
            buff.setField("sc", buff.getInt("sc") + 1);
            buff.post();
        }
        buff.post();
        return false;
    }

    private void saveToMysql(String ip) {
        AppHandle handle = new AppHandle();
        SqlQuery ds = new SqlQuery(handle);
        ds.add("select * from ip_blacklist where ip_='%s'", ip);
        ds.open();
        if (!ds.eof()) {
            return;
        }

        ds.append();
        ds.setField("ip_", ip);
        ds.setField("createTime_", TDateTime.Now());
        ds.post();
    }

    public boolean timeAllowed(String startTime, String endTime) {
        // 生成指定时间对象
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // 一天的毫秒数
        long nd = 1000 * 24 * 60 * 60;

        // 一小时的毫秒数
        long nh = 1000 * 60 * 60;

        // 一分钟的毫秒数
        long nm = 1000 * 60;

        // 一秒钟的毫秒数
        long ns = 1000;

        long diff;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        try {
            // 计算时间差
            diff = dateFormat.parse(endTime).getTime() - dateFormat.parse(startTime).getTime();
            day = diff / nd;// 计算差多少天
            hour = diff % nd / nh + day * 24;// 计算差多少小时
            min = diff % nd % nh / nm + day * 24 * 60;// 计算差多少分钟
            sec = diff % nd % nh % nm / ns;// 计算差多少秒
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // 天数
        if (day > 0) {
            return true;
        }

        // 小时
        if (hour - day * 24 > 0) {
            return true;
        }

        // 分
        if (min - day * 24 * 60 < 1) {
            return true;
        }

        // 秒
        return sec - day < 1;
    }

    public int compareSecond(TDateTime dateFrom) {
        return 0;
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
