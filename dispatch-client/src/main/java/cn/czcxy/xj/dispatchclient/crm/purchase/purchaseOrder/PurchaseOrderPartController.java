package cn.czcxy.xj.dispatchclient.crm.purchase.purchaseOrder;

import cn.czcxy.xj.core.platform.base.annotation.BaseResource;
import cn.czcxy.xj.core.platform.base.controller.BaseCurdClienController;
import cn.czcxy.xj.core.platform.base.model.DataSourceRequest;
import cn.czcxy.xj.core.platform.base.model.DataSourceResult;
import cn.czcxy.xj.core.util.HttpServletRequestUtils;
import cn.czcxy.xj.crmserver.purchase.purchaseOrder.model.PurchaseOrderPart;
import cn.czcxy.xj.crmserver.purchase.purchaseOrder.service.IPurchaseOrderPartClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/crm/purchase/purchaseOrderPart")
public class PurchaseOrderPartController extends BaseCurdClienController<PurchaseOrderPart> {

    @Resource
    @BaseResource
    private IPurchaseOrderPartClientService purchaseOrderPartService;


    /**
     * 载入明细数据
     * @param dataSourceRequest
     * @param request
     * @return
     */
    @RequestMapping(value = "loadDetailData")
    @ResponseBody
    public DataSourceResult loadDetailData(@RequestBody DataSourceRequest dataSourceRequest, HttpServletRequest request) {
        HttpServletRequestUtils.transRequestToDataSourceRequest(dataSourceRequest, request);
        return purchaseOrderPartService.loadDetailData(dataSourceRequest);
    }

    /**
     * 编辑明细
     *
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

    /**
     * 新增明细
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/{purchaseOrderId}/{vendorId}/addPurchaseOrderPart")
    public String addPurchaseOrderPart(Model model, @RequestParam(value = "id", required = false) String id,
                                       @PathVariable("purchaseOrderId") String purchaseOrderId,
                                       @PathVariable("vendorId") String vendorId) {
        model.addAttribute("id", id);
        model.addAttribute("purchaseOrderId", purchaseOrderId);
        return this.view("edit");
    }
}
