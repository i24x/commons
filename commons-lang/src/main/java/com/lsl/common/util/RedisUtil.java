package com.lsl.common.util;

import com.alibaba.fastjson.JSON;
import com.lsl.common.util.file.PropertiesUtils;
import com.lsl.domain.User;
import redis.clients.jedis.Jedis;

import java.util.*;

public class RedisUtil {
    public static final Properties redisConfig =
        PropertiesUtils.getProperties(PropertiesUtils.ConfigPath.REDIS_CONFIG_PATH);
    private static volatile Jedis jedis;

    // https://www.cnblogs.com/ring2/p/11401809.html
    public static Jedis getJedis() {
        if (jedis == null) {
            synchronized (RedisUtil.class) {
                if (jedis == null) {
                    jedis = new Jedis(PropertiesUtils.getString(redisConfig, PropertiesUtils.ConfigPath.REDIS_HOST),
                        PropertiesUtils.getInt(redisConfig, PropertiesUtils.ConfigPath.REDIS_PORT));
                }
            }
        }

        return jedis;
    }

    public static void main(String[] args) {
        // System.out.println("RedisMonitor 分布式锁");
        // Thread redisMonitor = new Thread(new RedisMonitor.MonitorTask(new
        // Jedis(PropertiesUtils.getString(redisConfig, ConfigPath.REDIS_HOST)
        // , PropertiesUtils.getInt(redisConfig, ConfigPath.REDIS_PORT))));
        // redisMonitor.start();

        // setnx
        System.out.println("setnx 分布式锁");
        Long setnx = RedisUtil.getJedis().setnx("token-cay9417", UUID.randomUUID().toString());
        System.out.println(setnx);
        setnx = RedisUtil.getJedis().setnx("token-cay9417", UUID.randomUUID().toString());
        System.out.println(setnx);

        // incr
        System.out.println("incr计数器限流");
        Long incr = RedisUtil.getJedis().incr("product-count");
        System.out.println(RedisUtil.getJedis().incrBy("product-count", 10));
        System.out.println(RedisUtil.getJedis().get("product-count"));
        System.out.println(RedisUtil.getJedis().incrBy("product-count2", 10));
        System.out.println(RedisUtil.getJedis().decrBy("product-count2", 10));
        System.out.println(RedisUtil.getJedis().get("product-count2"));

        System.out.println("incr收藏数");
        System.out.println(RedisUtil.getJedis().incr("read-count"));

        System.out.println(RedisUtil.getJedis().set("product-1", "NXEX", "NX", "EX", 5));
        System.out.println(RedisUtil.getJedis().get("product-1"));

        System.out.println("mset 多个值储存操作");
        String mset = RedisUtil.getJedis().mset("user-1", "1", "user-2", "0");
        System.out.println(mset);
        List<String> mget = RedisUtil.getJedis().mget("user-1", "user-2");
        System.out.println(String.join(",", mget));

        User user = User.instance();
        System.out.println("set 保存对象");
        RedisUtil.getJedis().set("user:1", JSON.toJSONString(user));
        System.out.println(RedisUtil.getJedis().get("user:1"));
        RedisUtil.getJedis().set("user:1:name", "lsl");
        RedisUtil.getJedis().set("user:1:phone", "10086");
        RedisUtil.getJedis().set("user:1:email", "lsl@firefox.com");
        RedisUtil.getJedis().set("user:1:address", "Asia/ShangHai");

        // hset 节省储存空间 分类 不能设置过期
        RedisUtil.getJedis().hset("t_user", "user:1:name", "lsl");
        RedisUtil.getJedis().hset("t_user", "user:1:phone", "10086");
        RedisUtil.getJedis().hset("t_user", "user:1:email", "lsl@firefox.com");

        Map<String, String> t_user = RedisUtil.getJedis().hgetAll("t_user");
        System.out.println(t_user);
        // Long t_user1 = RedisUtil.getJedis().hincrBy("t_user", "user:1:name", 1);//报错
        // Long t_user1 = RedisUtil.getJedis().hincrBy("t_user", "user:1:phone", 1);//OK

        System.out.println(RedisUtil.getJedis().hlen("t_user"));
        System.out.println(RedisUtil.getJedis().hmget("t_user", "user:1:name", "user:1:phone"));

        System.out.println(RedisUtil.getJedis().lpush("dis_queue", "cart:1001"));
        System.out.println(RedisUtil.getJedis().llen("dis_queue"));
        System.out.println(RedisUtil.getJedis().lpop("dis_queue"));
        System.out.println(RedisUtil.getJedis().rpop("dis_queue"));
        System.out.println(RedisUtil.getJedis().lrange("dis_queue", 0, 2));
        // System.out.println(RedisUtil.getJedis().brpop(3,"dis_queue"));

        // 抽奖 set member
        System.out.println(RedisUtil.getJedis().sadd("user-1-friends", "user-2"));
        System.out.println(RedisUtil.getJedis().sadd("user-1-friends", "user-3"));
        System.out.println(RedisUtil.getJedis().sadd("user-1-friends", "user-4"));
        System.out.println(RedisUtil.getJedis().sadd("user-1-friends", "user-XX"));
        System.out.println(RedisUtil.getJedis().srem("user-1-friends", "user-XX"));
        System.out.println(RedisUtil.getJedis().smembers("user-1-friends"));

        RedisUtil.getJedis().scard("user-1-friends");

        Boolean sismember = RedisUtil.getJedis().sismember("user-1-friends", "user-4");

        System.out.println("抽奖：" + RedisUtil.getJedis().srandmember("user-1-friends", 2));
        // System.out.println("抽奖："+RedisUtil.getJedis().spop("user-1-friends", 2));//1 等奖 2 等奖

        // set 运算

        System.out.println(RedisUtil.getJedis().sadd("user-1-friends", "user-2"));
        System.out.println(RedisUtil.getJedis().sadd("user-1-friends", "user-3"));
        System.out.println(RedisUtil.getJedis().sadd("user-1-friends", "user-4"));

        System.out.println(RedisUtil.getJedis().sadd("user-2-friends", "user-2"));
        System.out.println(RedisUtil.getJedis().sadd("user-2-friends", "user-3"));
        System.out.println(RedisUtil.getJedis().sadd("user-2-friends", "user-5"));

        System.out.println("交集:" + RedisUtil.getJedis().sinter("user-1-friends", "user-2-friends"));
        System.out.println("并集:" + RedisUtil.getJedis().sunion("user-1-friends", "user-2-friends"));
        System.out.println("不同集:" + RedisUtil.getJedis().sdiff("user-1-friends", "user-2-friends"));// B-A

        // 有序集合

        RedisUtil.getJedis().zadd("article", 1, "id-1");
        RedisUtil.getJedis().zadd("article", 12, "id-2");
        RedisUtil.getJedis().zadd("article", 3, "id-3");
        RedisUtil.getJedis().zadd("article", 5, "id-4");

        System.out.println(RedisUtil.getJedis().zscore("article", "id-2"));

        RedisUtil.getJedis().zadd("hotNews:20190312", 1222, "msg-1");
        System.out.println(RedisUtil.getJedis().zincrby("hotNews:20190312", 1, "msg-1"));

        Set<String> article = RedisUtil.getJedis().zrange("article", 0, 2);
        System.out.println(article);

        // RedisUtil.getJedis().getbit()

    }

}
