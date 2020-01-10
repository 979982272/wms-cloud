package cn.czcxy.xj.basicclient.goods.controller;

import cn.czcxy.xj.basicclient.goods.entity.GoodsType;
import cn.czcxy.xj.basicclient.goods.mapper.GoodsTypeMapper;
import cn.czcxy.xj.basicclient.goods.service.IGoodsTypeService;
import cn.czcxy.xj.core.platform.base.annotation.BaseResource;
import cn.czcxy.xj.core.platform.base.controller.BaseCurdController;
import cn.czcxy.xj.core.platform.base.model.ComboModel;
import cn.czcxy.xj.core.platform.base.model.StatusModel;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/goodsType")
@Api("商品类型服务")
public class GoodsTypeController extends BaseCurdController<GoodsType,String> {

    @Resource
    @BaseResource
    private IGoodsTypeService baseGoodsTypeService;

    @Resource
    private GoodsTypeMapper goodsTypeMapper;


    // 载入所有数据
    @RequestMapping(value = "loadGoodsTypeData", method = RequestMethod.GET)
    @ApiOperation(value = "载入所有数据", notes = "载入所有数据")
    public String loadGoodsTypeData() {
        List<GoodsType> goodsTypes = goodsTypeMapper.selectAll();
        return JSON.toJSONString(goodsTypes, SerializerFeature.WriteMapNullValue);
    }


    /**
     * 产品类型下拉框数据源
     *
     * @return
     */
    @RequestMapping(value = "findCombo", method = RequestMethod.POST)
    @ApiOperation(value = "商品类型下拉数据源", notes = "商品类型下拉数据源")
    public List<ComboModel> findCombo() {
        List<GoodsType> goodsTypes = goodsTypeMapper.selectAll();
        String[][] array = new String[goodsTypes.size() + 1][goodsTypes.size() + 1];
        for (int i = 0; i < goodsTypes.size(); i++) {
            array[i][0] = goodsTypes.get(i).getId();
            array[i][1] = goodsTypes.get(i).getName();
        }
        return ComboModel.convert(array, true);
    }
}
