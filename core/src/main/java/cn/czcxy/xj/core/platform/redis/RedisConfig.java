package cn.czcxy.xj.core.platform.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class RedisConfig extends CachingConfigurerSupport {
    @Autowired
    private RedisConn redisConn;

    /**
     * selectByExample缓存策略
     *
     * @return
     */
    @Bean
    public KeyGenerator selectByExampleCache() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params) {
                    Example example = (Example) obj;
                    sb.append(example.getEntityClass().getName());
                    List<Example.Criteria> criteria = example.getOredCriteria();
                    for (Example.Criteria c : criteria) {
                        List<Example.Criterion> criteria1 = c.getAllCriteria();
                        for (Example.Criterion criterion : criteria1) {
                            sb.append(";condition=" + criterion.getCondition() + ";value=" + criterion.getValue());
                        }
                    }
                }
                System.out.println("读取缓存key=" + sb.toString());
                return sb.toString();
            }
        };

    }

    /**
     * 32 * 生产key的策略
     * 33 *
     * 34 * @return
     * 35
     */
    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }

    /**
     * 57 * 管理缓存
     * 58 *
     * 59 * @param redisTemplate
     * 60 * @return
     * 61
     */
    @SuppressWarnings("rawtypes")
    @Bean
    public CacheManager CacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager rcm = new RedisCacheManager(redisTemplate);
        // 设置cache过期时间,时间单位是秒
        rcm.setDefaultExpiration(RedisUtil.tokenTimeOut);
        return rcm;
    }

    /**
     * 76 * redis 数据库连接池
     * 77 * @return
     * 78
     */
    @Bean
    public JedisConnectionFactory redisConnectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName(redisConn.getHost());
        factory.setPort(redisConn.getPort());
        factory.setTimeout(redisConn.getTimeout()); // 设置连接超时时间
        factory.setPassword(redisConn.getPassword());
        return factory;
    }

    /**
     * 90 * redisTemplate配置
     * 91 *
     * 92 * @param factory
     * 93 * @return
     * 94
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        StringRedisTemplate template = new StringRedisTemplate(factory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

}
