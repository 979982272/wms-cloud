package cn.czcxy.xj.crmclient.storage.inStorage.mapper;

import cn.czcxy.xj.core.platform.base.mapper.CustomizeMapper;
import cn.czcxy.xj.crmclient.storage.inStorage.entity.InStorageWorkPart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InStorageWorkPartMapper extends CustomizeMapper<InStorageWorkPart> {
    /**
     * 通过入库单编号查询明细
     *
     * @param id
     * @return
     */
    List<InStorageWorkPart> findByPartId(@Param("id") String id);


}