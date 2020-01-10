package cn.czcxy.xj.basicserver.vendor.service;


import cn.czcxy.xj.basicserver.vendor.model.Vendor;
import cn.czcxy.xj.core.platform.base.annotation.BasePath;
import cn.czcxy.xj.core.platform.base.model.ComboModel;
import cn.czcxy.xj.core.platform.base.service.IBaseClientService;
import cn.czcxy.xj.core.platform.feign.FeignConfig;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 供应商服务
 */
@FeignClient(value = "basic-client", configuration = FeignConfig.class)
@BasePath("/vendor")
public interface IVendorClientService extends IBaseClientService<Vendor> {

    /**
     * 查询仓库下拉数据源
     *
     * @return
     */
    @RequestMapping(value = "findVendorCombo", method = RequestMethod.POST)
    public List<ComboModel> findVendorCombo();

}
