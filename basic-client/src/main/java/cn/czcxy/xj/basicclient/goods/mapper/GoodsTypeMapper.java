package cn.czcxy.xj.basicclient.goods.mapper;

import cn.czcxy.xj.basicclient.goods.entity.GoodsType;
import cn.czcxy.xj.core.platform.base.mapper.CustomizeMapper;

import java.util.List;

public interface GoodsTypeMapper extends CustomizeMapper<GoodsType> {
    List<GoodsType> findAllGoodsTypeByIdIsNull();
}