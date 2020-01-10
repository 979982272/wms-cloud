package cn.czcxy.xj.basicserver.common.service;


import cn.czcxy.xj.basicserver.goods.model.Goods;
import cn.czcxy.xj.core.platform.base.annotation.BasePath;
import cn.czcxy.xj.core.platform.base.model.ComboModel;
import cn.czcxy.xj.core.platform.feign.FeignConfig;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * 通用服务接口
 *
 * @author weihua
 * @create 2017-05-15 18:51
 */
@FeignClient(value = "basic-client", configuration = FeignConfig.class)
@BasePath("/common")
public interface ICommonClientService {
    /**
     * 通过包名获取枚举的下拉框数据源
     *
     * @param key
     * @return
     */
    @RequestMapping(value = "/getComboModelDataSource")
    public List<ComboModel> getComboModelDataSource(@RequestParam("key") String key);

    /**
     * 通过id列表查询产品并且转换为map
     *
     * @param goods
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "findGoodsByIdsAndToMap", method = RequestMethod.POST)
    Map<String, Goods> findGoodsByIdsAndToMap(@RequestParam("goods") List<String> goods);
}
