package cn.czcxy.xj.basicclient.warehouse.mapper;

import cn.czcxy.xj.basicclient.warehouse.entity.Warehouse;
import cn.czcxy.xj.core.platform.base.mapper.CustomizeMapper;
import org.apache.ibatis.annotations.Select;

public interface WarehouseMapper extends CustomizeMapper<Warehouse> {
    /**
     * 通过仓库编码查询仓库名称
     *
     * @param warehouseId
     * @return
     */
    @Select("select warehouse_name from eidp_warehouse where id = #{warehouseId}")
    String findWarehouseNameById(String warehouseId);
}