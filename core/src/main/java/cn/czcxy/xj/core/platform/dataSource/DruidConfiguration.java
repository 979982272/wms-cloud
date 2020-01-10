package cn.czcxy.xj.core.platform.dataSource;

import cn.czcxy.xj.core.platform.base.properties.JdbcProperties;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;

/**
 * http://127.0.0.1:8082/druid/webapp.html
 *
 * @Auther: wwh
 * @Date: 2018/12/19 0019 22:21
 * @Description:
 */
@Configuration
public class DruidConfiguration {

    @Resource
    private JdbcProperties jdbcProperties;

    @Bean
    public ServletRegistrationBean druidStatViewServlet() {
        String ip = "127.0.0.1";
        try {
            InetAddress address = InetAddress.getLocalHost();
            ip = address.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        //org.springframework.boot.context.embedded.ServletRegistrationBean提供类的进行注册.
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        //添加初始化参数：initParams
        //白名单：
//        servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
//        servletRegistrationBean.addInitParameter("allow", ip);
        //IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.
        // servletRegistrationBean.addInitParameter("deny","192.168.1.73");
        //登录查看信息的账号密码.
//        servletRegistrationBean.addInitParameter("loginUsername", "admin");
//        servletRegistrationBean.addInitParameter("loginPassword", "123456");
        //是否能够重置数据.
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }

    /**
     * 注册一个：filterRegistrationBean
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean druidStatFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        //添加过滤规则.
        filterRegistrationBean.addUrlPatterns("/*");
        //添加不需要忽略的格式信息.
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

    @Bean
    @Primary
    public DataSource druidDataSource() {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(jdbcProperties.getUrl());
        datasource.setUsername(jdbcProperties.getUsername());
        datasource.setPassword(jdbcProperties.getPassword());
        datasource.setDriverClassName(jdbcProperties.getDriverClassName());
        datasource.setInitialSize(jdbcProperties.getInitialSize());
        datasource.setMinIdle(jdbcProperties.getMinIdle());
        datasource.setMaxActive(jdbcProperties.getMaxActive());
        datasource.setMaxWait(jdbcProperties.getMaxWait());
        datasource.setTimeBetweenEvictionRunsMillis(jdbcProperties.getTimeBetweenEvictionRunsMillis());
        datasource.setMinEvictableIdleTimeMillis(jdbcProperties.getMinEvictableIdleTimeMillis());
        datasource.setValidationQuery(jdbcProperties.getValidationQuery());
        datasource.setTestWhileIdle(jdbcProperties.isTestWhileIdle());
        datasource.setTestOnBorrow(jdbcProperties.isTestOnBorrow());
        datasource.setTestOnReturn(jdbcProperties.isTestOnReturn());
        try {
            datasource.setFilters(jdbcProperties.getFilters());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datasource;
    }
}
