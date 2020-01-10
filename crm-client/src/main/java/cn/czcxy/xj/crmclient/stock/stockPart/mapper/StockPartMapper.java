package cn.czcxy.xj.crmclient.stock.stockPart.mapper;

import cn.czcxy.xj.core.platform.base.mapper.CustomizeMapper;
import cn.czcxy.xj.crmclient.stock.stockPart.entity.StockPart;
import org.apache.ibatis.annotations.Param;

public interface StockPartMapper extends CustomizeMapper<StockPart> {
    /**
     * 通过仓库编码和产品编码查询库存
     * @param warehouseId
     * @param goodsId
     * @return
     */
    StockPart findStockPartByWarehouseIdAndGoodsId(@Param("warehouseId") String warehouseId, @Param("goodsId") String goodsId);
}