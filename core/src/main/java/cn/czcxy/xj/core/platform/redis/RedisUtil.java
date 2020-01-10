package cn.czcxy.xj.core.platform.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {
    @Autowired
    private RedisTemplate redisTemplate;

    //半小时
    public static final Long tokenTimeOut = Long.valueOf(60 * 30);

    /**
     * 19 * 批量删除对应的value
     * 20 *
     * 21 * @param keys
     * 22
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 30 * 批量删除key
     * 31 *
     * 32 * @param pattern
     * 33
     */
    @SuppressWarnings("unchecked")
    public void removePattern(final String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0) {
            redisTemplate.delete(keys);
        }
    }

    /**
     * 42 * 删除对应的value
     * 43 *
     * 44 * @param key
     * 45
     */
    @SuppressWarnings("unchecked")
    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * 54 * 判断缓存中是否有对应的value
     * 55 *
     * 56 * @param key
     * 57 * @return
     * 58
     */
    @SuppressWarnings("unchecked")
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 65 * 读取缓存
     * 66 *
     * 67 * @param key
     * 68 * @return
     * 69
     */
    @SuppressWarnings("unchecked")
    public Object get(final String key) {
        Object result = null;
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }

    /**
     * 79 * 写入缓存
     * 80 *
     * 81 * @param key
     * 82 * @param value
     * 83 * @return
     * 84
     */
    @SuppressWarnings("unchecked")
    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 99 * 写入缓存
     * 100 *
     * 101 * @param key
     * 102 * @param value
     * 103 * @return
     * 104
     */
    @SuppressWarnings("unchecked")
    public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public String createToken(String current) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
        String result = sdf.format(new Date());

        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replace("-", "");
        result = result + uuid + "_" + current;

        return result;
    }
}
