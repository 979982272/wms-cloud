package cn.czcxy.xj.crmclient.storage.outStorage.mapper;

import cn.czcxy.xj.core.platform.base.mapper.CustomizeMapper;
import cn.czcxy.xj.crmclient.storage.outStorage.entity.OutStorageWorkPart;

import java.util.List;

public interface OutStorageWorkPartMapper extends CustomizeMapper<OutStorageWorkPart> {
    /**
     * 通过出库单查询明细
     *
     * @param id
     * @return
     */
    List<OutStorageWorkPart> findOutStorageWorkPartByOutStorageWork(String id);
}