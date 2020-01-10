package cn.czcxy.xj.basicclient.warehouse.entity;


import cn.czcxy.xj.core.platform.base.annotation.CreateFlag;
import cn.czcxy.xj.core.platform.base.annotation.DeleteFlag;
import cn.czcxy.xj.core.platform.base.annotation.ModifyFlag;
import cn.czcxy.xj.core.platform.base.annotation.NotEmpty;
import lombok.*;
import tk.mybatis.mapper.annotation.Version;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "eidp_warehouse")
@CreateFlag
@ModifyFlag
@DeleteFlag
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Warehouse {
    /**
     * 仓库编码
     */
    @Id
    @NotEmpty
    private String id;

    /**
     * 仓库名称
     */
    @Column(name = "warehouse_name")
    @NotEmpty
    private String warehouseName;

    /**
     * 仓库类型编码
     */
    @Column(name = "warehouse_type")
    @NotEmpty
    private String warehouseType;

    /**
     * 类型名称10售后仓20物料仓30限量仓40零售仓
     */
    @Column(name = "warehouse_type_name")
    @NotEmpty
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
    @Column(name = "post_code")
    private String postCode;

    /**
     * 地址
     */
    private String address;

    /**
     * 联系人
     */
    @Column(name = "contact_person")
    private String contactPerson;

    /**
     * 联系电话
     */
    @Column(name = "contact_phone")
    private String contactPhone;

    /**
     * 传真
     */
    @Column(name = "contact_fax")
    private String contactFax;

    /**
     * 邮件
     */
    @Column(name = "contact_email")
    private String contactEmail;

    /**
     * 备注
     */
    private String remark;

    /**
     * 删除时间
     */
    @Column(name = "delete_time")
    private Date deleteTime;

    /**
     * 删除人
     */
    @Column(name = "delete_emp")
    private String deleteEmp;

    /**
     * 删除人编号
     */
    @Column(name = "delete_emp_id")
    private String deleteEmpId;

    /**
     * 是否删除0：正常,1删除
     */
    @Column(name = "server_flag")
    private Integer serverFlag;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 创建人
     */
    @Column(name = "create_emp")
    private String createEmp;

    /**
     * 创建人编号
     */
    @Column(name = "create_emp_id")
    private String createEmpId;

    /**
     * 修改时间
     */
    @Column(name = "modify_time")
    private Date modifyTime;

    /**
     * 修改人
     */
    @Column(name = "modify_emp")
    private String modifyEmp;

    /**
     * 修改人编号
     */
    @Column(name = "modify_emp_id")
    private String modifyEmpId;

    @Version
    private Integer version;
}