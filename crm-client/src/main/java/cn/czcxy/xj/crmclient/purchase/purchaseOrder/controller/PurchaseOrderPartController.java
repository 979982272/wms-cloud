package cn.czcxy.xj.crmclient.purchase.purchaseOrder.controller;

import cn.czcxy.xj.core.platform.base.annotation.BaseResource;
import cn.czcxy.xj.core.platform.base.controller.BaseCurdController;
import cn.czcxy.xj.core.platform.base.model.DataSourceRequest;
import cn.czcxy.xj.core.platform.base.model.DataSourceResult;
import cn.czcxy.xj.core.platform.base.model.StatusModel;
import cn.czcxy.xj.crmclient.purchase.purchaseOrder.entity.PurchaseOrderPart;
import cn.czcxy.xj.crmclient.purchase.purchaseOrder.mapper.PurchaseOrderPartMapper;
import cn.czcxy.xj.crmclient.purchase.purchaseOrder.service.IPurchaseOrderPartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/purchase/purchaseOrderPart")
public class PurchaseOrderPartController extends BaseCurdController<PurchaseOrderPart,String> {

    @Resource
    @BaseResource
    private IPurchaseOrderPartService purchaseOrderPartService;

    @Resource
    private PurchaseOrderPartMapper purchaseOrderPartMapper;


    @RequestMapping(value = "loadDetailData")
    public DataSourceResult loadDetailData(@RequestBody DataSourceRequest dataSourceRequest, HttpServletRequest request) {
        DataSourceResult dataSourceResult = new DataSourceResult();
        String id = String.valueOf(dataSourceRequest.getData().get("id"));
        try {
            List<PurchaseOrderPart> purchaseOrderParts = purchaseOrderPartMapper.findPurchaseOrderPartByPurchaseId(id);
            dataSourceResult.setTotal(purchaseOrderParts.size());
            dataSourceResult.setData(purchaseOrderParts);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
        }
        return dataSourceResult;
    }
}
