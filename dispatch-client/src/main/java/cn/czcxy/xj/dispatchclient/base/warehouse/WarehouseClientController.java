package cn.czcxy.xj.dispatchclient.base.warehouse;

import cn.czcxy.xj.basicserver.warehouse.model.Warehouse;
import cn.czcxy.xj.basicserver.warehouse.service.IWarehouseClientService;
import cn.czcxy.xj.core.platform.base.annotation.BaseResource;
import cn.czcxy.xj.core.platform.base.controller.BaseCurdClienController;
import cn.czcxy.xj.core.platform.base.model.ComboModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/base/warehouse")
public class WarehouseClientController extends BaseCurdClienController<Warehouse> {

    @Resource
    @BaseResource
    private IWarehouseClientService warehouseService;


    /**
     * 查询仓库下拉数据源
     *
     * @return
     */
    @RequestMapping(value = "findWarehouseCombo", method = RequestMethod.POST)
    @ResponseBody
    public List<ComboModel> findWarehouseCombo() {
        return warehouseService.findWarehouseCombo();
    }
}
