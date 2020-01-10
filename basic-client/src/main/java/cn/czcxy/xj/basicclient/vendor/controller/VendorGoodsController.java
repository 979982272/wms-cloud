package cn.czcxy.xj.basicclient.vendor.controller;

import cn.czcxy.xj.basicclient.vendor.entity.VendorGoods;
import cn.czcxy.xj.basicclient.vendor.service.IVendorGoodsService;
import cn.czcxy.xj.core.platform.base.annotation.BaseResource;
import cn.czcxy.xj.core.platform.base.controller.BaseCurdController;
import cn.czcxy.xj.core.platform.base.model.StatusModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

@RestController
@RequestMapping("/vendorGoods")
@Api("供应商对应的商品服务")
public class VendorGoodsController extends BaseCurdController<VendorGoods,String> {

    @Resource
    @BaseResource
    private IVendorGoodsService vendorGoodsService;


    /**
     * 保存供应商的产品
     *
     * @param goodsIds
     * @param prices
     * @return
     */
    @RequestMapping(value = "/saveVendorGoods", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "保存供应商的产品", notes = "保存供应商的产品")
    @ApiImplicitParams({@ApiImplicitParam(value = "goodsIds", name = "商品编码"),
            @ApiImplicitParam(value = "prices", name = "商品价格"),
            @ApiImplicitParam(value = "vendorId", name = "供应商编码")})
    public StatusModel saveVendorGoods(@RequestParam(value = "goodsIds", required = true) String[] goodsIds, @RequestParam(value = "prices", required = true) BigDecimal[] prices,
                                       @RequestParam(value = "vendorId", required = true) String vendorId) {
        try {
            vendorGoodsService.saveVendorGoods(goodsIds, prices, vendorId);
            return new StatusModel(true, "保存成功!");
        } catch (Exception e) {
            return new StatusModel(false, e.getMessage());
        }
    }
}
