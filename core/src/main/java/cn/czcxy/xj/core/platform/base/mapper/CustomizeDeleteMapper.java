package cn.czcxy.xj.core.platform.base.mapper;

import org.apache.ibatis.annotations.DeleteProvider;
import tk.mybatis.mapper.provider.base.BaseDeleteProvider;

/**
 * @Auther: wwh
 * @Date: 2018/7/2 0002 22:42
 * @Description:
 */
@Deprecated
public interface CustomizeDeleteMapper<T> {
    /**
     * 根据主键字段进行删除，方法参数必须包含完整的主键属性
     * 不判断版本信息
     *
     * @param key
     * @return
     */
    @DeleteProvider(type = CustomizeDeleteProvider.class, method = "dynamicSQL")
    int deleteByPrimaryKeyNotVersion(Object key);

    /**
     * 根据实体属性作为条件进行删除，查询条件使用等号
     * 不判断版本信息
     *
     * @param record
     * @return
     */
    @DeleteProvider(type = CustomizeDeleteProvider.class, method = "dynamicSQL")
    int deleteNotVersion(T record);
}
