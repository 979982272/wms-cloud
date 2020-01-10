package cn.czcxy.xj.crmclient.storage.outStorage.service;


import cn.czcxy.xj.core.platform.base.service.IBaseService;
import cn.czcxy.xj.crmclient.sales.entity.SalesOrder;
import cn.czcxy.xj.crmclient.storage.outStorage.entity.OutStorageWork;

public interface IOutStorageWorkService extends IBaseService<OutStorageWork,String> {

    /**
     * 创建出库单
     * @param salesOrder
     * @throws Exception
     */
    void buidOutStorageWorkBySalesOrder(SalesOrder salesOrder)throws Exception;

    /**
     * 整单出库
     * @param id
     * @throws Exception
     */
    void outStorageByStorageId(String id)throws Exception;

    /**
     * 修改出库单状态
     * @param storageWork
     * @throws Exception
     */
    public void updateOutStorageWorkStatus(OutStorageWork storageWork) throws Exception;
}
