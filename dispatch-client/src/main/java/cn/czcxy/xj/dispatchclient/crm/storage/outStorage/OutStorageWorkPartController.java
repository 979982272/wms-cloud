package cn.czcxy.xj.dispatchclient.crm.storage.outStorage;

import cn.czcxy.xj.core.platform.base.annotation.BaseResource;
import cn.czcxy.xj.core.platform.base.controller.BaseCurdClienController;
import cn.czcxy.xj.core.platform.base.model.DataSourceRequest;
import cn.czcxy.xj.core.platform.base.model.DataSourceResult;
import cn.czcxy.xj.core.platform.base.model.StatusModel;
import cn.czcxy.xj.core.util.HttpServletRequestUtils;
import cn.czcxy.xj.crmserver.storage.outStorage.model.OutStorageWorkPart;
import cn.czcxy.xj.crmserver.storage.outStorage.service.IOutStorageWorkPartClientService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/crm/outStorage/outStorageWorkPart")
public class OutStorageWorkPartController extends BaseCurdClienController<OutStorageWorkPart> {

    @Resource
    @BaseResource
    private IOutStorageWorkPartClientService outStorageWorkPartService;


    /**
     * 载入明细信息
     *
     * @param dataSourceRequest
     * @param request
     * @return
     */
    @RequestMapping(value = "loadDetailData")
    @ResponseBody
    public DataSourceResult loadDetailData(@RequestBody DataSourceRequest dataSourceRequest, HttpServletRequest request) {
        HttpServletRequestUtils.transRequestToDataSourceRequest(dataSourceRequest, request);
        return outStorageWorkPartService.loadDetailData(dataSourceRequest);
    }

    /**
     * 明细出库
     *
     * @return
     */
    @RequestMapping(value = "outStorageByPart", method = RequestMethod.POST)
    @ResponseBody
    public StatusModel outStorageByPart(@RequestParam(value = "id", required = true) String id,
                                        @RequestParam(value = "deliveryAmount", required = true) BigDecimal deliveryAmount) {
        return outStorageWorkPartService.outStorageByPart(id, deliveryAmount);
    }
}
