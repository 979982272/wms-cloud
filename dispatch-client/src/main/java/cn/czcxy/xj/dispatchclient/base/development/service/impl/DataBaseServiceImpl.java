package cn.czcxy.xj.dispatchclient.base.development.service.impl;

import cn.czcxy.xj.basicserver.development.service.IDevelopmentClientService;
import cn.czcxy.xj.core.platform.base.properties.JdbcProperties;
import cn.czcxy.xj.core.util.FreemakerUtil;
import cn.czcxy.xj.dispatchclient.base.development.service.IDataBaseService;
import cn.czcxy.xj.dispatchclient.properties.DevelopmentProperties;
import org.apache.commons.lang.StringUtils;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.*;

/**
 * @Auther: wwh
 * @Date: 2018/6/30 0030 23:02
 * @Description:
 */
@Service
public class DataBaseServiceImpl implements IDataBaseService {
    @Resource
    private DevelopmentProperties developmentProperties;

    @Resource
    private JdbcProperties jdbcProperties;

    @Resource
    private IDevelopmentClientService developmentClientService;


    /**
     * 数据库字段到java类型的转换
     *
     * @param sqlType 数据库字段
     * @param scale   精度
     * @param size    大小
     * @return
     */
    private String oracleSqlType2JavaType(String sqlType, Integer scale, Integer size) {
        if (sqlType.equals("integer") || sqlType.equals("int")) {
            return "java.lang.Integer";
        } else if (sqlType.equals("long")) {
            return "java.lang.Long";
        } else if (sqlType.equals("float") || sqlType.equals("float precision"))
            return "float";
        else if (sqlType.equals("double") || sqlType.equals("double precision")) {
            return "java.lang.Double";
        } else if (sqlType.equals("number") || sqlType.equals("decimal")
                || sqlType.equals("numeric") || sqlType.equals("real")) {
            return scale == null ? (size == null ? "java.lang.Integer" : "java.lang.Long") : "java.math.BigDecimal";
        } else if (sqlType.equals("varchar") || sqlType.equals("varchar2")
                || sqlType.equals("char") || sqlType.equals("nvarchar2")
                || sqlType.equals("nchar")) {
            return "java.lang.String";
        } else if (sqlType.equals("datetime") || sqlType.equals("date")
                || sqlType.equals("timestamp")) {
            return "java.util.Date";
        }
        return null;
    }

    @Override
    public void createEntityCode(String[] ids, String[] pack) throws Exception {
        Map<String, Object> columnInfos = null;
        String idType = null;
        Integer size = null;
        Integer scale = null;
        String dataType = null;
        for (int i = 0; i < ids.length; i++) {
            columnInfos = developmentClientService.findPriByTable(ids[i]);
            size = columnInfos.get("size") != null ? (Integer) columnInfos.get("size") : null;
            scale = columnInfos.get("scale") != null ? (Integer) columnInfos.get("scale") : null;
            dataType = String.valueOf(columnInfos.get("dataType"));
            idType = oracleSqlType2JavaType(dataType,
                    scale, size);
            if (StringUtils.isEmpty(idType)) {
                throw new Exception("查询不到对应的java映射类型!【" + dataType + "】");
            }
            createJava(ids[i], pack[i], idType);
            createMybatis(ids[i], pack[i]);
        }
    }

