package cn.czcxy.xj.basicclient.vendor.service.impl;

import cn.czcxy.xj.basicclient.vendor.entity.Vendor;
import cn.czcxy.xj.basicclient.vendor.mapper.VendorMapper;
import cn.czcxy.xj.basicclient.vendor.service.IVendorService;
import cn.czcxy.xj.core.platform.base.annotation.BaseResource;
import cn.czcxy.xj.core.platform.base.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Transactional(rollbackFor = Exception.class)
@Service("vendorService")
public class VendorServiceImpl extends BaseServiceImpl<Vendor,String> implements IVendorService {

    @Resource
    @BaseResource
    private VendorMapper vendorMapper;

}