package cn.czcxy.xj.core.platform.redis;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.redis")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RedisConn {
    private String host;
    private int port;
    private int timeout;
    private String password;
}
