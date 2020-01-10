package cn.czcxy.xj.dispatchclient.base.customer;

import cn.czcxy.xj.basicserver.customer.model.Customer;
import cn.czcxy.xj.basicserver.customer.service.ICustomerClientService;
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
@RequestMapping("/base/customer")
public class CustomerClientController extends BaseCurdClienController<Customer> {

    @Resource
    @BaseResource
    private ICustomerClientService customerService;


    /**
     * 查询仓库下拉数据源
     *
     * @return
     */
    @RequestMapping(value = "findCustomerCombo", method = RequestMethod.POST)
    @ResponseBody
    public List<ComboModel> findWarehouseCombo() {
        return customerService.findWarehouseCombo();
    }
}
