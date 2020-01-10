package cn.czcxy.xj.crmclient.sales.controller;

import cn.czcxy.xj.core.platform.base.annotation.BaseResource;
import cn.czcxy.xj.core.platform.base.controller.BaseCurdController;
import cn.czcxy.xj.core.platform.base.model.DataSourceRequest;
import cn.czcxy.xj.core.platform.base.model.DataSourceResult;
import cn.czcxy.xj.crmclient.sales.entity.SalesOrderPart;
import cn.czcxy.xj.crmclient.sales.mapper.SalesOrderPartMapper;
import cn.czcxy.xj.crmclient.sales.service.ISalesOrderPartService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/sales/salesOrderPart")
public class SalesOrderPartController extends BaseCurdController<SalesOrderPart,String> {

    @Resource
    @BaseResource
    private ISalesOrderPartService salesOrderPartService;

    @Resource
    private SalesOrderPartMapper salesOrderPartMapper;


    /**
     * 载入明细数据
     *
     * @param dataSourceRequest
     * @param request
     * @return
     */
    @RequestMapping(value = "loadDetailData")
    public DataSourceResult loadDetailData(@RequestBody DataSourceRequest dataSourceRequest, HttpServletRequest request) {
        DataSourceResult dataSourceResult = new DataSourceResult();
        String id = String.valueOf(dataSourceRequest.getData().get("id"));
        try {
            List<SalesOrderPart> salesOrderParts = salesOrderPartMapper.findSalesOrderPartBySalesOrderId(id);
            dataSourceResult.setTotal(salesOrderParts.size());
            dataSourceResult.setData(salesOrderParts);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
        }
        return dataSourceResult;
    }

}
