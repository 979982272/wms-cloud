package cn.czcxy.xj.basicserver.goods.service;


import cn.czcxy.xj.basicserver.goods.model.Goods;
import cn.czcxy.xj.core.platform.base.annotation.BasePath;
import cn.czcxy.xj.core.platform.base.model.DataSourceRequest;
import cn.czcxy.xj.core.platform.base.model.DataSourceResult;
import cn.czcxy.xj.core.platform.base.service.IBaseClientService;
import cn.czcxy.xj.core.platform.feign.FeignConfig;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(value = "basic-client", configuration = FeignConfig.class)
@BasePath("/goods")
public interface IGoodsClientService extends IBaseClientService<Goods> {

    /**
     * 获取供应商可以选择的产品数据,给供应商分配商品的时候使用
     *
     * @param dataSourceRequest
     * @param
     * @return
     */
    @RequestMapping(value = "selectVendorGoods")
    public DataSourceResult selectVendorGoods(@RequestBody DataSourceRequest dataSourceRequest, @RequestParam(value = "vendorId", required = true) String vendorId);

    /**
     * 查询多个商品
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "findGoodsByIds", method = RequestMethod.POST)
    public List<Goods> findGoodsByIds(@RequestParam("ids") String[] ids);
}
