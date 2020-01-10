package cn.czcxy.xj.dispatchclient.crm.sales;

import cn.czcxy.xj.core.platform.base.annotation.BaseResource;
import cn.czcxy.xj.core.platform.base.controller.BaseCurdClienController;
import cn.czcxy.xj.core.platform.base.model.DataSourceRequest;
import cn.czcxy.xj.core.platform.base.model.DataSourceResult;
import cn.czcxy.xj.core.util.HttpServletRequestUtils;
import cn.czcxy.xj.crmserver.sales.model.SalesOrderPart;
import cn.czcxy.xj.crmserver.sales.service.ISalesOrderPartClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/crm/sales/salesOrderPart")
public class SalesOrderPartController extends BaseCurdClienController<SalesOrderPart> {

    @Resource
    @BaseResource
    private ISalesOrderPartClientService salesOrderPartService;


    /**
     * 载入明细数据
     *
     * @param dataSourceRequest
     * @param request
     * @return
     */
    @RequestMapping(value = "loadDetailData")
    @ResponseBody
    public DataSourceResult loadDetailData(@RequestBody DataSourceRequest dataSourceRequest, HttpServletRequest request) {
        HttpServletRequestUtils.transRequestToDataSourceRequest(dataSourceRequest, request);
        return salesOrderPartService.loadDetailData(dataSourceRequest);
    }

    /**
     * 新增明细
     *
     * @param model
     * @param
     * @return
     */
    @RequestMapping(value = "/{salesOrderId}/{warehouseId}/addSalesOrderPart")
    public String addSalesOrderPart(Model model,
                                    @PathVariable("salesOrderId") String salesOrderId,
                                    @PathVariable("warehouseId") String warehouseId, @RequestParam(value = "id", required = false) String id) {
        model.addAttribute("warehouseId", warehouseId);
        model.addAttribute("salesOrderId", salesOrderId);
        model.addAttribute("id", id);
        return this.view("edit");
    }
}
