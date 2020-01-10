package cn.czcxy.xj.crmserver.stock.stockBegin.model;


import lombok.*;
import tk.mybatis.mapper.annotation.Version;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StockBegin {
    private Integer version;
    /**
     * 主键
     */
    private String id;

    private Integer status;

    /**
     * 仓库编码
     */
    private String warehouseId;

    /**
     * 仓库名称
     */
    private String warehouseName;

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
     * 期初库存
     */
    private BigDecimal stockAmount;

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
     * 税金总额
     */
    private BigDecimal ratePrice;


    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String createEmp;


    /**
     * 审核时间
     */
    private Date auditTime;

    /**
     * 审核人
     */
    private String auditEmp;

    /**

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 修改人
     */
    private String modifyEmp;

}