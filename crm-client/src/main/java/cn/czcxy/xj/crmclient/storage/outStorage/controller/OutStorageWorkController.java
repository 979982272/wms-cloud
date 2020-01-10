package cn.czcxy.xj.crmclient.storage.outStorage.controller;

import cn.czcxy.xj.core.platform.base.annotation.BaseResource;
import cn.czcxy.xj.core.platform.base.controller.BaseCurdController;
import cn.czcxy.xj.core.platform.base.model.StatusModel;
import cn.czcxy.xj.crmclient.storage.outStorage.entity.OutStorageWork;
import cn.czcxy.xj.crmclient.storage.outStorage.service.IOutStorageWorkService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

@RestController
@RequestMapping("/outStorage/outStorageWork")
public class OutStorageWorkController extends BaseCurdController<OutStorageWork,String> {

    @Resource
    @BaseResource
    private IOutStorageWorkService outStorageWorkService;


    /**
     * 通过出库单整单出库
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/outStorageByStorageId", method = RequestMethod.POST)
    public StatusModel outStorageByStorageId(@RequestParam("ids") String id) {
        try {
            outStorageWorkService.outStorageByStorageId(id);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(),e);
            e.printStackTrace();
            return new StatusModel(false,e.getMessage());
        }
        return new StatusModel(true, "出库成功!");
    }
}
