package cn.czcxy.xj.crmclient.storage.inStorage.controller;

import cn.czcxy.xj.core.platform.base.annotation.BaseResource;
import cn.czcxy.xj.core.platform.base.controller.BaseCurdController;
import cn.czcxy.xj.core.platform.base.model.StatusModel;
import cn.czcxy.xj.crmclient.storage.inStorage.entity.InStorageWork;
import cn.czcxy.xj.crmclient.storage.inStorage.service.IInStorageWorkService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/inStorage/inStorageWork")
public class InStorageWorkController extends BaseCurdController<InStorageWork,String> {

    @Resource
    @BaseResource
    private IInStorageWorkService inStorageWorkService;


    /**
     * 通过入口单号入库
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/inStorageByStorageId", method = RequestMethod.POST)
    public StatusModel inStorageByStorageId(@RequestParam("ids") String id) {
        try {
            inStorageWorkService.inStorageByStorageId(id);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(),e);
            e.printStackTrace();
            return new StatusModel(false,e.getMessage());
        }
        return new StatusModel(true, "入库成功!");
    }

}
