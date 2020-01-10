package cn.czcxy.xj.crmserver.stock.stockBegin.service;



import cn.czcxy.xj.core.platform.base.annotation.BasePath;
import cn.czcxy.xj.core.platform.base.model.StatusModel;
import cn.czcxy.xj.core.platform.base.service.IBaseClientService;
import cn.czcxy.xj.core.platform.feign.FeignConfig;
import cn.czcxy.xj.crmserver.stock.stockBegin.model.StockBegin;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;


@FeignClient(value = "crm-client", configuration = FeignConfig.class)
@BasePath("/stock/stockBegin")
public interface IStockBeginClientService extends IBaseClientService<StockBegin> {

    /**
     * 保存期初库存
     *
     * @param goodsIds
     * @param goodsPrice
     * @param stockAmounts
     * @return
     */
    @RequestMapping(value = "/saveStockBegin", method = RequestMethod.POST)
    public StatusModel saveStockBegin(@RequestParam(value = "goodsIds", required = true) String[] goodsIds, @RequestParam(value = "goodsPrice", required = true) BigDecimal[] goodsPrice,
                                      @RequestParam(value = "stockAmounts", required = true) BigDecimal[] stockAmounts, @RequestParam(value = "warehouseId", required = false) String warehouseId);

    /**
     * 审核
     *
     * @return
     */
    @RequestMapping(value = "/auditStockBegin", method = RequestMethod.POST)
    public StatusModel auditStockBegin(@RequestParam("ids") String id) ;
}