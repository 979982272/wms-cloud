package cn.czcxy.xj.dispatchclient.crm.stock.stockBegin;

import cn.czcxy.xj.core.platform.base.annotation.BaseResource;
import cn.czcxy.xj.core.platform.base.controller.BaseCurdClienController;
import cn.czcxy.xj.core.platform.base.model.StatusModel;
import cn.czcxy.xj.crmserver.stock.stockBegin.model.StockBegin;
import cn.czcxy.xj.crmserver.stock.stockBegin.service.IStockBeginClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Controller
@RequestMapping("/crm/stock/stockBegin")
public class StockBeginController extends BaseCurdClienController<StockBegin> {

    @Resource
    @BaseResource
    private IStockBeginClientService stockBeginService;


    /**
     * 选择产品
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/selectGoods", method = RequestMethod.GET)
    public String selectGoods(Model model) {
        return this.view("selectGoods");
    }

    /**
     * 保存期初库存
     *
     * @param goodsIds
     * @param goodsPrice
     * @param stockAmounts
     * @return
     */
    @RequestMapping(value = "/saveStockBegin", method = RequestMethod.POST)
    @ResponseBody
    public StatusModel saveStockBegin(@RequestParam(value = "goodsIds", required = true) String[] goodsIds, @RequestParam(value = "goodsPrice", required = true) BigDecimal[] goodsPrice,
                                      @RequestParam(value = "stockAmounts", required = true) BigDecimal[] stockAmounts, @RequestParam(value = "warehouseId", required = false) String warehouseId) {
        return stockBeginService.saveStockBegin(goodsIds, goodsPrice, stockAmounts, warehouseId);
    }


    /**
     * 审核
     *
     * @return
     */
    @RequestMapping(value = "/auditStockBegin", method = RequestMethod.POST)
    @ResponseBody
    public StatusModel auditStockBegin(@RequestParam("ids") String id) {
        return stockBeginService.auditStockBegin(id);
    }

}
