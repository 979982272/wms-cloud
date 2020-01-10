package cn.czcxy.xj.crmserver.purchase.purchaseOrder.model;


import lombok.*;
import tk.mybatis.mapper.annotation.Version;

import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrder {
    private Integer version;
    /**
     * 采购订单单号
     */
    private String id;

    private String fromOrder;

    /**
     * 供应商编码
     */
    private String vendorId;

    /**
     * 供应商名称
     */
    private String vendorName;

    /**
     * 仓库编码
     */
    private String warehouseId;

    /**
     * 仓库名称
     */
    private String warehouseName;

    /**
     * 订单状态 10制单20审核30部分收货40已收货
     */
    private Integer status;

    /**
     * 订单日期
     */
    private Date orderDate;

    /**
     * 到货日期
     */
    private Date arrivalDate;

    /**
     * 备注
     */
    private String remark;


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


    /**
     * 审核时间
     */
    private Date auditTime;

    /**
     * 审核人
     */
    private String auditEmp;


    /**
     * 删除时间
     */
    private Date deleteTime;

    /**
     * 删除人
     */
    private String deleteEmp;


    @Transient
    private List<PurchaseOrderPart> purchaseOrderParts;

}