    /**
     * 创建Mybatis
     *
     * @param table
     * @param pack
     * @throws Exception
     */
    private void createMybatis(String table, String pack) throws Exception {
        createMybatisConfig(table, pack);
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        // 设置读取的编码
        Reader reader = new BufferedReader(new InputStreamReader(new FileInputStream(this.getClass().getClassLoader().getResource("generatorConfig.xml").getPath()), "utf-8"));
        ConfigurationParser cp = new ConfigurationParser(warnings);
        //加载配置文件
        org.mybatis.generator.config.Configuration config = cp.parseConfiguration(reader);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,
                callback, warnings);
        myBatisGenerator.generate(null);
    }


    /**
     * 创建Mybatis配置文件
     *
     * @param table
     * @param pack
     * @throws Exception
     */
    private void createMybatisConfig(String table, String pack) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("table", table);
        map.put("path", developmentProperties.getJavaPath());
        map.put("pack", transPack(pack));
        String jdbcUrl = jdbcProperties.getUrl();
        jdbcUrl = jdbcUrl.replaceAll("\\&", "&amp;");
        map.put("jdbcDriver", jdbcProperties.getDriverClassName());
        map.put("jdbcUrl", jdbcUrl);
        map.put("jdbcUsername", jdbcProperties.getUsername());
        map.put("jdbcPassword", StringUtils.isNotEmpty(jdbcProperties.getPassword()) ? jdbcProperties.getPassword() : "");
        FreemakerUtil.createFileByFreemaker(map, developmentProperties.getGeneratorConfigFtl(), this.getClass().getClassLoader().getResource("generatorConfig.xml").getPath(), null, this.getClass().getClassLoader());
    }

    /**
     * 创建Java文件
     *
     * @param name
     * @param pack
     * @throws Exception
     */
    private void createJava(String name, String pack, String idType) throws Exception {
        createControllerJava(name, pack, idType);
        createServiceJava(name, pack, idType);
        createServiceImplJava(name, pack, idType);
    }

    /**
     * 创建控制器文件
     *
     * @param name
     * @param pack
     * @throws Exception
     */
    private void createControllerJava(String name, String pack, String idType) throws Exception {
        String controllerPath = null;
        if (pack.indexOf(".") > 0) {
            controllerPath = "/" + pack.replaceAll("\\.", "/");
        } else {
            controllerPath = "/" + pack;
        }
        pack = transPack(pack);
        name = transFileName(name, pack);
        String folderPath = transFloderByPack(pack);
        folderPath += "\\controller";
        String filePath = folderPath + "\\" + name + "Controller.java";
        Map<String, String> map = new HashMap<>();
        map.put("classPack", pack);
        map.put("className", name);
        map.put("controllerPath", controllerPath);
        map.put("idType", idType);
        FreemakerUtil.createFileByFreemaker(map, developmentProperties.getControllerFtl(), filePath, folderPath, this.getClass().getClassLoader());
    }

    /**
     * 创建服务层文件
     *
     * @param name
     * @param pack
     */
    private void createServiceJava(String name, String pack, String idType) throws Exception {
        pack = transPack(pack);
        name = transFileName(name, pack);
        String folderPath = transFloderByPack(pack);
        folderPath += "\\service";
        String filePath = folderPath + "\\I" + name + "Service.java";
        Map<String, String> map = new HashMap<>();
        map.put("classPack", pack);
        map.put("className", name);
        map.put("idType", idType);
        FreemakerUtil.createFileByFreemaker(map, developmentProperties.getServiceFtl(), filePath, folderPath, this.getClass().getClassLoader());
    }

    /**
     * 创建服务层实现文件
     *
     * @param name
     * @param pack
     * @param idType
     * @throws Exception
     */
    private void createServiceImplJava(String name, String pack, String idType) throws Exception {
        pack = transPack(pack);
        name = transFileName(name, pack);
        String folderPath = transFloderByPack(pack);
        folderPath += "\\service\\impl";
        String filePath = folderPath + "\\" + name + "ServiceImpl.java";
        Map<String, String> map = new HashMap<>();
        map.put("classPack", pack);
        map.put("className", name);
        map.put("idType", idType);
        FreemakerUtil.createFileByFreemaker(map, developmentProperties.getServiceImplFtl(), filePath, folderPath, this.getClass().getClassLoader());
    }

    /**
     * 转换文件名称
     *
     * @param name
     * @param pack
     * @return
     */
    private String transFileName(String name, String pack) {
        //将第一个字母转换成大写
        String first = name.substring(0, 1);
        name = first.toUpperCase() + name.substring(1, name.length());
        int j = 0;
        String value = null;
        String temValue = null;
        for (int i = name.indexOf("_"); i > 0; i--) {
            if (name.indexOf("_") > 0) {
                value = name.substring(i + 1, i + 2);
                name = name.substring(0, i) + value.toUpperCase() + name.substring(i + 2, name.length());
                i = -1;
                name = transFileName(name, pack);
            }
        }
        return name;
    }

    /**
     * 将报名替换成文件夹路径
     *
     * @param pack
     * @return
     */
    private String transFloderByPack(String pack) {
        String folderPath = "";
        // 将包名替换成文件夹路径
        if (pack.indexOf(".") > 0) {
            folderPath = pack.replaceAll("\\.", "\\\\");
        }
        folderPath = developmentProperties.getJavaPath() + "\\" + folderPath;
        return folderPath;
    }

    /**
     * 转换包名
     *
     * @param pack
     * @return
     */
    private String transPack(String pack) {
        if (pack.indexOf("cn.czcxy.xj") == -1) {
            pack = "cn.czcxy.xj." + pack;
        }
        return pack;
    }

}
