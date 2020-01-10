package cn.czcxy.xj.crmclient.storage.outStorage.controller;

import cn.czcxy.xj.core.platform.base.annotation.BaseResource;
import cn.czcxy.xj.core.platform.base.controller.BaseCurdController;
import cn.czcxy.xj.core.platform.base.model.DataSourceRequest;
import cn.czcxy.xj.core.platform.base.model.DataSourceResult;
import cn.czcxy.xj.core.platform.base.model.StatusModel;
import cn.czcxy.xj.crmclient.storage.outStorage.entity.OutStorageWorkPart;
import cn.czcxy.xj.crmclient.storage.outStorage.mapper.OutStorageWorkPartMapper;
import cn.czcxy.xj.crmclient.storage.outStorage.service.IOutStorageWorkPartService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/outStorage/outStorageWorkPart")
public class OutStorageWorkPartController extends BaseCurdController<OutStorageWorkPart,String> {

    @Resource
    @BaseResource
    private IOutStorageWorkPartService outStorageWorkPartService;

    @Resource
    private OutStorageWorkPartMapper outStorageWorkPartMapper;


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
            List<OutStorageWorkPart> outStorageWorkParts = outStorageWorkPartMapper.findOutStorageWorkPartByOutStorageWork(id);
            dataSourceResult.setTotal(outStorageWorkParts.size());
            dataSourceResult.setData(outStorageWorkParts);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
        }
        return dataSourceResult;
    }

    /**
     * 明细出库
     *
     * @return
     */
    @RequestMapping(value = "outStorageByPart", method = RequestMethod.POST)
    public StatusModel outStorageByPart(@RequestParam(value = "id", required = true) String id,
                                        @RequestParam(value = "deliveryAmount", required = true) BigDecimal deliveryAmount) {
        try {
            outStorageWorkPartService.outStorageByPart(id, deliveryAmount);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
            return new StatusModel(false, e.getMessage());
        }
        return new StatusModel(true, "入库成功！");
    }
}
