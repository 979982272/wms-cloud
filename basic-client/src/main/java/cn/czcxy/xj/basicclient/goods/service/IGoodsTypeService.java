package cn.czcxy.xj.basicclient.goods.service;


import cn.czcxy.xj.basicclient.goods.entity.GoodsType;
import cn.czcxy.xj.core.platform.base.service.IBaseService;

import java.util.List;

public interface IGoodsTypeService extends IBaseService<GoodsType,String> {
    List<GoodsType> loadGoodsTypeDate(String id);
}
