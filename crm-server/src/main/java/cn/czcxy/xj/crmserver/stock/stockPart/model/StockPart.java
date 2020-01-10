package cn.czcxy.xj.crmserver.stock.stockPart.model;



import lombok.*;
import tk.mybatis.mapper.annotation.Version;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StockPart {
    private Integer version;
    /**
     * 库存流水
     */
    private String id;

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
     * 总库存
     */
    private BigDecimal totalStockAmount;

    /**
     * 可用库存
     */
    private BigDecimal stockAmount;

    /**
     * 入库库存,废弃暂时不用
     */
    private BigDecimal inStockAmount;

    /**
     * 出库库存，废弃暂时不用
     */
    private BigDecimal lockStockAmount;

    /**
     * 单位成本
     */
    private BigDecimal unitCostPrice;

    @Transient
    private Goods goods;

    /**
     * 含税单位成本
     */
    private BigDecimal unitCostPriceRate;

    /**
     * 总成本
     */
    private BigDecimal totalCostPrice;

    /**
     * 含税总成本
     */
    private BigDecimal totalCostPriceRate;


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