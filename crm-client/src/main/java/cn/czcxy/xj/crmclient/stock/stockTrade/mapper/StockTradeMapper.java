package cn.czcxy.xj.crmclient.stock.stockTrade.mapper;

import cn.czcxy.xj.core.platform.base.mapper.CustomizeMapper;
import cn.czcxy.xj.crmclient.stock.stockTrade.entity.StockTrade;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StockTradeMapper extends CustomizeMapper<StockTrade> {
    /**
     * 通过仓库编码与产品编码查询库存交易日志
     *
     * @param warehouseId
     * @param goodsId
     * @return
     */
    List<StockTrade> findStockTradeByWarehouseIdAndGoodsId(@Param("warehouseId") String warehouseId, @Param("goodsId") String goodsId);
}