package cn.czcxy.xj.dispatchclient.base.goods;

import cn.czcxy.xj.basicserver.goods.model.GoodsType;
import cn.czcxy.xj.basicserver.goods.model.GoodsTypeTree;
import cn.czcxy.xj.basicserver.goods.service.IGoodsTypeClientService;
import cn.czcxy.xj.core.platform.base.annotation.BaseResource;
import cn.czcxy.xj.core.platform.base.controller.BaseCurdClienController;
import cn.czcxy.xj.core.platform.base.model.ComboModel;
import cn.czcxy.xj.core.util.tree.TreeUtils;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/base/goodsType")
public class GoodsTypeClientController extends BaseCurdClienController<GoodsType> {

    @Resource
    @BaseResource
    private IGoodsTypeClientService baseGoodsTypeService;


    // 载入所有数据
    @RequestMapping(value = "loadGoodsTypeData")
    @ResponseBody
    public String loadGoodsTypeData(HttpServletRequest request) {
        String result = baseGoodsTypeService.loadGoodsTypeData();
        List<GoodsTypeTree> goodsTypes = JSONObject.parseArray(result, GoodsTypeTree.class);
        TreeUtils<GoodsTypeTree, String> treeUtils = new TreeUtils<GoodsTypeTree, String>();
        List<GoodsTypeTree> goodsTypeTrees = treeUtils.getChildTreeObjects(goodsTypes, null);
        return result;
    }

    // 添加下一级
    @RequestMapping(value = "/addNext", method = RequestMethod.GET)
    public String addNext(Model model, @RequestParam(value = "parentId", required = false) String id) {
        model.addAttribute("parentId", id);
        return this.view("edit");
    }

    /**
     * 产品类型下拉框数据源
     *
     * @return
     */
    @RequestMapping(value = "findCombo", method = RequestMethod.POST)
    @ResponseBody
    public List<ComboModel> findCombo() {
        return baseGoodsTypeService.findCombo();
    }

    // 载入所有数据
    @RequestMapping(value = "loadGoodsTypeDataByChildren")
    @ResponseBody
    public String loadGoodsTypeDataByChildren(HttpServletRequest request) {
        String result = baseGoodsTypeService.loadGoodsTypeData();
        List<GoodsTypeTree> goodsTypes = JSONObject.parseArray(result, GoodsTypeTree.class);
        TreeUtils<GoodsTypeTree, String> treeUtils = new TreeUtils<GoodsTypeTree, String>();
        List<GoodsTypeTree> goodsTypeTrees = treeUtils.getChildTreeObjects(goodsTypes, null);
        return JSONObject.toJSONString(goodsTypeTrees);
    }
}
