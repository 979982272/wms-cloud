package cn.czcxy.xj.basicclient.common.controller;

import cn.czcxy.xj.basicclient.common.service.ICommonService;
import cn.czcxy.xj.basicclient.goods.entity.Goods;
import cn.czcxy.xj.core.platform.base.controller.BaseController;
import cn.czcxy.xj.core.platform.base.model.ComboModel;
import cn.czcxy.xj.core.util.CommonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 公用控制器
 *
 * @author weihua
 * @create 2017-05-15 18:47
 */
@RestController
@RequestMapping(value = "/common")
@Api("公共服务")
public class CommonController extends BaseController {

    @Resource
    private ICommonService commonService;

    /**
     * 通过包名获取枚举的下拉框数据源
     *
     * @param key
     * @return
     */
    @RequestMapping(value = "/getComboModelDataSource")
    @ResponseBody
    @ApiOperation(value = "通过包名获取枚举的下拉框数据源", notes = "通过包名获取枚举的下拉框数据源")
    @ApiImplicitParam(name = "key", value = "枚举地址", required = true)
    public List<ComboModel> getComboModelDataSource(@RequestParam("key") String key) {
        List<ComboModel> comboModels = null;
        try {
            return CommonUtil.getComboModelByKey(key);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * 通过id列表查询产品并且转换为map
     *
     * @param goods
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "findGoodsByIdsAndToMap", method = RequestMethod.POST)
    @ApiOperation(value = "通过id列表查询产品并且转换为map", notes = "通过id列表查询产品并且转换为map")
    @ApiImplicitParam(name = "goods", value = "商品列表", required = true)
    Map<String, Goods> findGoodsByIdsAndToMap(@RequestParam("goods") List<String> goods) {
        Map<String, Goods> goodsMap = new HashMap<>();
        try {
            goodsMap = commonService.findGoodsByIdsAndToMap(goods);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
        }
        return goodsMap;
    }
}
