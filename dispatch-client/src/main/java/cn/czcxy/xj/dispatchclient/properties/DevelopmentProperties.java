package cn.czcxy.xj.dispatchclient.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Auther: wwh
 * @Date: 2018/6/30 0030 23:11
 * @Description: 程序制作的配置
 */
@Component
@ConfigurationProperties(prefix = "development")
@Getter
@Setter
public class DevelopmentProperties {
    private String generatorConfigPath;
    private String javaPath;
    private String htmPath;
    private String webInfoPath;
    private String generatorConfigFtl;
    private String controllerFtl;
    private String serviceFtl;
    private String serviceImplFtl;
    private String gridHtmlFtl;
    private String editHtmlFtl;
    private String gridVueFtl;
    private String editVuelFtl;
}
