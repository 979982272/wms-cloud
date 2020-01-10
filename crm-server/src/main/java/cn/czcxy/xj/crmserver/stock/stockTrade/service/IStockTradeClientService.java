package cn.czcxy.xj.crmserver.stock.stockTrade.service;



import cn.czcxy.xj.core.platform.base.annotation.BasePath;
import cn.czcxy.xj.core.platform.base.service.IBaseClientService;
import cn.czcxy.xj.core.platform.feign.FeignConfig;
import cn.czcxy.xj.crmserver.stock.stockTrade.model.StockTrade;
import org.springframework.cloud.netflix.feign.FeignClient;


@FeignClient(value = "crm-client", configuration = FeignConfig.class)
@BasePath("/stock/stockTrade")
public interface IStockTradeClientService extends IBaseClientService<StockTrade> {
}
