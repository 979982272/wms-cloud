package cn.czcxy.xj.dispatchclient.crm.storage.inStorage;

import cn.czcxy.xj.core.platform.base.annotation.BaseResource;
import cn.czcxy.xj.core.platform.base.controller.BaseCurdClienController;
import cn.czcxy.xj.core.platform.base.model.StatusModel;
import cn.czcxy.xj.crmserver.storage.inStorage.model.InStorageWork;
import cn.czcxy.xj.crmserver.storage.inStorage.service.IInStorageWorkClientService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/crm/inStorage/inStorageWork")
public class InStorageWorkController extends BaseCurdClienController<InStorageWork> {

    @Resource
    @BaseResource
    private IInStorageWorkClientService inStorageWorkService;


    /**
     * 通过入口单号入库
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/inStorageByStorageId", method = RequestMethod.POST)
    @ResponseBody
    public StatusModel inStorageByStorageId(@RequestParam("ids") String id) {
        return inStorageWorkService.inStorageByStorageId(id);
    }

}
