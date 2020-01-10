package cn.czcxy.xj.crmclient.sales.service;


import cn.czcxy.xj.core.platform.base.service.IBaseService;
import cn.czcxy.xj.crmclient.sales.entity.SalesOrderPart;

import java.util.List;

public interface ISalesOrderPartService extends IBaseService<SalesOrderPart, String> {

    /**
     * 保存销售订单明细
     *
     * @param salesOrderParts
     * @param id
     * @throws Exception
     */
    void saveSalesOrderPart(List<SalesOrderPart> salesOrderParts, String id) throws Exception;
}
