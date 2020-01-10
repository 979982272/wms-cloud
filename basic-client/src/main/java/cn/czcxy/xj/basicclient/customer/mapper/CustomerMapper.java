package cn.czcxy.xj.basicclient.customer.mapper;

import cn.czcxy.xj.basicclient.customer.entity.Customer;
import cn.czcxy.xj.core.platform.base.mapper.CustomizeMapper;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CustomerMapper extends CustomizeMapper<Customer> {
    List<Customer> findCustomerByName(@Param("name") String name);

    List<Customer> findCustomerByNameNotId(@Param("name") String name, @Param("id") String id);
}