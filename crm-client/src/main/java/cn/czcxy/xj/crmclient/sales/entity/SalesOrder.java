package cn.czcxy.xj.crmclient.sales.entity;


import cn.czcxy.xj.core.platform.base.annotation.*;
import lombok.*;
import tk.mybatis.mapper.annotation.Version;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

@Table(name = "crm_sales_order")
@CreateFlag
@ModifyFlag
@OrderByFlag
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SalesOrder {
    /**
     * 销售订单主键
     */
    @Id
    private String id;

    /**
     * 客户编码
     */
    @NotEmpty
    @Column(name = "customer_id")
    private String customerId;

    /**
     * 客户名称
     */
    @Column(name = "customer_name")
    @NotEmpty
    private String customerName;

    /**
     * 订单状态 10制单20审核30部分入库40完全入库
     */
    private Integer status;

    /**
     * 发货仓库编码
     */
    @Column(name = "warehouse_id")
    @NotEmpty
    private String warehouseId;

    /**
     * 仓库名称
     */
    @Column(name = "warehouse_name")
    @NotEmpty
    private String warehouseName;

    /**
     * 订单日期
     */
    @Column(name = "order_date")
    @NotEmpty
    private Date orderDate;

    /**
     * 申请发货日期
     */
    @Column(name = "delivery_date")
    @NotEmpty
    private Date deliveryDate;

    /**
     * 发票号
     */
    private String receipt;

    /**
     * 备注
     */
    private String remark;

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

    /**
     * 审核时间
     */
    @Column(name = "audit_time")
    private Date auditTime;

    /**
     * 审核人
     */
    @Column(name = "audit_emp")
    private String auditEmp;

    /**
     * 审核人编号
     */
    @Column(name = "audit_emp_id")
    private String auditEmpId;

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
     * 收货地址
     */
    private String address;

    /**
     * 联系人
     */
    private String person;

    /**
     * 联系电话
     */
    private String phone;

    @Transient
    private List<SalesOrderPart> salesOrderParts;

    @Version
    private Integer version;

}