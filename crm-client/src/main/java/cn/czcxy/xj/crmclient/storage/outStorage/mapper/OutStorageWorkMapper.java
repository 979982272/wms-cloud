package cn.czcxy.xj.crmclient.storage.outStorage.mapper;

import cn.czcxy.xj.core.platform.base.mapper.CustomizeMapper;
import cn.czcxy.xj.crmclient.storage.outStorage.entity.OutStorageWork;
import org.apache.ibatis.annotations.Param;

public interface OutStorageWorkMapper extends CustomizeMapper<OutStorageWork> {
    /**
     * 通过出库单编码查询
     *
     * @param id
     * @return
     */
    OutStorageWork findOutStorageWorkById(String id);

    /**
     * 修改状态
     *
     * @param id
     * @param deliveryCode
     */
    void updateOutStorageWorkStatusById(@Param("id") String id, @Param("deliveryCode") Integer deliveryCode);
}