package cn.czcxy.xj.crmclient.stock.stockBegin.controller;

import cn.czcxy.xj.core.platform.base.annotation.BaseResource;
import cn.czcxy.xj.core.platform.base.controller.BaseCurdController;
import cn.czcxy.xj.core.platform.base.model.StatusModel;
import cn.czcxy.xj.crmclient.stock.stockBegin.entity.StockBegin;
import cn.czcxy.xj.crmclient.stock.stockBegin.service.IStockBeginService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;

@RestController
@RequestMapping("/stock/stockBegin")
public class StockBeginController extends BaseCurdController<StockBegin,String> {

    @Resource
    @BaseResource
    private IStockBeginService stockBeginService;

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
                                      @RequestParam(value = "stockAmounts", required = true) BigDecimal[] stockAmounts, @RequestParam(value = "warehouseId", required = false) String warehouseId) {
        try {
            stockBeginService.saveStockBegin(goodsIds, goodsPrice, stockAmounts, warehouseId);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
            return new StatusModel(false, e.getMessage());
        }
        return new StatusModel(true, "期初库存创建成功!");
    }


    /**
     * 审核
     *
     * @return
     */
    @RequestMapping(value = "/auditStockBegin", method = RequestMethod.POST)
    public StatusModel auditStockBegin(@RequestParam("ids") String id) {
        try {
            stockBeginService.auditStockBegin(id.split(","));
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
            return new StatusModel(false, e.getMessage());
        }
        return new StatusModel(true, "审核成功");
    }

}
