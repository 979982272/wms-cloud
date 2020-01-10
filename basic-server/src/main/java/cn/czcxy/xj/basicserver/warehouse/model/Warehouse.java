package cn.czcxy.xj.basicserver.warehouse.model;


import lombok.*;
import tk.mybatis.mapper.annotation.Version;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Warehouse {
    private Integer version;
    /**
     * 仓库编码
     */
    private String id;

    /**
     * 仓库名称
     */
    private String warehouseName;

    /**
     * 仓库类型编码
     */
    private String warehouseType;

    /**
     * 类型名称10售后仓20物料仓30限量仓40零售仓
     */
    private String warehouseTypeName;

    /**
     * 所属机构
     */
    private String department;

    /**
     * 所属区域
     */
    private String area;

    /**
     * 国家
     */
    private String country;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 邮编
     */
    private String postCode;

    /**
     * 地址
     */
    private String address;

    /**
     * 联系人
     */
    private String contactPerson;

    /**
     * 联系电话
     */
    private String contactPhone;

    /**
     * 传真
     */
    private String contactFax;

    /**
     * 邮件
     */
    private String contactEmail;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否删除0：正常,1删除
     */
    private Integer serverFlag;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String createEmp;


    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 修改人
     */
    private String modifyEmp;


}