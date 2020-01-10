package cn.czcxy.xj.dispatchclient.crm.sales;

import cn.czcxy.xj.core.extra.enums.OrderTypeEnum;
import cn.czcxy.xj.core.extra.enums.sales.SalesOrderStatusEnum;
import cn.czcxy.xj.core.platform.base.annotation.BaseResource;
import cn.czcxy.xj.core.platform.base.controller.BaseCurdClienController;
import cn.czcxy.xj.core.platform.base.model.StatusModel;
import cn.czcxy.xj.core.util.CommonUtil;
import cn.czcxy.xj.crmserver.sales.model.SalesOrder;
import cn.czcxy.xj.crmserver.sales.service.ISalesOrderClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/crm/sales/salesOrder")
public class SalesOrderController extends BaseCurdClienController<SalesOrder> {

    @Resource
    @BaseResource
    private ISalesOrderClientService salesOrderService;


    /**
     * 初始化
     *
     * @return
     */
    @RequestMapping(value = "/getDefaultInfo", method = RequestMethod.POST)
    @ResponseBody
    @Override
    public StatusModel getDefaultInfo() {
        Map<String, Object> info = new HashMap<>();
        SalesOrder salesOrder = new SalesOrder();
        String id = CommonUtil.getIdByCode(OrderTypeEnum.SALESORDER.getCode());
        salesOrder.setOrderDate(new Date());
        salesOrder.setDeliveryDate(new Date());
        salesOrder.setId(id);
        salesOrder.setStatus(SalesOrderStatusEnum.CREATE.getCode());
        info.put("modelData", salesOrder);
        StatusModel statusModel = new StatusModel(true, info);
        return statusModel;
    }

    /**
     * 创建页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String editView(Model model) {
        return this.view("create");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}/getDataInfoById", method = RequestMethod.POST)
    @ResponseBody
    @Override
    public StatusModel getDataInfoById(@PathVariable(value = "id") String id) {
        return salesOrderService.getDataInfoById(id);
    }

    /**
     * 审核
     *
     * @return
     */
    @RequestMapping(value = "/{id}/auditById", method = RequestMethod.POST)
    @ResponseBody
    public StatusModel auditById(@PathVariable("id") String id) {
        return salesOrderService.auditById(id);
    }
}
