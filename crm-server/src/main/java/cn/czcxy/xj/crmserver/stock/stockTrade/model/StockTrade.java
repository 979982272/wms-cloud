package cn.czcxy.xj.crmserver.stock.stockTrade.model;


import lombok.*;
import tk.mybatis.mapper.annotation.Version;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StockTrade {
    private Integer version;
    /**
     * 主键
     */
    private String id;

    /**
     * 交易类型 10库存期初20入库30出库
     */
    private String tradeType;

    /**
     * 库存交易时间
     */
    private Date tradeTime;

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
     * 来源单号
     */
    private String formOrder;

    /**
     * 交易数量
     */
    private BigDecimal tradeAmount;

    /**
     * 单位价格
     */
    private BigDecimal unitPrice;

    /**
     * 含税单价
     */
    private BigDecimal unitPriceRate;

    /**
     * 总金额
     */
    private BigDecimal totalPrice;

    /**
     * 含税总金额
     */
    private BigDecimal totalPriceRate;


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
     * 删除时间
     */
    private Date deleteTime;

    /**
     * 删除人
     */
    private String deleteEmp;

}