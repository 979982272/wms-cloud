package cn.czcxy.xj.crmclient.storage.inStorage.controller;

import cn.czcxy.xj.core.platform.base.annotation.BaseResource;
import cn.czcxy.xj.core.platform.base.controller.BaseCurdController;
import cn.czcxy.xj.core.platform.base.model.DataSourceRequest;
import cn.czcxy.xj.core.platform.base.model.DataSourceResult;
import cn.czcxy.xj.core.platform.base.model.StatusModel;
import cn.czcxy.xj.crmclient.storage.inStorage.entity.InStorageWorkPart;
import cn.czcxy.xj.crmclient.storage.inStorage.mapper.InStorageWorkPartMapper;
import cn.czcxy.xj.crmclient.storage.inStorage.service.IInStorageWorkPartService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/inStorage/inStorageWorkPart")
public class InStorageWorkPartController extends BaseCurdController<InStorageWorkPart,String> {

    @Resource
    @BaseResource
    private IInStorageWorkPartService inStorageWorkPartService;

    @Resource
    private InStorageWorkPartMapper inStorageWorkPartMapper;


    /**
     * 载入明细信息
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
            List<InStorageWorkPart> inStorageWorkParts = inStorageWorkPartMapper.findByPartId(id);
            dataSourceResult.setTotal(inStorageWorkParts.size());
            dataSourceResult.setData(inStorageWorkParts);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
        }
        return dataSourceResult;
    }

    /**
     * 明细入库
     *
     * @return
     */
    @RequestMapping(value = "inStorageByPart", method = RequestMethod.POST)
    public StatusModel inStorageByPart(@RequestParam(value = "id", required = true) String id,
                                       @RequestParam(value = "receivingAmount", required = true) BigDecimal receivingAmount) {
        try {
            inStorageWorkPartService.inStorageByPart(id, receivingAmount);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
            return new StatusModel(false, e.getMessage());
        }
        return new StatusModel(true, "入库成功！");
    }
}
