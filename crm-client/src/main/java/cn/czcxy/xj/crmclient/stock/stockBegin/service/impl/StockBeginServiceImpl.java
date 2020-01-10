package cn.czcxy.xj.crmclient.stock.stockBegin.service.impl;

import cn.czcxy.xj.basicserver.goods.model.Goods;
import cn.czcxy.xj.basicserver.goods.service.IGoodsClientService;
import cn.czcxy.xj.basicserver.warehouse.service.IWarehouseClientService;
import cn.czcxy.xj.core.extra.enums.OrderStatusEnum;
import cn.czcxy.xj.core.platform.base.annotation.BaseResource;
import cn.czcxy.xj.core.platform.base.service.impl.BaseServiceImpl;
import cn.czcxy.xj.core.util.ArrayUtil;
import cn.czcxy.xj.core.util.CommonUtil;
import cn.czcxy.xj.core.util.EntityUtil;
import cn.czcxy.xj.core.util.MathUtil;
import cn.czcxy.xj.crmclient.stock.stockBegin.entity.StockBegin;
import cn.czcxy.xj.crmclient.stock.stockBegin.mapper.StockBeginMapper;
import cn.czcxy.xj.crmclient.stock.stockBegin.service.IStockBeginService;
import cn.czcxy.xj.crmclient.stock.stockPart.service.IStockPartService;
import cn.czcxy.xj.crmclient.stock.stockTrade.service.IStockTradeService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional(rollbackFor = Exception.class)
@Service("stockBeginService")
public class StockBeginServiceImpl extends BaseServiceImpl<StockBegin, String> implements IStockBeginService {

    @Resource
    @BaseResource
    private StockBeginMapper stockBeginMapper;

    @Resource
    private IGoodsClientService goodsClientService;

    @Resource
    private IWarehouseClientService warehouseClientService;

    @Resource
    private IStockPartService stockPartService;

    @Resource
    private IStockTradeService stockTradeService;

    @Override
    public void deleteById(String id) throws Exception {
        StockBegin stockBegin = selectById(id);
        if (null == stockBegin) {
            throw new Exception("查询不到对应的库存期初记录！【" + id + "】");
        }
        if (stockBegin.getStatus() > OrderStatusEnum.CREATE.getCode()) {
            throw new Exception("该记录已审核完成无法进行删除操作!");
        }
        stockBeginMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void auditStockBegin(String[] ids) throws Exception {
        for (String id : ids) {
            audit(id);
        }
    }

    /**
     * 审核
     *
     * @param id
     * @throws Exception
     */
    private void audit(String id) throws Exception {
        StockBegin stockBegin = stockBeginMapper.selectByPrimaryKey(id);
        if (null == stockBegin) {
            throw new Exception("查询不到对应的期初库存记录！【" + id + "】");
        }
        stockBegin.setStatus(OrderStatusEnum.AUDIT.getCode());
        EntityUtil.setEntityAuditInfo(stockBegin);
        stockBeginMapper.updateByPrimaryKey(stockBegin);
        //1.生成库存
        stockPartService.saveStockPartByStockBegin(stockBegin);
        //2.生成库存交易日志
        stockTradeService.saveStockTradeByStockBegin(stockBegin);
    }

    @Override
    public void saveStockBegin(String[] goodsIds, BigDecimal[] goodsPrice, BigDecimal[] stockAmounts, String warehouseId) throws Exception {
        // 判断数量是否一致
        if (goodsIds.length != goodsPrice.length || goodsPrice.length != stockAmounts.length || goodsIds.length != stockAmounts.length) {
            throw new Exception("参数不一致!请联系管理员");
        }
        String warehouseName = warehouseClientService.findWarehouseNameById(warehouseId);
        if (StringUtils.isEmpty(warehouseName)) {
            throw new Exception("查询不到对应的仓库！【" + warehouseId + "】");
        }
        List<Goods> goodses = goodsClientService.findGoodsByIds(goodsIds);
        if (CollectionUtils.isEmpty(goodses) || goodses.size() != goodsIds.length) {
            throw new Exception("查询不到对应的产品，或查询出来的产品数量与传递的产品参数不等!");
        }
        Map<String, Goods> goodsMap = listToMap(goodses);
        Goods goods = null;
        StockBegin stockBegin = null;
        for (int i = 0; i < goodsIds.length; i++) {
            goods = goodsMap.get(goodsIds[i]);
            stockBegin = new StockBegin();
            String id = CommonUtil.getIdByCode("SB");
            stockBegin.setId(id);
            stockBegin.setStatus(OrderStatusEnum.CREATE.getCode());
            stockBegin.setWarehouseId(warehouseId);
            stockBegin.setWarehouseName(warehouseName);
            stockBegin.setGoodsId(goods.getId());
            stockBegin.setGoodsName(goods.getGoodsName());
            stockBegin.setGoodsUnitId(goods.getGoodsUnit());
            stockBegin.setGoodsUnitName(goods.getGoodsUnitName());
            stockBegin.setGoodsModel(goods.getGoodsModel());
            stockBegin.setStockAmount(stockAmounts[i]);
            stockBegin.setUnitPrice(goodsPrice[i]);
            stockBegin.setUnitPriceRate(MathUtil.mathRate(stockBegin.getUnitPrice(), goods.getRate()));
            stockBegin.setTotalPrice(stockBegin.getStockAmount().multiply(stockBegin.getUnitPrice()));
            stockBegin.setTotalPriceRate(stockBegin.getStockAmount().multiply(stockBegin.getUnitPriceRate()));
            stockBegin.setRatePrice(stockBegin.getTotalPrice().subtract(stockBegin.getTotalPriceRate()));
            super.save(stockBegin);
        }
    }

    @Override
    public StockBegin update(StockBegin stockBegin) throws Exception {
        Goods goods = goodsClientService.selectById(stockBegin.getGoodsId());
        if (null == goods) {
            throw new Exception("查询不到对应的产品信息!【" + stockBegin.getGoodsId() + "】");
        }
        stockBegin.setUnitPriceRate(MathUtil.mathRate(stockBegin.getUnitPrice(), goods.getRate()));
        stockBegin.setTotalPrice(stockBegin.getStockAmount().multiply(stockBegin.getUnitPrice()));
        stockBegin.setTotalPriceRate(stockBegin.getStockAmount().multiply(stockBegin.getUnitPriceRate()));
        stockBegin.setRatePrice(stockBegin.getTotalPrice().subtract(stockBegin.getTotalPriceRate()));
        super.update(stockBegin);
        return stockBegin;
    }

    /**
     * 将集合转换为map
     *
     * @param goodses
     * @return
     */
    private Map<String, Goods> listToMap(List<Goods> goodses) {
        Map<String, Goods> map = new HashMap<>();
        for (Goods goods : goodses) {
            map.put(goods.getId(), goods);
        }
        return map;
    }
}