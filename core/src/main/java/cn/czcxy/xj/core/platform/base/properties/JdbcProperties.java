package cn.czcxy.xj.core.platform.base.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Auther: wwh
 * @Date: 2018/6/30 0030 23:38
 * @Description: 数据库配置文件
 */
@Component
@ConfigurationProperties(prefix = "spring.datasource")
@Getter
@Setter
public class JdbcProperties {
    private String url;
    private String username;
    private String password;
    private String type;
    private String driverClassName;


    private int initialSize;

    private int minIdle;

    private int maxActive;

    private int maxWait;

    private int timeBetweenEvictionRunsMillis;

    private int minEvictableIdleTimeMillis;

    private String validationQuery;

    private boolean testWhileIdle;

    private boolean testOnBorrow;

    private boolean testOnReturn;

    private String filters;

    private String logSlowSql;
}
