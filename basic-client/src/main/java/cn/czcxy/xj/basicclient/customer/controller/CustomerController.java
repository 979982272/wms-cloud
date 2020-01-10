package cn.czcxy.xj.basicclient.customer.controller;

import cn.czcxy.xj.basicclient.customer.entity.Customer;
import cn.czcxy.xj.basicclient.customer.service.ICustomerService;
import cn.czcxy.xj.core.platform.base.annotation.BaseResource;
import cn.czcxy.xj.core.platform.base.controller.BaseCurdController;
import cn.czcxy.xj.core.platform.base.model.ComboModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customer")
@Api("客户服务")
public class CustomerController extends BaseCurdController<Customer,String> {

    @Resource
    @BaseResource
    private ICustomerService customerService;


    /**
     * 查询仓库下拉数据源
     *
     * @return
     */
    @ApiOperation(value = "查询仓库下拉数据源", notes = "查询仓库下拉数据源")
    @RequestMapping(value = "findCustomerCombo", method = RequestMethod.POST)
    @ResponseBody
    public List<ComboModel> findWarehouseCombo() {
        List<ComboModel> list = new ArrayList<>();
        List<Customer> customers = customerService.selectAll();
        String[][] array = new String[customers.size() + 1][customers.size() + 1];
        for (int i = 0; i < customers.size(); i++) {
            array[i][0] = customers.get(i).getId();
            array[i][1] = customers.get(i).getCustomerName();
        }
        return ComboModel.convert(array, true);
    }
}
