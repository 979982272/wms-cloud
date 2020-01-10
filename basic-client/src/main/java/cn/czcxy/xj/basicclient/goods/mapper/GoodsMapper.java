package cn.czcxy.xj.basicclient.goods.mapper;

import cn.czcxy.xj.basicclient.goods.entity.Goods;
import cn.czcxy.xj.core.platform.base.mapper.CustomizeMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

public interface GoodsMapper extends CustomizeMapper<Goods> {
    /**
     * 通过产品名称查询
     *
     * @param goodsName
     * @return
     */
    List<Goods> findGoodsByGoodsName(@Param("goodsName") String goodsName);

    /**
     * 通过产品名称查询，排除id
     *
     * @param goodsName
     * @param id
     * @return
     */
    List<Goods> findGoodsByGoodsNameAndNotId(@Param("goodsName") String goodsName, @Param("id") String id);

    /**
     * 设置为逻辑删除
     *
     * @param id
     */
    void updateGoodsByServerFlag(@Param("id") String id);

    /**
     * 通过供应商id选择供应商可以操作的产品
     *
     * @param map
     * @return
     */
    @SelectProvider(type = GoodsDynaSqlProvider.class, method = "findVendorGoodsByVendorId")
    @ResultMap(value = "BaseResultMap")
    List<Goods> findVendorGoodsByVendorId(Map<String, Object> map);
}