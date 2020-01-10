package cn.czcxy.xj.basicserver.development.service;

import cn.czcxy.xj.core.platform.base.annotation.BasePath;
import cn.czcxy.xj.core.platform.base.model.ComboModel;
import cn.czcxy.xj.core.platform.base.model.DataSourceRequest;
import cn.czcxy.xj.core.platform.base.model.DataSourceResult;
import cn.czcxy.xj.core.platform.feign.FeignConfig;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * 开发管理服务
 *
 * @author weihua
 * @create 2017-05-02 22:25
 */
@FeignClient(value = "basic-client", configuration = FeignConfig.class)
@BasePath("/development")
public interface IDevelopmentClientService {

    /**
     * 查询所有数据库表
     *
     * @param dataSourceRequest
     * @return
     */
    @RequestMapping(value = "findDataBaseCombo")
    public List<ComboModel> findDataBaseCombo(@RequestBody DataSourceRequest dataSourceRequest);

    /**
     * 查询表中的所有字段
     *
     * @param table
     * @return
     */
    @RequestMapping(value = "findColumnInfoByTable")
    public DataSourceResult findColumnInfoByTable(@RequestParam(value = "table", required = true) String table);

    /**
     * 查询表的主键信息
     *
     * @param table
     * @return
     */
    @RequestMapping(value = "findPriByTable", method = RequestMethod.POST)
    public Map<String, Object> findPriByTable(@RequestParam(value = "table", required = true) String table);
}
