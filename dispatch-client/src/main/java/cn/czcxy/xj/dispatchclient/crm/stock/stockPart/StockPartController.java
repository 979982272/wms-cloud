package cn.czcxy.xj.dispatchclient.crm.stock.stockPart;

import cn.czcxy.xj.core.platform.base.annotation.BaseResource;
import cn.czcxy.xj.core.platform.base.controller.BaseCurdClienController;
import cn.czcxy.xj.core.platform.base.model.DataSourceRequest;
import cn.czcxy.xj.core.platform.base.model.DataSourceResult;
import cn.czcxy.xj.core.util.HttpServletRequestUtils;
import cn.czcxy.xj.crmserver.stock.stockPart.model.StockPart;
import cn.czcxy.xj.crmserver.stock.stockPart.service.IStockPartClientService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/crm/stock/stockPart")
public class StockPartController extends BaseCurdClienController<StockPart> {

    @Resource
    @BaseResource
    private IStockPartClientService stockPartService;


    @RequestMapping(value = "loadSelectGoodsData")
    @ResponseBody
    public DataSourceResult loadSelectGoodsData(@RequestBody DataSourceRequest dataSourceRequest, HttpServletRequest request) {
        HttpServletRequestUtils.transRequestToDataSourceRequest(dataSourceRequest,request);
        return stockPartService.loadSelectGoodsData(dataSourceRequest);
    }
}
