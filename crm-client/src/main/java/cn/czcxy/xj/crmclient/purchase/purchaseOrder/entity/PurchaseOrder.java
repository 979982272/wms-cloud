package cn.czcxy.xj.crmclient.purchase.purchaseOrder.entity;


import cn.czcxy.xj.core.platform.base.annotation.CreateFlag;
import cn.czcxy.xj.core.platform.base.annotation.DeleteFlag;
import cn.czcxy.xj.core.platform.base.annotation.ModifyFlag;
import cn.czcxy.xj.core.platform.base.annotation.NotEmpty;
import lombok.*;
import tk.mybatis.mapper.annotation.Version;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

@Table(name = "crm_purchase_order")
@CreateFlag
@ModifyFlag
@DeleteFlag
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrder {
    /**
     * 采购订单单号
     */
    @Id
    @NotEmpty
    private String id;

    @Column(name = "from_order")
    private String fromOrder;

    /**
     * 供应商编码
     */
    @Column(name = "vendor_id")
    @NotEmpty
    private String vendorId;

    /**
     * 供应商名称
     */
    @Column(name = "vendor_name")
    @NotEmpty
    private String vendorName;

    /**
     * 仓库编码
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
     * 订单状态 10制单20审核30部分收货40已收货
     */
    @NotEmpty
    private Integer status;

    /**
     * 订单日期
     */
    @Column(name = "order_date")
    @NotEmpty
    private Date orderDate;

    /**
     * 到货日期
     */
    @Column(name = "arrival_date")
    @NotEmpty
    private Date arrivalDate;

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

    @Transient
    private List<PurchaseOrderPart> purchaseOrderParts;

    @Version
    private Integer version;
}