package cn.czcxy.xj.crmclient.purchase.purchaseOrder.service.impl;

import cn.czcxy.xj.basicserver.goods.model.Goods;
import cn.czcxy.xj.basicserver.goods.service.IGoodsClientService;
import cn.czcxy.xj.core.platform.base.annotation.BaseResource;
import cn.czcxy.xj.core.platform.base.model.DataSourceRequest;
import cn.czcxy.xj.core.platform.base.model.DataSourceResult;
import cn.czcxy.xj.core.platform.base.service.impl.BaseServiceImpl;
import cn.czcxy.xj.core.util.EntityUtil;
import cn.czcxy.xj.core.util.MathUtil;
import cn.czcxy.xj.crmclient.purchase.purchaseApply.entity.PurchaseApplyPart;
import cn.czcxy.xj.crmclient.purchase.purchaseOrder.entity.PurchaseOrderPart;
import cn.czcxy.xj.crmclient.purchase.purchaseOrder.mapper.PurchaseOrderPartMapper;
import cn.czcxy.xj.crmclient.purchase.purchaseOrder.service.IPurchaseOrderPartService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

@Transactional(rollbackFor = Exception.class)
@Service("purchaseOrderPartService")
public class PurchaseOrderPartServiceImpl extends BaseServiceImpl<PurchaseOrderPart, String> implements IPurchaseOrderPartService {

    @Resource
    @BaseResource
    private PurchaseOrderPartMapper purchaseOrderPartMapper;

    @Resource
    private IGoodsClientService goodsClientService;

    @Override
    public void savePurchaseApplyPart(List<PurchaseOrderPart> purchaseOrderParts, String id) throws Exception {
        if (CollectionUtils.isEmpty(purchaseOrderParts)) {
            throw new Exception("产品信息不能为空！");
        }
        if (StringUtils.isEmpty(id)) {
            throw new Exception("采购订单号不能为空！");
        }
        for (PurchaseOrderPart part : purchaseOrderParts) {
            part.setPurchaseOrderId(id);
            save(part);
        }
    }

    @Override
    public PurchaseOrderPart save(PurchaseOrderPart part) throws Exception {
        if (StringUtils.isEmpty(part.getPurchaseOrderId())) {
            throw new Exception("采购订单号不能为空！");
        }
        Goods goods = goodsClientService.selectById(part.getGoodsId());
        if (null == goods) {
            throw new Exception("查询不到对应的产品!【" + part.getGoodsId() + "】");
        }
        valid(part);
        setData(part, goods);
        Example example = new Example(PurchaseApplyPart.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andCondition("goods_id = ", part.getGoodsId());
        criteria.andCondition("purchase_order_id =", part.getPurchaseOrderId());
        List<PurchaseOrderPart> purchaseOrderParts = purchaseOrderPartMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(purchaseOrderParts)) {
            throw new Exception("采购订单中已存在产品【" + part.getGoodsId() + "】");
        }
        super.save(part);
        return part;
    }

    @Override
    public PurchaseOrderPart update(PurchaseOrderPart part) throws Exception {
        if (StringUtils.isEmpty(part.getPurchaseOrderId())) {
            throw new Exception("采购订单号不能为空！");
        }
        Goods goods = goodsClientService.selectById(part.getGoodsId());
        valid(part);
        if (null == goods) {
            throw new Exception("查询不到对应的产品!【" + part.getGoodsId() + "】");
        }
        setData(part, goods);
        Example example = new Example(PurchaseApplyPart.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andCondition("goods_id = ", part.getGoodsId());
        criteria.andCondition("purchase_order_id =", part.getPurchaseOrderId());
        criteria.andNotEqualTo("id", part.getId());
        List<PurchaseOrderPart> purchaseOrderParts = purchaseOrderPartMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(purchaseOrderParts)) {
            throw new Exception("采购申请单中已存在产品【" + part.getGoodsId() + "】");
        }
        super.update(part);
        return part;
    }

    private void setData(PurchaseOrderPart part, Goods goods) {
        part.setGoodsUnitId(goods.getGoodsUnit());
        part.setGoodsUnitName(goods.getGoodsUnitName());
        part.setGoodsName(goods.getGoodsName());
        part.setGoodsModel(goods.getGoodsModel());
        part.setUnitPriceRate(MathUtil.mathRate(part.getUnitPrice(), part.getRate()));
        part.setTotalPrice(part.getPurchaseAmount().multiply(part.getUnitPrice()));
        part.setTotalPriceRate(part.getPurchaseAmount().multiply(part.getUnitPriceRate()));
    }

    /**
     * 验证采购数量/单位价格
     *
     * @param purchaseOrderPart
     * @throws Exception
     */
    private void valid(PurchaseOrderPart purchaseOrderPart) throws Exception {
        if (null == purchaseOrderPart.getUnitPrice() || BigDecimal.ZERO.compareTo(purchaseOrderPart.getUnitPrice()) >= 0) {
            throw new Exception("采购价格不能为空,并且需要大于零！");
        }
        if (null == purchaseOrderPart.getPurchaseAmount() || BigDecimal.ZERO.compareTo(purchaseOrderPart.getPurchaseAmount()) >= 0) {
            throw new Exception("采购数量不能为空,并且需要大于零！");
        }
        if (null == purchaseOrderPart.getRate()) {
            purchaseOrderPart.setRate(BigDecimal.ZERO);
        }
    }

    @Override
    public DataSourceResult loadData(Class c, DataSourceRequest dataSourceRequest) {
        Example example = new Example(c);
        Page<PurchaseOrderPart> page = PageHelper.startPage(dataSourceRequest.getPage(), dataSourceRequest.getPageSize());
        // 将实体的属性与数据库属性进行映射
        EntityUtil.buidSqlByRequest(dataSourceRequest, example);
        // 执行查询之后会自动把值设置到page中
        Example.Criteria criteria = example.getOredCriteria().get(0);
        criteria.andCondition("receiving_amount >", 0);
        purchaseOrderPartMapper.selectByExample(example);
        DataSourceResult dataSourceResult = new DataSourceResult();
        dataSourceResult.setData(page);
        dataSourceResult.setTotal(page.getTotal());
        return dataSourceResult;
    }

    @Override
    public void deletePurchaseOrderPartByOrderId(String orderId) throws Exception {
        purchaseOrderPartMapper.deletePurchaseOrderPartByOrderId(orderId);
    }
}