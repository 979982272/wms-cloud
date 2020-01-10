package cn.czcxy.xj.basicclient.goods.controller;

import cn.czcxy.xj.basicclient.goods.entity.GoodsUnit;
import cn.czcxy.xj.basicclient.goods.service.IGoodsUnitService;
import cn.czcxy.xj.core.platform.base.annotation.BaseResource;
import cn.czcxy.xj.core.platform.base.controller.BaseCurdController;
import cn.czcxy.xj.core.platform.base.model.ComboModel;
import cn.czcxy.xj.core.platform.base.model.StatusModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/goodsUnit")
@Api("商品单位服务")
public class GoodsUnitController extends BaseCurdController<GoodsUnit,String> {

    @Resource
    @BaseResource
    private IGoodsUnitService goodsUnitService;

    /**
     * 产品单位下拉框数据源
     *
     * @return
     */
    @RequestMapping(value = "findCombo", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "商品单位下拉数据源", notes = "商品单位下拉数据源")
    public List<ComboModel> findCombo() {
        List<GoodsUnit> goodsTypes = goodsUnitService.selectAll();
        String[][] array = new String[goodsTypes.size() + 1][goodsTypes.size() + 1];
        for (int i = 0; i < goodsTypes.size(); i++) {
            array[i][0] = goodsTypes.get(i).getId();
            array[i][1] = goodsTypes.get(i).getUnitName();
        }
        return ComboModel.convert(array, true);
    }
}
