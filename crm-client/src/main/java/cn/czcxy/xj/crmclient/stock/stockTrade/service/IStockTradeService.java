package cn.czcxy.xj.crmclient.stock.stockTrade.service;


import cn.czcxy.xj.core.platform.base.service.IBaseService;
import cn.czcxy.xj.crmclient.stock.stockBegin.entity.StockBegin;
import cn.czcxy.xj.crmclient.stock.stockTrade.entity.StockTrade;
import cn.czcxy.xj.crmclient.storage.inStorage.entity.InStorageWork;
import cn.czcxy.xj.crmclient.storage.inStorage.entity.InStorageWorkPart;
import cn.czcxy.xj.crmclient.storage.outStorage.entity.OutStorageWork;
import cn.czcxy.xj.crmclient.storage.outStorage.entity.OutStorageWorkPart;

import java.math.BigDecimal;

public interface IStockTradeService extends IBaseService<StockTrade, String> {
    /**
     * 通过库存期初保存库存交易日志
     *
     * @param stockBegin
     * @throws Exception
     */
    void saveStockTradeByStockBegin(StockBegin stockBegin) throws Exception;

    /**
     * 通过入库单进行保存库存交易日志
     *
     * @param storageWork
     * @param part
     * @param receivingAmount
     * @throws Exception
     */
    void saveStockTradeByInStoragePart(InStorageWork storageWork, InStorageWorkPart part, BigDecimal receivingAmount) throws Exception;

    /**
     * 通过出库单进行保存库存交易日志
     *
     * @param outStorageWork
     * @param part
     * @param deliveryAmount
     */
    void saveStockTradeByOutStoragePart(OutStorageWork outStorageWork, OutStorageWorkPart part, BigDecimal deliveryAmount) throws Exception;
}
