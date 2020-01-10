package cn.czcxy.xj.core.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Map;

/**
 * freemaker工具
 *
 * @author weihua
 * @create 2017-05-07下午 9:36
 */
@Component
public class FreemakerUtil {

    /**
     * 通过freemaker创建文件
     *
     * @param map        数据源
     * @param ftlPath    模板路径
     * @param filePath   生成文件的路径
     * @param folderPath 文件夹路径，用来判断是否有这个路径
     */
    public static void createFileByFreemaker(Map map, String ftlPath, String filePath, String folderPath,ClassLoader classLoader) throws Exception {
        Writer out = null;
        try {
            Configuration configuration = SpringUtil.getBean(Configuration.class);
            // 设置模板的载入路径
            String path = FreemakerUtil.class.getClassLoader().getResource("").getPath();
            // 加载到web-info目录下
            configuration.setDirectoryForTemplateLoading(new File(path));
            // 设置模板
            Template template = null;
            template = configuration.getTemplate(ftlPath, "UTF-8");
            // 文件夹路径，判断是否存在
            if (StringUtils.isNotEmpty(folderPath)) {
                // 判断文件夹路径，生成文件夹
                File folderFile = new File(folderPath);
                if (!folderFile.exists()) {
                    folderFile.mkdirs();
                }
            }
            // 文件输出路径
            File outFile = new File(filePath);
            // 设置输出流编码为utf-8，默认为gbk不设置会乱码
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "UTF-8"));
            template.process(map, out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

}
