package cn.czcxy.xj.dispatchclient.base.vendor;

import cn.czcxy.xj.basicserver.vendor.model.Vendor;
import cn.czcxy.xj.basicserver.vendor.service.IVendorClientService;
import cn.czcxy.xj.core.platform.base.annotation.BaseResource;
import cn.czcxy.xj.core.platform.base.controller.BaseCurdClienController;
import cn.czcxy.xj.core.platform.base.model.ComboModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/base/vendor")
public class VendorClientController extends BaseCurdClienController<Vendor> {

    @Resource
    @BaseResource
    private IVendorClientService vendorService;


    /**
     * 查询仓库下拉数据源
     *
     * @return
     */
    @RequestMapping(value = "findVendorCombo", method = RequestMethod.POST)
    @ResponseBody
    public List<ComboModel> findVendorCombo() {
        return vendorService.findVendorCombo();
    }
}
