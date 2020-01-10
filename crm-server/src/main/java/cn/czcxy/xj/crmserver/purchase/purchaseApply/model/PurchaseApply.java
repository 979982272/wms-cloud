package cn.czcxy.xj.crmserver.purchase.purchaseApply.model;


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
public class PurchaseApply {
    private Integer version;
    private String id;

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
     * 订单状态 10制单20审核30部分下推40已下推
     */
    private Integer status;

    /**
     * 申请日期
     */
    private Date applyDate;

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


    @Transient
    private List<PurchaseApplyPart> purchaseApplyParts;


}