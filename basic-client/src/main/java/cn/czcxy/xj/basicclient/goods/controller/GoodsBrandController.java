package cn.czcxy.xj.basicclient.goods.controller;

import cn.czcxy.xj.basicclient.goods.entity.GoodsBrand;
import cn.czcxy.xj.basicclient.goods.service.IGoodsBrandService;
import cn.czcxy.xj.core.platform.base.annotation.BaseResource;
import cn.czcxy.xj.core.platform.base.controller.BaseCurdController;
import cn.czcxy.xj.core.platform.base.model.ComboModel;
import cn.czcxy.xj.core.platform.base.model.StatusModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
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
@RequestMapping("/goodsBrand")
@Api("商品品牌服务")
public class GoodsBrandController extends BaseCurdController<GoodsBrand,String> {

    @Resource
    @BaseResource
    private IGoodsBrandService goodsBrandService;


    /**
     * 产品单位下拉框数据源
     *
     * @return
     */
    @RequestMapping(value = "findCombo", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "查询多个商品,产品单位下拉框数据源", notes = "产品单位下拉框数据源")
    public List<ComboModel> findCombo() {
        List<GoodsBrand> goodsTypes = goodsBrandService.selectAll();
        String[][] array = new String[goodsTypes.size() + 1][goodsTypes.size() + 1];
        for (int i = 0; i < goodsTypes.size(); i++) {
            array[i][0] = goodsTypes.get(i).getId();
            array[i][1] = goodsTypes.get(i).getChnName();
        }
        return ComboModel.convert(array, true);
    }
}
