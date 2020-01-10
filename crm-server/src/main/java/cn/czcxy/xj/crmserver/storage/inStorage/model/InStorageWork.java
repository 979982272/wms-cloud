package cn.czcxy.xj.crmserver.storage.inStorage.model;


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
public class InStorageWork {
    private Integer version;
    private String id;

    /**
     * 来源单号
     */
    private String fromOrder;

    /**
     * 订单类型
     */
    private String orderType;

    /**
     * 订单日期
     */
    private Date orderDate;

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
     * 订单状态 10制单20审核30部分入库40完全入库
     */
    private Integer status;

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
    private List<InStorageWorkPart> inStorageWorkParts;

}