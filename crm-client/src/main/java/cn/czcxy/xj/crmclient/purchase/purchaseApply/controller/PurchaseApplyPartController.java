package cn.czcxy.xj.crmclient.purchase.purchaseApply.controller;

import cn.czcxy.xj.core.platform.base.annotation.BaseResource;
import cn.czcxy.xj.core.platform.base.controller.BaseCurdController;
import cn.czcxy.xj.core.platform.base.model.StatusModel;
import cn.czcxy.xj.crmclient.purchase.purchaseApply.entity.PurchaseApplyPart;
import cn.czcxy.xj.crmclient.purchase.purchaseApply.service.IPurchaseApplyPartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/purchase/purchaseApplyPart")
public class PurchaseApplyPartController extends BaseCurdController<PurchaseApplyPart,String> {

    @Resource
    @BaseResource
    private IPurchaseApplyPartService purchaseApplyPartService;

}
