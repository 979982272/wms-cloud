package cn.czcxy.xj.crmserver.sales.model;


import cn.czcxy.xj.core.platform.base.annotation.NotEmpty;
import lombok.*;
import tk.mybatis.mapper.annotation.Version;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SalesOrderPart {
    private Integer version;
    /**
     * 明细流水
     */
    private String id;

    /**
     * 销售单号
     */
    private String salesOrderId;

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
     * 税率
     */
    @NotEmpty
    private BigDecimal rate;

    /**
     * 销售数量
     */
    private BigDecimal salesAmount;

    /**
     * 出库数量
     */
    private BigDecimal deliveryAmount;


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