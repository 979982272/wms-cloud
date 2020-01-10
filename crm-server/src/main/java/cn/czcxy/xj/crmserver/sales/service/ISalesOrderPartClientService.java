package cn.czcxy.xj.crmserver.sales.service;


import cn.czcxy.xj.core.platform.base.annotation.BasePath;
import cn.czcxy.xj.core.platform.base.model.DataSourceRequest;
import cn.czcxy.xj.core.platform.base.model.DataSourceResult;
import cn.czcxy.xj.core.platform.base.service.IBaseClientService;
import cn.czcxy.xj.core.platform.feign.FeignConfig;
import cn.czcxy.xj.crmserver.sales.model.SalesOrderPart;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@FeignClient(value = "crm-client", configuration = FeignConfig.class)
@BasePath("/sales/salesOrderPart")
public interface ISalesOrderPartClientService extends IBaseClientService<SalesOrderPart> {

    /**
     * 载入明细数据
     *
     * @param dataSourceRequest
     * @return
     */
    @RequestMapping(value = "loadDetailData")
    public DataSourceResult loadDetailData(@RequestBody DataSourceRequest dataSourceRequest) ;
}
