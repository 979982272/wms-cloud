package cn.czcxy.xj.dispatchclient.crm.purchase.purchaseApply;

import cn.czcxy.xj.core.extra.enums.OrderTypeEnum;
import cn.czcxy.xj.core.platform.base.annotation.BaseResource;
import cn.czcxy.xj.core.platform.base.controller.BaseCurdClienController;
import cn.czcxy.xj.core.platform.base.model.StatusModel;
import cn.czcxy.xj.core.util.CommonUtil;
import cn.czcxy.xj.crmserver.purchase.purchaseApply.model.PurchaseApply;
import cn.czcxy.xj.crmserver.purchase.purchaseApply.service.IPurchaseApplyClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/crm/purchase/purchaseApply")
public class PurchaseApplyController extends BaseCurdClienController<PurchaseApply> {

    @Resource
    @BaseResource
    private IPurchaseApplyClientService purchaseApplyService;


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
        PurchaseApply purchaseApply = new PurchaseApply();
        String id = CommonUtil.getIdByCode(OrderTypeEnum.PURCHASEAPPLY.getCode());
        purchaseApply.setId(id);
        info.put("modelData", purchaseApply);
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
        return purchaseApplyService.getDataInfoById(id);
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
     * 审核
     *
     * @return
     */
    @RequestMapping(value = "/auditById", method = RequestMethod.POST)
    @ResponseBody
    public StatusModel auditById(@RequestParam("ids") String id) {
        return purchaseApplyService.auditById(id);
    }

    /**
     * 取消审核
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/cancelAuditById", method = RequestMethod.POST)
    @ResponseBody
    public StatusModel cancelAuditById(@RequestParam("ids") String id) {
        return purchaseApplyService.cancelAuditById(id);
    }

    /**
     * 下推采购订单
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/{purchaseApplyId}/pushPurchaseOrder", method = RequestMethod.GET)
    public String pushPurchaseOrder(Model model, @PathVariable("purchaseApplyId") String purchaseApplyId) {
        model.addAttribute("purchaseApplyId", purchaseApplyId);
        return this.view("pushPurchaseOrder");
    }
}
