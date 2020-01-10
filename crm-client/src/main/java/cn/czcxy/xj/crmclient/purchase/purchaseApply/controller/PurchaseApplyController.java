package cn.czcxy.xj.crmclient.purchase.purchaseApply.controller;

import cn.czcxy.xj.core.extra.enums.OrderTypeEnum;
import cn.czcxy.xj.core.platform.base.annotation.BaseResource;
import cn.czcxy.xj.core.platform.base.controller.BaseCurdController;
import cn.czcxy.xj.core.platform.base.model.StatusModel;
import cn.czcxy.xj.core.util.CommonUtil;
import cn.czcxy.xj.crmclient.purchase.purchaseApply.entity.PurchaseApply;
import cn.czcxy.xj.crmclient.purchase.purchaseApply.mapper.PurchaseApplyMapper;
import cn.czcxy.xj.crmclient.purchase.purchaseApply.mapper.PurchaseApplyPartMapper;
import cn.czcxy.xj.crmclient.purchase.purchaseApply.service.IPurchaseApplyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/purchase/purchaseApply")
public class PurchaseApplyController extends BaseCurdController<PurchaseApply, String> {

    @Resource
    @BaseResource
    private IPurchaseApplyService purchaseApplyService;

    @Resource
    private PurchaseApplyMapper purchaseApplyMapper;

    @Resource
    private PurchaseApplyPartMapper purchaseApplyPartMapper;

    /**
     * 保存
     *
     * @param t
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.POST)
    @Override
    public StatusModel create(@RequestBody PurchaseApply t) {
        Map<String, Object> map = new HashMap<>();
        try {
            PurchaseApply purchaseApply = purchaseApplyService.savePurchaseApply(t);
            map.put("purchaseApply", purchaseApply);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
            return new StatusModel(false, e.getMessage());
        }
        return new StatusModel(true, "保存成功!", map);
    }

    /**
     * 获取数据
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}/getDataInfoById", method = RequestMethod.POST)
    @Override
    public StatusModel getDataInfoById(@PathVariable(value = "id") String id) {
        StatusModel statusModel = null;
        try {
            PurchaseApply t = purchaseApplyMapper.findPurchaseApplyById(id);
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

    /**
     * 审核
     *
     * @return
     */
    @RequestMapping(value = "/auditById", method = RequestMethod.POST)
    public StatusModel auditById(@RequestParam("ids") String id) {
        try {
            purchaseApplyService.auditById(id);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
            return new StatusModel(false, e.getMessage());
        }
        return new StatusModel(true, "审核成功!");
    }

    /**
     * 取消审核
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/cancelAuditById", method = RequestMethod.POST)
    public StatusModel cancelAuditById(@RequestParam("ids") String id) {
        try {
            purchaseApplyService.cancelAuditById(id);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
            return new StatusModel(false, e.getMessage());
        }
        return new StatusModel(true, "取消审核成功!");
    }

}
