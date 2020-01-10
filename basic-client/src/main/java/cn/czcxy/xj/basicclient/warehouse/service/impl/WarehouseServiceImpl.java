package cn.czcxy.xj.basicclient.warehouse.service.impl;

import cn.czcxy.xj.basicclient.warehouse.entity.Warehouse;
import cn.czcxy.xj.basicclient.warehouse.mapper.WarehouseMapper;
import cn.czcxy.xj.basicclient.warehouse.service.IWarehouseService;
import cn.czcxy.xj.core.platform.base.annotation.BaseResource;
import cn.czcxy.xj.core.platform.base.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Transactional(rollbackFor = Exception.class)
@Service("warehouseService")
public class WarehouseServiceImpl extends BaseServiceImpl<Warehouse,String> implements IWarehouseService {

    @Resource
    @BaseResource
    private WarehouseMapper warehouseMapper;

}