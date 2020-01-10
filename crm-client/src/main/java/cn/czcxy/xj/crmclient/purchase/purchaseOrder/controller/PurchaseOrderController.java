package cn.czcxy.xj.crmclient.purchase.purchaseOrder.controller;

import cn.czcxy.xj.core.extra.enums.OrderTypeEnum;
import cn.czcxy.xj.core.platform.base.annotation.BaseResource;
import cn.czcxy.xj.core.platform.base.controller.BaseCurdController;
import cn.czcxy.xj.core.platform.base.model.StatusModel;
import cn.czcxy.xj.core.util.CommonUtil;
import cn.czcxy.xj.crmclient.purchase.purchaseOrder.entity.PurchaseOrder;
import cn.czcxy.xj.crmclient.purchase.purchaseOrder.mapper.PurchaseOrderMapper;
import cn.czcxy.xj.crmclient.purchase.purchaseOrder.service.IPurchaseOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/purchase/purchaseOrder")
public class PurchaseOrderController extends BaseCurdController<PurchaseOrder, String> {

    @Resource
    @BaseResource
    private IPurchaseOrderService purchaseOrderService;

    @Resource
    private PurchaseOrderMapper purchaseOrderMapper;

    /**
     * 通过采购申请单获取采购订单
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "getPurchaseOrder", method = RequestMethod.POST)
    public StatusModel getPurchaseOrder(@RequestParam("id") String id) {
        Map<String, Object> map = new HashMap<>();
        try {
            PurchaseOrder order = purchaseOrderService.buidPurchaseOrderByApplyId(id);
            map.put("modelData", order);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
            return new StatusModel(false, e.getMessage(), map);
        }
        return new StatusModel(true, "获取成功", map);
    }

    /**
     * 通过下推采购申请保存采购订单
     *
     * @return
     */
    @RequestMapping(value = "savePurchaseOrderByApplyPush", method = RequestMethod.POST)
    public StatusModel savePurchaseOrderByApplyPush(@RequestBody PurchaseOrder purchaseOrder) {
        try {
            purchaseOrderService.savePurchaseOrderByApplyPush(purchaseOrder);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
            return new StatusModel(false, e.getMessage());
        }
        return new StatusModel(true, "下推采购订单成功！");
    }


    /**
     * 保存
     *
     * @param t
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.POST)
    @Override
    public StatusModel create(@RequestBody PurchaseOrder t) {
        Map<String, Object> map = new HashMap<>();
        try {
            PurchaseOrder purchaseOrder = purchaseOrderService.savePurchaseOrder(t);
            map.put("purchaseOrder", purchaseOrder);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
            return new StatusModel(false, e.getMessage());
        }
        return new StatusModel(true, "保存成功!", map);
    }

    /**
     * 审核
     *
     * @return
     */
    @RequestMapping(value = "/auditById", method = RequestMethod.POST)
    public StatusModel auditById(@RequestParam("ids") String id) {
        try {
            purchaseOrderService.auditById(id);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
            return new StatusModel(false, e.getMessage());
        }
        return new StatusModel(true, "审核成功!");
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
        StatusModel statusModel = null;
        try {
            PurchaseOrder t = purchaseOrderMapper.findPurchaseOrderById(id);
            Map<String, Object> map = new HashMap<>();
            map.put("modelData", t);
            statusModel = new StatusModel(true, map);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
            statusModel = new StatusModel(false, e.getMessage());
        }
        return statusModel;
    }
}
