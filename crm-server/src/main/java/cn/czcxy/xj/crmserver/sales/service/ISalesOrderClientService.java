package cn.czcxy.xj.crmserver.sales.service;


import cn.czcxy.xj.core.platform.base.annotation.BasePath;
import cn.czcxy.xj.core.platform.base.model.StatusModel;
import cn.czcxy.xj.core.platform.base.service.IBaseClientService;
import cn.czcxy.xj.core.platform.feign.FeignConfig;
import cn.czcxy.xj.crmserver.sales.model.SalesOrder;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(value = "crm-client", configuration = FeignConfig.class)
@BasePath("/sales/salesOrder")
public interface ISalesOrderClientService extends IBaseClientService<SalesOrder> {

    /**
     * 审核
     *
     * @return
     */
    @RequestMapping(value = "/{id}/auditById", method = RequestMethod.POST)
    public StatusModel auditById(@PathVariable("id") String id) ;

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}/getDataInfoById", method = RequestMethod.POST)
    @Override
    public StatusModel getDataInfoById(@PathVariable(value = "id") String id) ;
}
