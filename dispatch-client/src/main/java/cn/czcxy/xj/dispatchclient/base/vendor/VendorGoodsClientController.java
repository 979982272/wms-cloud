package cn.czcxy.xj.dispatchclient.base.vendor;

import cn.czcxy.xj.basicserver.vendor.model.VendorGoods;
import cn.czcxy.xj.basicserver.vendor.service.IVendorGoodsClientService;
import cn.czcxy.xj.core.platform.base.annotation.BaseResource;
import cn.czcxy.xj.core.platform.base.controller.BaseCurdClienController;
import cn.czcxy.xj.core.platform.base.model.StatusModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

@Controller
@RequestMapping("/base/vendorGoods")
public class VendorGoodsClientController extends BaseCurdClienController<VendorGoods> {

    @Resource
    @BaseResource
    private IVendorGoodsClientService vendorGoodsService;

    @RequestMapping(method = RequestMethod.GET)
    @Override
    public String index(Model model, HttpServletRequest request) {
        model.addAttribute("vendorId", request.getParameter("vendorId"));
        return this.view("index");
    }

    /**
     * 保存供应商的产品
     *
     * @param goodsIds
     * @param prices
     * @return
     */
    @RequestMapping(value = "/saveVendorGoods", method = RequestMethod.POST)
    @ResponseBody
    public StatusModel saveVendorGoods(@RequestParam(value = "goodsIds", required = true) String[] goodsIds, @RequestParam(value = "prices", required = true) BigDecimal[] prices,
                                       @RequestParam(value = "vendorId", required = true) String vendorId) {
        return vendorGoodsService.saveVendorGoods(goodsIds, prices, vendorId);
    }
}
