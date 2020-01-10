package cn.czcxy.xj.dispatchclient.base.goods;

import cn.czcxy.xj.basicserver.goods.model.Goods;
import cn.czcxy.xj.basicserver.goods.service.IGoodsClientService;
import cn.czcxy.xj.core.platform.base.annotation.BaseResource;
import cn.czcxy.xj.core.platform.base.controller.BaseCurdClienController;
import cn.czcxy.xj.core.platform.base.model.DataSourceRequest;
import cn.czcxy.xj.core.platform.base.model.DataSourceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/base/goods")
public class GoodsClientController extends BaseCurdClienController<Goods> {

    @Autowired
    @BaseResource
    private IGoodsClientService goodsService;


    /**
     * 通过供应商编码选择产品 多选
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/{vendorId}/multipleSelectGoods", method = RequestMethod.GET)
    public String multipleSelectGoods(Model model, @PathVariable("vendorId") String vendorId) {
        model.addAttribute("vendorId", vendorId);
        return this.view("multipleSelectGoods");
    }

    /**
     * 通过供应商编码选择产品 单选
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/{vendorId}/selectGoods", method = RequestMethod.GET)
    public String selectGoods(Model model, @PathVariable("vendorId") String vendorId) {
        model.addAttribute("vendorId", vendorId);
        return this.view("selectGoods");
    }

    /**
     * 查询产品信息，附带库存 多选
     *
     * @param model
     * @param warehouseId
     * @return
     */
    @RequestMapping(value = "/{warehouseId}/multipleSelectGoodsByStock")
    public String multipleSelectGoodsByStock(Model model, @PathVariable("warehouseId") String warehouseId) {
        model.addAttribute("warehouseId", warehouseId);
        return this.view("multipleSelectGoodsByStock");
    }

    /**
     * 查询产品信息，附带库存 单选
     *
     * @param model
     * @param warehouseId
     * @return
     */
    @RequestMapping(value = "/{warehouseId}/selectGoodsByStock", method = RequestMethod.GET)
    public String selectGoodsByStock(Model model, @PathVariable("warehouseId") String warehouseId) {
        model.addAttribute("warehouseId", warehouseId);
        return this.view("selectGoodsByStock");
    }

    /**
     * 获取供应商可以选择的产品数据,给供应商分配商品的时候使用
     *
     * @param dataSourceRequest
     * @param request
     * @return
     */
    @RequestMapping(value = "selectVendorGoods")
    @ResponseBody
    public DataSourceResult selectVendorGoods(@RequestBody DataSourceRequest dataSourceRequest, HttpServletRequest request, @RequestParam(value = "vendorId", required = true) String vendorId) {
        return goodsService.selectVendorGoods(dataSourceRequest,vendorId);
    }

}
