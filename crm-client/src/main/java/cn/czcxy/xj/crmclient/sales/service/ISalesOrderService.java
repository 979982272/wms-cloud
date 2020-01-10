package cn.czcxy.xj.crmclient.sales.service;


import cn.czcxy.xj.core.platform.base.service.IBaseService;
import cn.czcxy.xj.crmclient.sales.entity.SalesOrder;

public interface ISalesOrderService extends IBaseService<SalesOrder, String> {

    /**
     * 审核
     *
     * @param id
     * @throws Exception
     */
    void auditById(String id) throws Exception;

    /**
     * 更新销售单状态
     *
     * @param fromOrder
     * @throws Exception
     */
    void updateSalesOrderStatus(String fromOrder) throws Exception;
}
