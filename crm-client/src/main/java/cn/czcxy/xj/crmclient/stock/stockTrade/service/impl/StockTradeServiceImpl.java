package cn.czcxy.xj.crmclient.stock.stockTrade.service.impl;

import cn.czcxy.xj.basicserver.goods.model.Goods;
import cn.czcxy.xj.basicserver.goods.service.IGoodsClientService;
import cn.czcxy.xj.core.extra.enums.stock.StockTradeTypeEnum;
import cn.czcxy.xj.core.platform.base.annotation.BaseResource;
import cn.czcxy.xj.core.platform.base.service.impl.BaseServiceImpl;
import cn.czcxy.xj.crmclient.purchase.purchaseOrder.entity.PurchaseOrderPart;
import cn.czcxy.xj.crmclient.purchase.purchaseOrder.mapper.PurchaseOrderPartMapper;
import cn.czcxy.xj.crmclient.sales.entity.SalesOrderPart;
import cn.czcxy.xj.crmclient.sales.mapper.SalesOrderPartMapper;
import cn.czcxy.xj.crmclient.stock.stockBegin.entity.StockBegin;
import cn.czcxy.xj.crmclient.stock.stockTrade.entity.StockTrade;
import cn.czcxy.xj.crmclient.stock.stockTrade.mapper.StockTradeMapper;
import cn.czcxy.xj.crmclient.stock.stockTrade.service.IStockTradeService;
import cn.czcxy.xj.crmclient.storage.inStorage.entity.InStorageWork;
import cn.czcxy.xj.crmclient.storage.inStorage.entity.InStorageWorkPart;
import cn.czcxy.xj.crmclient.storage.outStorage.entity.OutStorageWork;
import cn.czcxy.xj.crmclient.storage.outStorage.entity.OutStorageWorkPart;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

@Transactional(rollbackFor = Exception.class)
@Service("stockTradeLogService")
public class StockTradeServiceImpl extends BaseServiceImpl<StockTrade, String> implements IStockTradeService {

    @Resource
    @BaseResource
    private StockTradeMapper stockTradeLogMapper;

    @Resource
    private IGoodsClientService goodsClientService;

    @Resource
    private PurchaseOrderPartMapper purchaseOrderPartMapper;

    @Resource
    private SalesOrderPartMapper salesOrderPartMapper;

    @Override
    public void saveStockTradeByOutStoragePart(OutStorageWork storageWork, OutStorageWorkPart part, BigDecimal deliveryAmount) throws Exception{
        Goods goods = goodsClientService.selectById(part.getGoodsId());
        SalesOrderPart orderPart = salesOrderPartMapper.selectByPrimaryKey(part.getFromOrderPart());
        saveStockTrade(StockTradeTypeEnum.OUTSTOCK.getCode(), goods, storageWork.getId(), storageWork.getWarehouseId(), storageWork.getWarehouseName(),
                BigDecimal.ZERO.subtract(deliveryAmount), orderPart.getUnitPrice(), orderPart.getUnitPriceRate(), orderPart.getTotalPrice(), orderPart.getTotalPriceRate());
    }

    @Override
    public void saveStockTradeByInStoragePart(InStorageWork storageWork, InStorageWorkPart part, BigDecimal receivingAmount) throws Exception {
        Goods goods = goodsClientService.selectById(part.getGoodsId());
        PurchaseOrderPart orderPart = purchaseOrderPartMapper.selectByPrimaryKey(part.getFromOrderPart());
        saveStockTrade(StockTradeTypeEnum.INSTOCK.getCode(), goods, storageWork.getId(), storageWork.getWarehouseId(), storageWork.getWarehouseName(),
                receivingAmount, orderPart.getUnitPrice(), orderPart.getUnitPriceRate(), orderPart.getTotalPrice(), orderPart.getTotalPriceRate());
    }

    @Override
    public void saveStockTradeByStockBegin(StockBegin stockBegin) throws Exception {
        Goods goods = goodsClientService.selectById(stockBegin.getGoodsId());
        saveStockTrade(StockTradeTypeEnum.STOCKBEGIN.getCode(), goods, stockBegin.getId(), stockBegin.getWarehouseId(), stockBegin.getWarehouseName(),
                stockBegin.getStockAmount(), stockBegin.getUnitPrice(), stockBegin.getUnitPriceRate(), stockBegin.getTotalPrice(), stockBegin.getTotalPriceRate());
    }

    /**
     * 保存交易日志
     *
     * @param tradeType      交易类型
     * @param goods          产品
     * @param formOrder      来源单号
     * @param warehouseId    仓库编码
     * @param warehouseName  仓库名称
     * @param stockAmount    库存
     * @param unitPrice      单价
     * @param unitPriceRate  含税单价
     * @param totalPrice     总金额
     * @param totalPriceRate 含税总金额
     * @throws Exception
     */
    private void saveStockTrade(String tradeType, Goods goods, String formOrder, String warehouseId, String warehouseName, BigDecimal stockAmount,
                                BigDecimal unitPrice, BigDecimal unitPriceRate, BigDecimal totalPrice, BigDecimal totalPriceRate) throws Exception {
        StockTrade stockTrade = new StockTrade();
        stockTrade.setTradeTime(new Date());
        stockTrade.setTradeType(tradeType);
        stockTrade.setWarehouseId(warehouseId);
        stockTrade.setWarehouseName(warehouseName);
        stockTrade.setGoodsId(goods.getId());
        stockTrade.setGoodsName(goods.getGoodsName());
        stockTrade.setGoodsModel(goods.getGoodsModel());
        stockTrade.setGoodsUnitId(goods.getGoodsUnit());
        stockTrade.setGoodsUnitName(goods.getGoodsUnitName());
        stockTrade.setFormOrder(formOrder);
        stockTrade.setTradeAmount(stockAmount);
        stockTrade.setUnitPrice(unitPrice);
        stockTrade.setUnitPriceRate(unitPriceRate);
        stockTrade.setTotalPrice(totalPrice);
        stockTrade.setTotalPriceRate(totalPriceRate);
        super.save(stockTrade);
    }
}