package cn.czcxy.xj.dispatchclient.crm.purchase.purchaseOrder;

import cn.czcxy.xj.core.extra.enums.OrderTypeEnum;
import cn.czcxy.xj.core.platform.base.annotation.BaseResource;
import cn.czcxy.xj.core.platform.base.controller.BaseCurdClienController;
import cn.czcxy.xj.core.platform.base.model.StatusModel;
import cn.czcxy.xj.core.util.CommonUtil;
import cn.czcxy.xj.crmserver.purchase.purchaseOrder.model.PurchaseOrder;
import cn.czcxy.xj.crmserver.purchase.purchaseOrder.service.IPurchaseOrderClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/crm/purchase/purchaseOrder")
public class PurchaseOrderController extends BaseCurdClienController<PurchaseOrder> {

    @Resource
    @BaseResource
    private IPurchaseOrderClientService purchaseOrderService;

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
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        String id = CommonUtil.getIdByCode(OrderTypeEnum.PURCHASEORDER.getCode());
        purchaseOrder.setId(id);
        info.put("modelData", purchaseOrder);
        StatusModel statusModel = new StatusModel(true, info);
        return statusModel;
    }

    /**
     * 获取数据
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}/getDataInfoById", method = RequestMethod.POST)
    @ResponseBody
    @Override
    public StatusModel getDataInfoById(@PathVariable(value = "id") String id) {
        return purchaseOrderService.getDataInfoById(id);
    }

    /**
     * 通过采购申请单获取采购订单
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "getPurchaseOrder", method = RequestMethod.POST)
    @ResponseBody
    public StatusModel getPurchaseOrder(@RequestParam("id") String id) {
        return purchaseOrderService.getPurchaseOrder(id);
    }

    /**
     * 通过下推采购申请保存采购订单
     *
     * @return
     */
    @RequestMapping(value = "savePurchaseOrderByApplyPush", method = RequestMethod.POST)
    @ResponseBody
    public StatusModel savePurchaseOrderByApplyPush(@RequestBody PurchaseOrder purchaseOrder) {
        return purchaseOrderService.savePurchaseOrderByApplyPush(purchaseOrder);
    }

    /**
     * 跳转创建页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String edit(Model model) {
        return this.view("create");
    }

    /**
     * 保存
     *
     * @param t
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    @Override
    public StatusModel create(@RequestBody PurchaseOrder t) {
        return purchaseOrderService.savePurchaseOrder(t);
    }

    /**
     * 审核
     *
     * @return
     */
    @RequestMapping(value = "/auditById", method = RequestMethod.POST)
    @ResponseBody
    public StatusModel auditById(@RequestParam("ids") String id) {
        return purchaseOrderService.auditById(id);
    }

}
