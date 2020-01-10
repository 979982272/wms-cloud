package cn.czcxy.xj.crmclient.stock.stockPart.controller;

import cn.czcxy.xj.core.platform.base.annotation.BaseResource;
import cn.czcxy.xj.core.platform.base.controller.BaseCurdController;
import cn.czcxy.xj.core.platform.base.model.DataSourceRequest;
import cn.czcxy.xj.core.platform.base.model.DataSourceResult;
import cn.czcxy.xj.crmclient.stock.stockPart.entity.StockPart;
import cn.czcxy.xj.crmclient.stock.stockPart.service.IStockPartService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/stock/stockPart")
public class StockPartController extends BaseCurdController<StockPart,String> {

    @Resource
    @BaseResource
    private IStockPartService stockPartService;


    @RequestMapping(value = "loadSelectGoodsData")
    public DataSourceResult loadSelectGoodsData(@RequestBody DataSourceRequest dataSourceRequest, HttpServletRequest request) {
        DataSourceResult dataSourceResult = new DataSourceResult();
        String warehouseId = String.valueOf(dataSourceRequest.getData().get("warehouseId"));
        try {
            dataSourceResult = stockPartService.loadSelectGoodsData(dataSourceRequest, warehouseId);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
        }
        return dataSourceResult;
    }
}
