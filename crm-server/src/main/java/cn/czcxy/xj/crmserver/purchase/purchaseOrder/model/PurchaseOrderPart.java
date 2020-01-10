package cn.czcxy.xj.crmserver.purchase.purchaseOrder.model;


import lombok.*;
import tk.mybatis.mapper.annotation.Version;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrderPart {
    private Integer version;
    /**
     * 主键
     */
    private String id;

    /**
     * 来源明细单号
     */
    private String fromOrderPart;
    /**
     * 采购订单号
     */
    private String purchaseOrderId;

    /**
     * 产品编码
     */
    private String goodsId;

    /**
     * 产品名称
     */
    private String goodsName;

    /**
     * 单位编码
     */
    private String goodsUnitId;

    /**
     * 单位名称
     */
    private String goodsUnitName;

    /**
     * 规格型号
     */
    private String goodsModel;

    /**
     * 申请数量
     */
    private BigDecimal purchaseAmount;

    /**
     * 收货数量
     */
    private BigDecimal receivingAmount;

    /**
     * 单位价格
     */
    private BigDecimal unitPrice;

    /**
     * 含税单价
     */
    private BigDecimal unitPriceRate;

    /**
     * 总价
     */
    private BigDecimal totalPrice;

    /**
     * 含税总价
     */
    private BigDecimal totalPriceRate;

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
     * 税率
     */
    private BigDecimal rate;

}