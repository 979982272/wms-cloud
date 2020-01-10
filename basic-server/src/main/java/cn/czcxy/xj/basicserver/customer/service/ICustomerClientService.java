package cn.czcxy.xj.basicserver.customer.service;

import cn.czcxy.xj.basicserver.customer.model.Customer;
import cn.czcxy.xj.core.platform.base.annotation.BasePath;
import cn.czcxy.xj.core.platform.base.model.ComboModel;
import cn.czcxy.xj.core.platform.base.service.IBaseClientService;
import cn.czcxy.xj.core.platform.feign.FeignConfig;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "basic-client", configuration = FeignConfig.class)
@BasePath("/customer")
public interface ICustomerClientService extends IBaseClientService<Customer> {

    /**
     * 查询仓库下拉数据源
     *
     * @return
     */
    @RequestMapping(value = "findCustomerCombo", method = RequestMethod.POST)
    public List<ComboModel> findWarehouseCombo() ;

}
