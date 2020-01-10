package cn.czcxy.xj.basicclient.vendor.service;


import cn.czcxy.xj.basicclient.vendor.entity.VendorGoods;
import cn.czcxy.xj.core.platform.base.service.IBaseService;

import java.math.BigDecimal;

public interface IVendorGoodsService extends IBaseService<VendorGoods, String> {

    /**
     * 保存供应商
     *
     * @param goodsIds
     * @param prices
     * @param vendorId
     * @throws Exception
     */
    void saveVendorGoods(String[] goodsIds, BigDecimal[] prices, String vendorId) throws Exception;

}
