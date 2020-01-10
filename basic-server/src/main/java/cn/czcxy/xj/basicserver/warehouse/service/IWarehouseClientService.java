package cn.czcxy.xj.basicserver.warehouse.service;


import cn.czcxy.xj.basicserver.warehouse.model.Warehouse;
import cn.czcxy.xj.core.platform.base.annotation.BasePath;
import cn.czcxy.xj.core.platform.base.model.ComboModel;
import cn.czcxy.xj.core.platform.base.service.IBaseClientService;
import cn.czcxy.xj.core.platform.feign.FeignConfig;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 仓库服务
 */
@FeignClient(value = "basic-client", configuration = FeignConfig.class)
@BasePath("/warehouse")
public interface IWarehouseClientService extends IBaseClientService<Warehouse> {

    /**
     * 查询仓库下拉数据源
     *
     * @return
     */
    @RequestMapping(value = "findWarehouseCombo", method = RequestMethod.POST)
    public List<ComboModel> findWarehouseCombo();

    /**
     * 通过仓库编码查询名称
     *
     * @param warehouseId
     * @return
     */
    @RequestMapping(value = "/{warehouseId}/findWarehouseNameById", method = RequestMethod.GET)
    public String findWarehouseNameById(@PathVariable("warehouseId") String warehouseId) ;
}
