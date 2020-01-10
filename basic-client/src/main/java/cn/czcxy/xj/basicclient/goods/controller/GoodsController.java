package cn.czcxy.xj.basicclient.goods.controller;

import cn.czcxy.xj.basicclient.goods.entity.Goods;
import cn.czcxy.xj.basicclient.goods.service.IGoodsService;
import cn.czcxy.xj.core.platform.base.annotation.BaseResource;
import cn.czcxy.xj.core.platform.base.controller.BaseCurdController;
import cn.czcxy.xj.core.platform.base.model.DataSourceRequest;
import cn.czcxy.xj.core.platform.base.model.DataSourceResult;
import cn.czcxy.xj.core.platform.base.model.StatusModel;
import cn.czcxy.xj.core.util.ArrayUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/goods")
@Api("商品服务")
public class GoodsController extends BaseCurdController<Goods,String> {

    @Resource
    @BaseResource
    private IGoodsService goodsArchiveService;


    /**
     * 获取供应商可以选择的产品数据,给供应商分配商品的时候使用
     *
     * @param dataSourceRequest
     * @param request
     * @return
     */
    @RequestMapping(value = "selectVendorGoods")
    @ApiOperation(value = "获取供应商可以选择的产品数据,给供应商分配商品的时候使用", notes = "获取供应商可以选择的产品数据,给供应商分配商品的时候使用")
    @ApiImplicitParam(name = "vendorId", value = "供应商编码", required = true)
    public DataSourceResult selectVendorGoods(@RequestBody DataSourceRequest dataSourceRequest, HttpServletRequest request, @RequestParam(value = "vendorId", required = true) String vendorId) {
        DataSourceResult dataSourceResult = new DataSourceResult();
        try {
            dataSourceResult = goodsArchiveService.selectVendorGoods(dataSourceRequest, request, vendorId);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
        }
        return dataSourceResult;
    }

    /**
     * 查询多个商品
     *
     * @param ids
     * @return
     */
    @ApiOperation(value = "查询多个商品", notes = "查询多个商品")
    @ApiImplicitParam(name = "ids", value = "商品编码数组", required = true)
    @RequestMapping(value = "findGoodsByIds", method = RequestMethod.POST)
    public List<Goods> findGoodsByIds(@RequestParam("ids") String[] ids) {
        Example example = new Example(Goods.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("id", ArrayUtil.arrayToList(ids));
        return goodsArchiveService.selectByExample(example);
    }
}
