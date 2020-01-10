package cn.czcxy.xj.crmclient.storage.inStorage.mapper;

import cn.czcxy.xj.core.platform.base.mapper.CustomizeMapper;
import cn.czcxy.xj.crmclient.storage.inStorage.entity.InStorageWork;
import org.apache.ibatis.annotations.Param;

public interface InStorageWorkMapper extends CustomizeMapper<InStorageWork> {
    /**
     * 通过id查询入库单
     *
     * @param id
     * @return
     */
    InStorageWork findInStorageWorkById(@Param("id") String id);

    /**
     * 修改入库单状态
     *
     * @param id
     * @param receivingCode
     * @throws Exception
     */
    void updateInStorageWorkStatusById(@Param("id") String id, @Param("receivingCode") Integer receivingCode) throws Exception;
}