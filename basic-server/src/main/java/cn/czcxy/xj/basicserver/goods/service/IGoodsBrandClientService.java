package cn.czcxy.xj.basicserver.goods.service;


import cn.czcxy.xj.basicserver.goods.model.GoodsBrand;
import cn.czcxy.xj.core.platform.base.annotation.BasePath;
import cn.czcxy.xj.core.platform.base.model.ComboModel;
import cn.czcxy.xj.core.platform.base.service.IBaseClientService;
import cn.czcxy.xj.core.platform.feign.FeignConfig;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


@FeignClient(value = "basic-client", configuration = FeignConfig.class)
@BasePath("/goodsBrand")
public interface IGoodsBrandClientService extends IBaseClientService<GoodsBrand> {

    /**
     * 产品单位下拉框数据源
     *
     * @return
     */
    @RequestMapping(value = "findCombo", method = RequestMethod.POST)
    public List<ComboModel> findCombo();
}
