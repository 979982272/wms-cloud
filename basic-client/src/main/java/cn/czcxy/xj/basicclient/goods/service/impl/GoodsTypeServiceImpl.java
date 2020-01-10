package cn.czcxy.xj.basicclient.goods.service.impl;

import cn.czcxy.xj.basicclient.goods.entity.GoodsType;
import cn.czcxy.xj.basicclient.goods.mapper.GoodsTypeMapper;
import cn.czcxy.xj.basicclient.goods.service.IGoodsTypeService;
import cn.czcxy.xj.core.platform.base.annotation.BaseResource;
import cn.czcxy.xj.core.platform.base.model.DataSourceRequest;
import cn.czcxy.xj.core.platform.base.model.DataSourceResult;
import cn.czcxy.xj.core.platform.base.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Transactional(rollbackFor = Exception.class)
@Service("baseGoodsTypeService")
public class GoodsTypeServiceImpl extends BaseServiceImpl<GoodsType, String> implements IGoodsTypeService {

    @Resource
    @BaseResource
    private GoodsTypeMapper baseGoodsTypeMapper;

    @Override
    public List<GoodsType> loadGoodsTypeDate(String id) {
        return null;
    }

    @Override
    public DataSourceResult loadData(Class c, DataSourceRequest dataSourceRequest) {
        List<GoodsType> goodsTypes = baseGoodsTypeMapper.findAllGoodsTypeByIdIsNull();
        DataSourceResult dataSourceResult = new DataSourceResult();
        dataSourceResult.setData(goodsTypes);
        dataSourceResult.setTotal(goodsTypes.size());
        return dataSourceResult;
    }
}