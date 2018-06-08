package cn.cerc.sample.es;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

import cn.cerc.jdb.core.TDateTime;

public class ElasticsearchTest {
    private Logger log = LoggerFactory.getLogger(ElasticsearchTest.class);

    public static final String es_host = "120.79.185.14";
    public static final int es_port = 9300; // 客户端请求端口为9300，http访问端口为9200

    private TransportClient client = null;

    @SuppressWarnings("resource")
    @Before
    public void setUP() throws UnknownHostException {
        client = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(es_host), es_port));
        log.info("连接es，连接信息{}", client.toString());
    }

    /**
     * 创建索引
     * 
     * @throws IOException
     */
    // @Test
    public void addIndex1() throws IOException {
        log.info("---------test1------------");
        // IndexResponse response = client
        // .prepareIndex("msg", "tweet",
        // "1").setSource(XContentFactory.jsonBuilder().startObject()
        // .field("userName", "张三").field("sendDate", "2018-06-08").field("msg",
        // "你好李四").endObject())
        // .get();

        // prepareIndex方法创建索引库，index参数为索引名，必须为小写(类似库名)；type参数为类型(类似于表名)；id参数为每条记录的惟一编码，不赋值则自动生成
        // .field方法创建索引库的同时插入数据
        XContentBuilder xb = XContentFactory.jsonBuilder();
        xb.startObject();
        xb.field("userName", "张三");
        xb.field("sendDate", TDateTime.Now());
        xb.field("msg", "你好李四");
        xb.endObject();
        IndexResponse response = client.prepareIndex("msg", "tweet", "1").setSource(xb).get();

        log.info("索引名称：{}，类型：{}，文档id：{}，当前实例状态：{}", response.getIndex(), response.getType(), response.getId(),
                response.status());
    }

    // @Test
    public void addIndex2() {
        log.info("---------test2------------");
        String jsonStr = "{\"userName\":\"张三1\",\"sendDate\":\"2017-11-30\",\"msg\":\"你好李四1\"}";
        String jsonStr2 = "{\"userName\":\"张三2\",\"sendDate\":\"2017-11-29\",\"msg\":\"你好李四2\"}";
        IndexResponse response1 = client.prepareIndex("weixin", "tweet").setSource(jsonStr, XContentType.JSON).get();
        IndexResponse response2 = client.prepareIndex("weixin", "tweet").setSource(jsonStr2, XContentType.JSON).get();

        log.info("索引名称1：{}，类型1：{}，文档id1：{}，当前实例状态1：{}", response1.getIndex(), response1.getType(), response1.getId(),
                response1.status());
        log.info("索引名称2：{}，类型2：{}，文档id2：{}，当前实例状态2：{}", response2.getIndex(), response2.getType(), response2.getId(),
                response2.status());

        // 获取数据时，必须通过创建索引库的名称index，类型type，唯一编码id获取
        GetResponse msg = client.prepareGet("msg", "tweet", "1").get();
        GetResponse getResponse1 = client.prepareGet("weixin", "tweet", response1.getId()).get();
        GetResponse getResponse2 = client.prepareGet("weixin", "tweet", response2.getId()).get();
        log.info("取得数据msg：{}", msg.getSourceAsString());
        log.info("取得数据1：{}", getResponse1.getSourceAsString());
        log.info("取得数据2：{}", getResponse2.getSourceAsString());
    }

    // @Test
    public void addIndex3() {
        log.info("---------test3------------");
        Map<String, Object> items = new HashMap<>();
        items.put("userName", "张三Map");
        items.put("sendDate", TDateTime.Now());
        items.put("msg", "你好李四Map");

        IndexResponse response = client.prepareIndex("dingding", "tweet").setSource(items).get();

        log.info("Map索引名称：{}，Map类型：{}，Map文档id：{}，Map当前实例状态：{}", response.getIndex(), response.getType(),
                response.getId(), response.status());

        GetResponse map = client.prepareGet("dingding", "tweet", response.getId()).get();
        log.info("取得Map数据：{}", map.getSourceAsMap());
    }

    // @Test
    public void addIndex4() {
        log.info("---------test4------------");
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("userName", "张三");
        jsonObject.addProperty("sendDate", "2017-11-23");
        jsonObject.addProperty("msg", "你好李四");

        IndexResponse response = client.prepareIndex("qq", "tweet").setSource(jsonObject.toString(), XContentType.JSON)
                .get();

        log.info("Json索引名称：{}，Json类型：{}，Json文档id：{}，Json当前实例状态：{}", response.getIndex(), response.getType(),
                response.getId(), response.status());

        GetResponse json = client.prepareGet("qq", "tweet", response.getId()).get();
        log.info("取得Json数据：{}", json.getSourceAsString());
    }

    // @Test
    public void addIndex5() {
        log.info("---------test5------------");
        String jsonStr1 = "{\"userName\":\"张三1\",\"sendDate\":\"2017-11-30\",\"msg\":\"你好李四1\"}";
        String jsonStr2 = "{\"userName\":\"张三1\",\"sendDate\":\"2017-11-30\",\"msg\":\"你好李四王五123\"}";

        IndexResponse response1 = client.prepareIndex("weixin", "tweet").setSource(jsonStr1, XContentType.JSON).get();

        log.info("索引名称1：{}，类型1：{}，文档id1：{}，当前实例状态1：{}", response1.getIndex(), response1.getType(), response1.getId(),
                response1.status());

        GetResponse getResponse1 = client.prepareGet("weixin", "tweet", response1.getId()).get();
        log.info("取得update前数据：{}", getResponse1.getSourceAsString());

        UpdateResponse upResponse = client.prepareUpdate("weixin", "tweet", response1.getId())
                .setDoc(jsonStr2, XContentType.JSON).get();

        log.info("upResponse索引名称：{}，upResponse类型：{}，upResponse文档id：{}，upResponse当前实例状态：{}", upResponse.getIndex(),
                upResponse.getType(), upResponse.getId(), upResponse.status());

        GetResponse update = client.prepareGet("weixin", "tweet", upResponse.getId()).get();
        log.info("取得update后数据：{}", update.getSourceAsString());
    }

    // @Test
    public void addIndex6() {
        log.info("---------test6------------");

        DeleteResponse delResponse = client.prepareDelete("msg", "tweet", "1").get();

        log.info("delResponse索引名称：{}，delResponse类型：{}，delResponse文档id：{}，delResponse当前实例状态：{}", delResponse.getIndex(),
                delResponse.getType(), delResponse.getId(), delResponse.status());

        // 再获取数据
        GetResponse getResponse = client.prepareGet("msg", "tweet", "1").get();
        log.info("取得delete后数据：{}", getResponse.getSourceAsString());
    }

    @Test
    public void addIndex7() {
        // for (int i = 0; i < 1000; i++) {
        // PartItem json1 = new PartItem();
        // json1.setDesc("java编程思想");
        // json1.setSpec("《Java编程思想》这本书赢得了全球程序员的广泛赞誉" + i);
        // client.prepareIndex("vinedb", "part_info").setSource(json1.toString(),
        // XContentType.JSON).get();
        // }

        // 匹配多个字段
        MultiMatchQueryBuilder matchQuery1 = QueryBuilders.multiMatchQuery("33", "desc", "spec");
        // 匹配单个字段
        // MatchQueryBuilder matchQuery2 = QueryBuilders.matchQuery("content", "全球");

        // 设置多个条件
        // BoolQueryBuilder qb =
        // QueryBuilders.boolQuery().must(matchQuery1).must(matchQuery2);

        long time = System.currentTimeMillis();
        SearchResponse response = client.prepareSearch("vinedb").setQuery(matchQuery1).setMinScore(0.5f).execute()
                .actionGet();
        log.info("执行时长：{}", System.currentTimeMillis() - time);

        SearchHits searchHits = response.getHits();
        log.info("共搜索到 {} 条结果", searchHits.getTotalHits());

        for (SearchHit hit : searchHits) {
            log.info("" + hit.getScore());
            log.info("索引名称：{}，索引类型：{}，索引id：{}", hit.getIndex(), hit.getType(), hit.getId());
            log.info("通过String方式输出搜索内容：");
            log.info(hit.getSourceAsString());
            log.info("通过Map方式输出搜索内容：");
            Map<String, Object> map = hit.getSourceAsMap();
            for (String key : map.keySet()) {
                log.info("key is {} value is {}", key, map.get(key));
            }

            log.info("----------------------------------------");
        }
    }

    @After
    public void close() {
        if (client != null) {
            log.info("关闭es连接！");
            client.close();
        }
    }
}
