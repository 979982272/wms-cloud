package cn.czcxy.xj.basicclient.development.mapper;

import cn.czcxy.xj.basicclient.development.entity.Development;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

/**
 * 数据列信息接口
 *
 * @author weihua
 * @create 2017-04-23 15:47
 */
public interface DevelopmentMapper {
    public List<Development> findAllColumn(String table);

    @SelectProvider(type = DevelopmentMapperDynamic.class, method = "findAllColumForProvider")
    public List<Development> findAllColumForProvider(String table);

    /**
     * 查出表中的主键信息
     * @param table
     * @return
     */
    @Select("SELECT DISTINCT DATA_TYPE as dataType,NUMERIC_PRECISION as size,NUMERIC_SCALE as scale FROM" +
            " information_schema. COLUMNS WHERE table_name = #{table} and COLUMN_KEY='PRI' ")
    Map<String,Object> findPriByTable(@Param("table") String table);
}
