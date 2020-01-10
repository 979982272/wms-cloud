package cn.czcxy.xj.dispatchclient.crm.purchase.purchaseApply;

import cn.czcxy.xj.core.platform.base.annotation.BaseResource;
import cn.czcxy.xj.core.platform.base.controller.BaseCurdClienController;
import cn.czcxy.xj.crmserver.purchase.purchaseApply.model.PurchaseApplyPart;
import cn.czcxy.xj.crmserver.purchase.purchaseApply.service.IPurchaseApplyPartClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@RequestMapping("/crm/purchase/purchaseApplyPart")
public class PurchaseApplyPartController extends BaseCurdClienController<PurchaseApplyPart> {

    @Resource
    @BaseResource
    private IPurchaseApplyPartClientService purchaseApplyPartService;


    /**
     * 新增明细
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/{purchaseApplyId}/{vendorId}/addPurchaseApplyPart")
    public String addPurchaseApplyPart(Model model, @RequestParam(value = "id", required = false) String id,
                                       @PathVariable("purchaseApplyId") String purchaseApplyId,
                                       @PathVariable("vendorId") String vendorId) {
        model.addAttribute("id", id);
        model.addAttribute("purchaseApplyId", purchaseApplyId);
        return this.view("edit");
    }

    /**
     * 编辑明细
     * @param model
     * @param id
     * @param vendorId
     * @return
     */
    @RequestMapping(value = "/{vendorId}/edit", method = RequestMethod.GET)
    public String edit(Model model, @RequestParam(value = "id", required = false) String id, @PathVariable("vendorId") String vendorId) {
        model.addAttribute("id", id);
        model.addAttribute("vendorId", vendorId);
        return this.view("edit");
    }
}
