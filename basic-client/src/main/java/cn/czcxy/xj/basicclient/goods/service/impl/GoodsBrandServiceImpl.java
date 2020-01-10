package cn.czcxy.xj.basicclient.goods.service.impl;

import cn.czcxy.xj.basicclient.goods.entity.GoodsBrand;
import cn.czcxy.xj.basicclient.goods.mapper.GoodsBrandMapper;
import cn.czcxy.xj.basicclient.goods.service.IGoodsBrandService;
import cn.czcxy.xj.core.platform.base.annotation.BaseResource;
import cn.czcxy.xj.core.platform.base.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Transactional(rollbackFor = Exception.class)
@Service("goodsBrandService")
public class GoodsBrandServiceImpl extends BaseServiceImpl<GoodsBrand,String> implements IGoodsBrandService {

    @Resource
    @BaseResource
    private GoodsBrandMapper goodsBrandMapper;

}