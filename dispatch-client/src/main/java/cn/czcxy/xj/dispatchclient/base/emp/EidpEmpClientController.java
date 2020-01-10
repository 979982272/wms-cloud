package cn.czcxy.xj.dispatchclient.base.emp;

import cn.czcxy.xj.basicserver.emp.model.EidpEmp;
import cn.czcxy.xj.basicserver.emp.service.IEidpEmpClientService;
import cn.czcxy.xj.core.platform.base.annotation.BaseResource;
import cn.czcxy.xj.core.platform.base.controller.BaseCurdClienController;
import cn.czcxy.xj.core.platform.base.model.StatusModel;
import cn.czcxy.xj.core.platform.base.model.TreeModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/base/emp")
public class EidpEmpClientController extends BaseCurdClienController<EidpEmp> {

    @Resource
    @BaseResource
    private IEidpEmpClientService eidpEmpService;

    /**
     * 初始化
     *
     * @return
     */
    @RequestMapping(value = "/getDefaultInfo", method = RequestMethod.POST)
    @ResponseBody
    @Override
    public StatusModel getDefaultInfo() {
        return eidpEmpService.getDefaultInfo();
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}/getDataInfoById", method = RequestMethod.POST)
    @ResponseBody
    @Override
    public StatusModel getDataInfoById(@PathVariable(value = "id") String id) {
        return eidpEmpService.getDataInfoById(id);
    }

    @RequestMapping(value = "getDropDownDataSource", method = RequestMethod.POST)
    @ResponseBody
    public List<TreeModel> getDropDownDataSource(@RequestParam(value = "EmployeeId", required = false) String EmployeeId) {
        List<TreeModel> list = new ArrayList<>();
        TreeModel treeModel = new TreeModel();
        treeModel.setEmployeeId("123");
        treeModel.setFullNamel("456");
        treeModel.setHasEmployees(true);
        list.add(treeModel);
        return list;
    }

    /**
     * 选择负责人
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/selectEmp", method = RequestMethod.GET)
    public String selectEmp(Model model) {
        return this.view("selectEmp");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/reSetPassword", method = RequestMethod.POST)
    @ResponseBody
    public StatusModel reSetPassword(@RequestParam(value = "ids") String ids) {
        return eidpEmpService.reSetPassword(ids.split(","));
    }
}
