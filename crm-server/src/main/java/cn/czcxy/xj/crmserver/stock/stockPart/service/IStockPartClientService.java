package cn.czcxy.xj.crmserver.stock.stockPart.service;


import cn.czcxy.xj.core.platform.base.annotation.BasePath;
import cn.czcxy.xj.core.platform.base.model.DataSourceRequest;
import cn.czcxy.xj.core.platform.base.model.DataSourceResult;
import cn.czcxy.xj.core.platform.base.service.IBaseClientService;
import cn.czcxy.xj.core.platform.feign.FeignConfig;
import cn.czcxy.xj.crmserver.stock.stockPart.model.StockPart;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "crm-client", configuration = FeignConfig.class)
@BasePath("/stock/stockPart")
public interface IStockPartClientService extends IBaseClientService<StockPart> {
    @RequestMapping(value = "loadSelectGoodsData")
    public DataSourceResult loadSelectGoodsData(@RequestBody DataSourceRequest dataSourceRequest);
}
