package cn.czcxy.xj.basicserver.vendor.service;


import cn.czcxy.xj.basicserver.vendor.model.VendorGoods;
import cn.czcxy.xj.core.platform.base.annotation.BasePath;
import cn.czcxy.xj.core.platform.base.model.StatusModel;
import cn.czcxy.xj.core.platform.base.service.IBaseClientService;
import cn.czcxy.xj.core.platform.feign.FeignConfig;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * 供应商商品服务
 */
@FeignClient(value = "basic-client", configuration = FeignConfig.class)
@BasePath("/vendorGoods")
public interface IVendorGoodsClientService extends IBaseClientService<VendorGoods> {

    /**
     * 保存供应商的产品
     *
     * @param goodsIds
     * @param prices
     * @return
     */
    @RequestMapping(value = "/saveVendorGoods", method = RequestMethod.POST)
    public StatusModel saveVendorGoods(@RequestParam(value = "goodsIds", required = true) String[] goodsIds, @RequestParam(value = "prices", required = true) BigDecimal[] prices,
                                       @RequestParam(value = "vendorId", required = true) String vendorId);
}
