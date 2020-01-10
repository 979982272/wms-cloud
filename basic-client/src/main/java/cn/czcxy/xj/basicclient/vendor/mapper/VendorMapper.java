package cn.czcxy.xj.basicclient.vendor.mapper;

import cn.czcxy.xj.basicclient.vendor.entity.Vendor;
import cn.czcxy.xj.core.platform.base.mapper.CustomizeMapper;
import org.apache.ibatis.annotations.Select;

public interface VendorMapper extends CustomizeMapper<Vendor> {
    @Select("select vendor_name from eidp_vendor where id = #{vendorId}")
        //需要修改
        //@Result(column = "vendor_name", property = "vendorName")
    String findVendorNameById(String vendorId);
}