package cn.czcxy.xj.crmclient.sales.controller;

import cn.czcxy.xj.core.extra.enums.OrderTypeEnum;
import cn.czcxy.xj.core.extra.enums.sales.SalesOrderStatusEnum;
import cn.czcxy.xj.core.platform.base.annotation.BaseResource;
import cn.czcxy.xj.core.platform.base.controller.BaseCurdController;
import cn.czcxy.xj.core.platform.base.model.StatusModel;
import cn.czcxy.xj.core.util.CommonUtil;
import cn.czcxy.xj.crmclient.sales.entity.SalesOrder;
import cn.czcxy.xj.crmclient.sales.mapper.SalesOrderMapper;
import cn.czcxy.xj.crmclient.sales.service.ISalesOrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/sales/salesOrder")
public class SalesOrderController extends BaseCurdController<SalesOrder,String> {

    @Resource
    @BaseResource
    private ISalesOrderService salesOrderService;

    @Resource
    private SalesOrderMapper salesOrderMapper;


    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}/getDataInfoById", method = RequestMethod.POST)
    @Override
    public StatusModel getDataInfoById(@PathVariable(value = "id") String id) {
        StatusModel statusModel = null;
        try {
            SalesOrder t = salesOrderMapper.findSalesOrderById(id);
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
    @RequestMapping(value = "/{id}/auditById", method = RequestMethod.POST)
    public StatusModel auditById(@PathVariable("id") String id) {
        try {
            salesOrderService.auditById(id);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
            return new StatusModel(false, e.getMessage());
        }
        return new StatusModel(true, "审核成功！");
    }
}
