package cn.czcxy.xj.basicclient.vendor.controller;

import cn.czcxy.xj.basicclient.vendor.entity.Vendor;
import cn.czcxy.xj.basicclient.vendor.service.IVendorService;
import cn.czcxy.xj.core.platform.base.annotation.BaseResource;
import cn.czcxy.xj.core.platform.base.controller.BaseCurdController;
import cn.czcxy.xj.core.platform.base.model.ComboModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/vendor")
@Api("供应商服务")
public class VendorController extends BaseCurdController<Vendor,String> {

    @Resource
    @BaseResource
    private IVendorService vendorService;


    /**
     * 查询仓库下拉数据源
     *
     * @return
     */
    @RequestMapping(value = "findVendorCombo", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "查询供应商的下拉框数据源", notes = "查询供应商的下拉框数据源")
    public List<ComboModel> findVendorCombo() {
        List<ComboModel> list = new ArrayList<>();
        List<Vendor> vendors = vendorService.selectAll();
        String[][] array = new String[vendors.size()][vendors.size()];
        for (int i = 0; i < vendors.size(); i++) {
            array[i][0] = vendors.get(i).getId();
            array[i][1] = vendors.get(i).getVendorName();
        }
        return ComboModel.convert(array, true);
    }
}
