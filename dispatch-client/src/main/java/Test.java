import cn.czcxy.xj.core.platform.base.annotation.BasePath;
import cn.czcxy.xj.core.util.ReflectionUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

public class Test {
    public static void main(String[] args) {
        String masterName = "mymaster";
        Set<String> sentinel = new HashSet<>();
        sentinel.add("123.207.55.47:26389");
        sentinel.add("47.93.187.183:26389");
        sentinel.add("114.115.173.111:26389");
        JedisSentinelPool jedisSentinelPool = new JedisSentinelPool(masterName, sentinel, "tudou123");
        Jedis jedis = jedisSentinelPool.getResource();
        System.out.println(jedis.get("a"));
    }
}
