package cn.czcxy.xj.crmclient.stock.stockTrade.controller;

import cn.czcxy.xj.core.platform.base.annotation.BaseResource;
import cn.czcxy.xj.core.platform.base.controller.BaseCurdController;
import cn.czcxy.xj.crmclient.stock.stockTrade.entity.StockTrade;
import cn.czcxy.xj.crmclient.stock.stockTrade.service.IStockTradeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/stock/stockTrade")
public class StockTradeController extends BaseCurdController<StockTrade,String> {

    @Resource
    @BaseResource
    private IStockTradeService stockTradeLogService;

}
