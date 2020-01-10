package cn.czcxy.xj.basicclient.goods.service;


import cn.czcxy.xj.basicclient.goods.entity.Goods;
import cn.czcxy.xj.core.platform.base.model.DataSourceRequest;
import cn.czcxy.xj.core.platform.base.model.DataSourceResult;
import cn.czcxy.xj.core.platform.base.service.IBaseService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface IGoodsService extends IBaseService<Goods, String> {

    /**
     * 获取供应商可选择的产品
     *
     * @param dataSourceRequest
     * @param request
     * @return
     * @throws Exception
     */
    DataSourceResult selectVendorGoods(DataSourceRequest dataSourceRequest, HttpServletRequest request, String vendorId) throws Exception;

    /**
     * 查询所有产品的集合
     *
     * @return
     * @throws Exception
     */
    Map<String, Goods> findAllGoodsMap() throws Exception;
}
