package cn.czcxy.xj.dispatchclient.crm.stock.stockTrade;

import cn.czcxy.xj.core.platform.base.annotation.BaseResource;
import cn.czcxy.xj.core.platform.base.controller.BaseCurdClienController;
import cn.czcxy.xj.crmserver.stock.stockTrade.model.StockTrade;
import cn.czcxy.xj.crmserver.stock.stockTrade.service.IStockTradeClientService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/crm/stock/stockTrade")
public class StockTradeController extends BaseCurdClienController<StockTrade> {

    @Resource
    @BaseResource
    private IStockTradeClientService stockTradeLogService;

}
