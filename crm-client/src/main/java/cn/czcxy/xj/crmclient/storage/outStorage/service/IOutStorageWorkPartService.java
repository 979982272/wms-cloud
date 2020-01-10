package cn.czcxy.xj.crmclient.storage.outStorage.service;


import cn.czcxy.xj.core.platform.base.service.IBaseService;
import cn.czcxy.xj.crmclient.sales.entity.SalesOrderPart;
import cn.czcxy.xj.crmclient.storage.outStorage.entity.OutStorageWorkPart;

import java.math.BigDecimal;
import java.util.List;

public interface IOutStorageWorkPartService extends IBaseService<OutStorageWorkPart, String> {

    /**
     * 创建出库单明细
     *
     * @param salesOrderParts
     * @param id
     * @throws Exception
     */
    void buidOutStoragePartWorkBySalesOrder(List<SalesOrderPart> salesOrderParts, String id) throws Exception;

    /**
     * 明细出库(整单)
     *
     * @param outStorageWorkParts
     * @throws Exception
     */
    void outStorage(List<OutStorageWorkPart> outStorageWorkParts) throws Exception;

    /**
     * 明细出库
     *
     * @param part
     * @param salesOrderPart
     * @param deliveryAmount
     * @throws Exception
     */
    void outStorage(OutStorageWorkPart part, SalesOrderPart salesOrderPart, BigDecimal deliveryAmount) throws Exception;

    /**
     * 明细出库
     * @param id
     * @param deliveryAmount
     * @throws Exception
     */
    void outStorageByPart(String id, BigDecimal deliveryAmount)throws Exception;
}
