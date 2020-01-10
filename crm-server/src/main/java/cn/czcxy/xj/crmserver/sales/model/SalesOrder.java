package cn.czcxy.xj.crmserver.sales.model;


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
public class SalesOrder {
    private Integer version;
    /**
     * 销售订单主键
     */
    private String id;

    /**
     * 客户编码
     */
    private String customerId;

    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 订单状态 10制单20审核30部分入库40完全入库
     */
    private Integer status;

    /**
     * 发货仓库编码
     */
    private String warehouseId;

    /**
     * 仓库名称
     */
    private String warehouseName;

    /**
     * 订单日期
     */
    private Date orderDate;

    /**
     * 申请发货日期
     */
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
}