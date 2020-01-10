package cn.czcxy.xj.basicclient;

import cn.czcxy.xj.core.platform.redis.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleCacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * redis缓存
 * 需要在Application中启用redis缓存才会生效；还有一部分配置文件在core中
 */
@Configuration
public class RedisCachingConfig {
    @Autowired
    private RedisTemplate redisTemplate;

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

    @Bean
    public CacheErrorHandler errorHandler() {
        return new RedisCacheErrorHandler();
    }

    private static class RedisCacheErrorHandler extends SimpleCacheErrorHandler {
        public static final Logger log = LoggerFactory.getLogger(RedisCachingConfig.class);

        @Override
        public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
            log.error("handleCacheGetError key = {}, value = {}", key, cache);
            log.error("cache get error", exception);
        }

        @Override
        public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
            log.error("handleCachePutError key = {}, value = {}", key, cache);
            log.error("cache put error", exception);
        }

        @Override
        public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
            log.error("handleCacheEvictError key = {}, value = {}", key, cache);
            log.error("cache evict error", exception);
        }

        @Override
        public void handleCacheClearError(RuntimeException exception, Cache cache) {
            log.error("handleCacheClearError value = {}", cache);
            log.error("cache clear error", exception);
        }
    }
}
