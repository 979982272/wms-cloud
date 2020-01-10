package cn.czcxy.xj.core.platform.base.mapper;

import org.apache.ibatis.mapping.MappedStatement;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.mapperhelper.SqlHelper;

/**
 * @Auther: wwh
 * @Date: 2018/7/2 0002 22:43
 * @Description:
 */
@Deprecated
public class CustomizeDeleteProvider extends MapperTemplate {

    public CustomizeDeleteProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }


    /**
     * 通过条件删除
     *
     * @param ms
     * @return
     */
    public String deleteNotVersion(MappedStatement ms) {
        Class<?> entityClass = getEntityClass(ms);
        StringBuilder sql = new StringBuilder();
        sql.append(SqlHelper.deleteFromTable(entityClass, tableName(entityClass)));
        //TODO 增加 @Version 支持
        sql.append(SqlHelper.whereAllIfColumns(entityClass, isNotEmpty(), true));
        return sql.toString();
    }

    /**
     * 通过主键删除
     *
     * @param ms
     */
    public String deleteByPrimaryKeyNotVersion(MappedStatement ms) {
        final Class<?> entityClass = getEntityClass(ms);
        StringBuilder sql = new StringBuilder();
        sql.append(SqlHelper.deleteFromTable(entityClass, tableName(entityClass)));
        //增加 @Version 乐观锁支持
        sql.append(SqlHelper.wherePKColumns(entityClass, false));
        return sql.toString();
    }

}
