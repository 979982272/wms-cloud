package cn.czcxy.xj.dispatchclient.crm.storage.outStorage;

import cn.czcxy.xj.core.platform.base.annotation.BaseResource;
import cn.czcxy.xj.core.platform.base.controller.BaseCurdClienController;
import cn.czcxy.xj.core.platform.base.model.StatusModel;
import cn.czcxy.xj.crmserver.storage.outStorage.model.OutStorageWork;
import cn.czcxy.xj.crmserver.storage.outStorage.service.IOutStorageWorkClientService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/crm/outStorage/outStorageWork")
public class OutStorageWorkController extends BaseCurdClienController<OutStorageWork> {

    @Resource
    @BaseResource
    private IOutStorageWorkClientService outStorageWorkService;


    /**
     * 通过出库单整单出库
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/outStorageByStorageId", method = RequestMethod.POST)
    @ResponseBody
    public StatusModel outStorageByStorageId(@RequestParam("ids") String id) {
        return outStorageWorkService.outStorageByStorageId(id);
    }
}
