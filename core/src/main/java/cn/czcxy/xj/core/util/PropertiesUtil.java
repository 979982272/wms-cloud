package cn.czcxy.xj.core.util;



import org.apache.commons.lang.StringUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by li on 2015/10/15.
 */
public class PropertiesUtil {

    public Properties getProperties(String str) {
        Properties properties = new Properties();
        FileInputStream is = null;
        try {
            String fileUrl = getClass().getResource("/" + str).getPath();
            // 分开读取配置文件工程 resource目录与jar中的配置文件
            if (fileUrl.startsWith("file")) {
                InputStream inputStream = getClass().getResourceAsStream("/" + str);
                properties.load(inputStream);
            } else {
                is = new FileInputStream(fileUrl);
                properties.load(is);
            }

        } catch (IOException ioexception) {
            System.out.println("Open config file failure.");
        } catch (NullPointerException e) {
            System.out.println("is is null");
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return properties;
    }

    public static String getValue(String code, Object... objects) {
        PropertiesUtil propertiesUtils = new PropertiesUtil();
        // 模板路径
        Properties properties = propertiesUtils.getProperties("sysconfig.properties");
        String value = properties.getProperty(code);
        for (int i = 0; i < objects.length; i++) {
            if (StringUtils.isNotEmpty(value) && value.indexOf("{" + i + "}") > 0) {
                value = value.replaceAll("\\{" + i + "\\}", objects[i] != null ? objects[i].toString() : "");
            }
        }
        return value;
    }


}
