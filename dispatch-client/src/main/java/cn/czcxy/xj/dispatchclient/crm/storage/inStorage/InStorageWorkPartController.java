package cn.czcxy.xj.dispatchclient.crm.storage.inStorage;

import cn.czcxy.xj.core.platform.base.annotation.BaseResource;
import cn.czcxy.xj.core.platform.base.controller.BaseCurdClienController;
import cn.czcxy.xj.core.platform.base.model.DataSourceRequest;
import cn.czcxy.xj.core.platform.base.model.DataSourceResult;
import cn.czcxy.xj.core.platform.base.model.StatusModel;
import cn.czcxy.xj.core.util.HttpServletRequestUtils;
import cn.czcxy.xj.crmserver.storage.inStorage.model.InStorageWorkPart;
import cn.czcxy.xj.crmserver.storage.inStorage.service.IInStorageWorkPartClientService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

@Controller
@RequestMapping("/crm/inStorage/inStorageWorkPart")
public class InStorageWorkPartController extends BaseCurdClienController<InStorageWorkPart> {

    @Resource
    @BaseResource
    private IInStorageWorkPartClientService inStorageWorkPartService;


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
        HttpServletRequestUtils.transRequestToDataSourceRequest(dataSourceRequest,request);
        return inStorageWorkPartService.loadDetailData(dataSourceRequest);
    }

    /**
     * 明细入库
     *
     * @return
     */
    @RequestMapping(value = "inStorageByPart", method = RequestMethod.POST)
    @ResponseBody
    public StatusModel inStorageByPart(@RequestParam(value = "id", required = true) String id,
                                       @RequestParam(value = "receivingAmount", required = true) BigDecimal receivingAmount) {
        return inStorageWorkPartService.inStorageByPart(id,receivingAmount);
    }
}
