package cn.czcxy.xj.basicclient.warehouse.controller;

import cn.czcxy.xj.basicclient.warehouse.entity.Warehouse;
import cn.czcxy.xj.basicclient.warehouse.mapper.WarehouseMapper;
import cn.czcxy.xj.basicclient.warehouse.service.IWarehouseService;
import cn.czcxy.xj.core.platform.base.annotation.BaseResource;
import cn.czcxy.xj.core.platform.base.controller.BaseCurdController;
import cn.czcxy.xj.core.platform.base.model.ComboModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/warehouse")
@Api("仓库服务")
public class WarehouseController extends BaseCurdController<Warehouse,String> {

    @Resource
    @BaseResource
    private IWarehouseService warehouseService;

    @Resource
    private WarehouseMapper warehouseMapper;

    /**
     * 查询仓库下拉数据源
     *
     * @return
     */
    @RequestMapping(value = "findWarehouseCombo", method = RequestMethod.POST)
    @ApiOperation(value = "查询仓库下拉数据源", notes = "查询仓库下拉数据源")
    public List<ComboModel> findWarehouseCombo() {
        List<ComboModel> list = new ArrayList<>();
        List<Warehouse> warehouses = warehouseService.selectAll();
        String[][] array = new String[warehouses.size() + 1][warehouses.size() + 1];
        for (int i = 0; i < warehouses.size(); i++) {
            array[i][0] = warehouses.get(i).getId();
            array[i][1] = warehouses.get(i).getWarehouseName();
        }
        return ComboModel.convert(array, true);
    }

    /**
     * 通过仓库编码查询名称
     *
     * @param warehouseId
     * @return
     */
    @RequestMapping(value = "/{warehouseId}/findWarehouseNameById", method = RequestMethod.GET)
    @ApiOperation(value = "通过仓库编码查询名称", notes = "通过仓库编码查询名称")
    @ApiImplicitParam(value = "warehouseId",name = "仓库编码")
    public String findWarehouseNameById(@PathVariable("warehouseId") String warehouseId) {
        return warehouseMapper.findWarehouseNameById(warehouseId);
    }

}
