package cn.czcxy.xj.dispatchclient.base.goods;

import cn.czcxy.xj.basicserver.goods.model.GoodsUnit;
import cn.czcxy.xj.basicserver.goods.service.IGoodsUnitClientService;
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
@RequestMapping("/base/goodsUnit")
public class GoodsUnitClientController extends BaseCurdClienController<GoodsUnit> {

    @Resource
    @BaseResource
    private IGoodsUnitClientService goodsUnitService;


    /**
     * 产品单位下拉框数据源
     *
     * @return
     */
    @RequestMapping(value = "findCombo", method = RequestMethod.POST)
    @ResponseBody
    public List<ComboModel> findCombo() {
        return goodsUnitService.findCombo();
    }
}
