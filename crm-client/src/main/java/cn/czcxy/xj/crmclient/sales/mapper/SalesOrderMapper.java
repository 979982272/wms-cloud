package cn.czcxy.xj.crmclient.sales.mapper;

import cn.czcxy.xj.core.platform.base.mapper.CustomizeMapper;
import cn.czcxy.xj.crmclient.sales.entity.SalesOrder;
import org.apache.ibatis.annotations.Param;

public interface SalesOrderMapper extends CustomizeMapper<SalesOrder> {
    /**
     * 通过销售单号查询销售订单
     *
     * @param id
     * @return
     */
    SalesOrder findSalesOrderById(String id);

    /**
     * 修改销售单状态
     *
     * @param id
     * @param deliveryStatus
     */
    void updateSalesOrderStatusById(@Param("id") String id, @Param("deliveryStatus") Integer deliveryStatus);
}