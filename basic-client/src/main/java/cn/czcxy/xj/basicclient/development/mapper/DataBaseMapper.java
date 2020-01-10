package cn.czcxy.xj.basicclient.development.mapper;

import cn.czcxy.xj.basicclient.development.entity.DataBase;
import cn.czcxy.xj.core.platform.base.model.DataSourceRequest;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * Created by Administrator on 2016/12/24 0024.
 */
public interface DataBaseMapper {
    /**
     * 查询所有base数据库中所有的表
     *
     * @return
     */
    public List<DataBase> showTables();

    /**
     * 查询数据库中的表
     *
     * @param DBName
     * @return
     */
    public List<DataBase> showTablesByDBName(String DBName);

    @SelectProvider(type = DataBaseMapperDynmic.class, method = "showTablesProvider")
    @Results({
            @Result(column = "TABLE_NAME", property = "tableName"),
            @Result(column = "TABLE_COMMENT", property = "tableComment")
    })
    public List<DataBase> showTablesProvider(DataSourceRequest dataSourceRequest);
}
