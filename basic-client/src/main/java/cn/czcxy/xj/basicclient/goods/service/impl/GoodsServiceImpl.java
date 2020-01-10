package cn.czcxy.xj.basicclient.goods.service.impl;

import cn.czcxy.xj.basicclient.goods.entity.Goods;
import cn.czcxy.xj.basicclient.goods.mapper.GoodsMapper;
import cn.czcxy.xj.basicclient.goods.service.IGoodsService;
import cn.czcxy.xj.core.extra.enums.ServerFlag;
import cn.czcxy.xj.core.extra.system.AuthInfo;
import cn.czcxy.xj.core.extra.system.UserInfoUtil;
import cn.czcxy.xj.core.platform.base.annotation.BaseResource;
import cn.czcxy.xj.core.platform.base.model.DataSourceRequest;
import cn.czcxy.xj.core.platform.base.model.DataSourceResult;
import cn.czcxy.xj.core.platform.base.service.impl.BaseServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional(rollbackFor = Exception.class)
@Service("goodsArchiveService")
public class GoodsServiceImpl extends BaseServiceImpl<Goods, String> implements IGoodsService {

    @Resource
    @BaseResource
    private GoodsMapper goodsMapper;


    @Override
    public Map<String, Goods> findAllGoodsMap() throws Exception {
        List<Goods> goodses = goodsMapper.selectAll();
        Map<String, Goods> goodsMap = new HashMap<>();
        for (Goods goods : goodses) {
            goodsMap.put(goods.getId(), goods);
        }
        return goodsMap;
    }

    @Override
    public DataSourceResult selectVendorGoods(DataSourceRequest dataSourceRequest, HttpServletRequest request, String vendorId) throws Exception {
        DataSourceResult dataSourceResult = new DataSourceResult();
        Map<String, Object> map = new HashMap<>();
        map.put("vendorId", vendorId);
        map.put("dataSourceRequest", dataSourceRequest);
        List<Goods> goods = goodsMapper.findVendorGoodsByVendorId(map);
        dataSourceResult.setData(goods);
        dataSourceResult.setTotal(goods.size());
        return dataSourceResult;
    }

    @Override
    public Goods save(Goods t) throws Exception {
        valide(t);
        if (goodsMapper.selectByPrimaryKey(t) != null) {
            throw new Exception("产品编码重复！【" + t.getId() + "】");
        }
        if (CollectionUtils.isNotEmpty(goodsMapper.findGoodsByGoodsName(t.getGoodsName()))) {
            throw new Exception("产品名称重复！【" + t.getGoodsName() + "】");
        }
        AuthInfo authInfo = UserInfoUtil.getCurrentAuthInfo();
        t.setCreateTime(new Date());
        t.setCreateEmpId(authInfo.getEmpId());
        t.setCreateEmp(authInfo.getEmpName());
        return super.save(t);
    }

    @Override
    public Goods update(Goods t) throws Exception {
        valide(t);
        if (goodsMapper.selectByPrimaryKey(t) == null) {
            throw new Exception("查询不到对应的产品档案！【" + t.getId() + "】");
        }
        if (CollectionUtils.isNotEmpty(goodsMapper.findGoodsByGoodsNameAndNotId(t.getGoodsName(), t.getId()))) {
            throw new Exception("产品名称重复！【" + t.getGoodsName() + "】");
        }
        AuthInfo authInfo = UserInfoUtil.getCurrentAuthInfo();
        t.setModifyTime(new Date());
        t.setModifyEmpId(authInfo.getEmpId());
        t.setModifyEmp(authInfo.getEmpName());
        goodsMapper.updateByPrimaryKeyWithVersion(t);
        return t;
    }

    private void valide(Goods goods) throws Exception {
        if (null == goods) {
            throw new Exception("产品档案信息不能为空！");
        }
        if (StringUtils.isEmpty(goods.getId())) {
            throw new Exception("产品编码不能为空！");
        }
        if (StringUtils.isEmpty(goods.getGoodsName())) {
            throw new Exception("产品名称不能为空！");
        }
        if (StringUtils.isEmpty(goods.getGoodsUnit())) {
            throw new Exception("产品类型不能为空！");
        }
        if (StringUtils.isEmpty(goods.getGoodsBrand())) {
            throw new Exception("产品品牌不能为空！");
        }
        if (StringUtils.isEmpty(goods.getGoodsUnit())) {
            throw new Exception("产品单位不能为空！");
        }
    }
}