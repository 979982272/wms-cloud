package cn.czcxy.xj.crmclient.sales.mapper;

import cn.czcxy.xj.core.platform.base.mapper.CustomizeMapper;
import cn.czcxy.xj.crmclient.sales.entity.SalesOrderPart;

import java.util.List;

public interface SalesOrderPartMapper extends CustomizeMapper<SalesOrderPart> {
    /**
     * 通过销售单号查询明细
     *
     * @param id
     * @return
     */
    List<SalesOrderPart> findSalesOrderPartBySalesOrderId(String id);

    /**
     * 通过明细查询整单明细
     *
     * @param id
     * @return
     */
    List<SalesOrderPart> findSalesOrderPartBySalesOrderPartId(String id);
}