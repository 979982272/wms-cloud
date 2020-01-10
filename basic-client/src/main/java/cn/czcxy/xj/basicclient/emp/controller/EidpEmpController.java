package cn.czcxy.xj.basicclient.emp.controller;

import cn.czcxy.xj.core.platform.base.annotation.BaseResource;
import cn.czcxy.xj.core.platform.base.controller.BaseCurdController;
import cn.czcxy.xj.basicclient.emp.entity.EidpEmp;
import cn.czcxy.xj.basicclient.emp.service.IEidpEmpService;
import cn.czcxy.xj.core.platform.base.model.StatusModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: wwh
 * @Date: 2018/5/27 0027 22:50
 * @Description:
 */
@RestController
@RequestMapping("/emp")
@Api("用户服务")
public class EidpEmpController extends BaseCurdController<EidpEmp,String> {
    @Resource
    @BaseResource
    private IEidpEmpService eidpEmpService;

    @ApiOperation(value = "查询用户", notes = "通过用户名称获取用户")
    @ApiImplicitParam(name = "userName", value = "用户名称", required = true)
    @RequestMapping(value = "/findEidpEmpByName", method = RequestMethod.POST)
    public EidpEmp findEidpEmpByName(@RequestParam(value = "userName", required = true) String userName) {
        return eidpEmpService.findEidpEmpByName(userName);
    }

    /**
     * 初始化
     *
     * @return
     */
    @RequestMapping(value = "/getDefaultInfo", method = RequestMethod.POST)
    @Override
    @ApiOperation(value = "初始化", notes = "初始化")
    public StatusModel getDefaultInfo() {
        Map map = new HashMap();
        EidpEmp eidpEmp = new EidpEmp();
        String id = eidpEmpService.getCreateEidpEmpId();
        eidpEmp.setId(id);
        map.put("modelData", eidpEmp);
        StatusModel statusModel = new StatusModel(true, map);
        return statusModel;
    }

    /**
     * 重置密码
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "reSetPassword", method = RequestMethod.POST)
    @ApiOperation(value = "重置密码")
    @ApiImplicitParam(value = "ids", name = "需要重置的用户编码")
    public StatusModel reSetPassword(@RequestParam(value = "ids", required = true) String[] ids) {
        StatusModel statusModel = new StatusModel(true, "重置密码成功！");
        try {
            eidpEmpService.reSetPassword(ids);
        } catch (Exception e) {
            statusModel.setStatus(false);
            statusModel.setMsg(e.getMessage());
        }
        return statusModel;
    }
}
