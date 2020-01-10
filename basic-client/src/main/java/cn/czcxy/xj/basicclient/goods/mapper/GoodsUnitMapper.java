package cn.czcxy.xj.basicclient.goods.mapper;

import cn.czcxy.xj.basicclient.goods.entity.GoodsUnit;
import cn.czcxy.xj.core.platform.base.mapper.CustomizeMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface GoodsUnitMapper extends CustomizeMapper<GoodsUnit> {
    /**
     * 通过单位名称查询
     *
     * @param unitName
     * @return
     */
    List<GoodsUnit> findGoodsUnitByUnitName(@Param("unitName") String unitName);

    /**
     * 通过单位名称查询，排除单位
     *
     * @param unitName
     * @param id
     * @return
     */
    List<GoodsUnit> findGoodsUnitByUnitNameAndNotId(@Param("unitName") String unitName, @Param("id") String id);
}