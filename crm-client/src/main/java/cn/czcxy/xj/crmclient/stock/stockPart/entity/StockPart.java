package cn.czcxy.xj.crmclient.stock.stockPart.entity;


import cn.czcxy.xj.basicserver.goods.model.Goods;
import cn.czcxy.xj.core.platform.base.annotation.CreateFlag;
import cn.czcxy.xj.core.platform.base.annotation.ModifyFlag;
import cn.czcxy.xj.core.platform.base.annotation.NotEmpty;
import lombok.*;
import tk.mybatis.mapper.annotation.Version;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "crm_stock_part")
@CreateFlag
@ModifyFlag
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StockPart {
    /**
     * 库存流水
     */
    @Id
    @GeneratedValue(generator = "UUID")
    private String id;

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
     * 产品编码
     */
    @Column(name = "goods_id")
    @NotEmpty
    private String goodsId;

    /**
     * 产品名称
     */
    @Column(name = "goods_name")
    @NotEmpty
    private String goodsName;

    /**
     * 单位编码
     */
    @Column(name = "goods_unit_id")
    @NotEmpty
    private String goodsUnitId;

    /**
     * 单位名称
     */
    @Column(name = "goods_unit_name")
    @NotEmpty
    private String goodsUnitName;

    /**
     * 规格型号
     */
    @Column(name = "goods_model")
    private String goodsModel;

    /**
     * 总库存
     */
    @Column(name = "total_stock_amount")
    @NotEmpty
    private BigDecimal totalStockAmount;

    /**
     * 可用库存
     */
    @Column(name = "stock_amount")
    @NotEmpty
    private BigDecimal stockAmount;

    /**
     * 入库库存,废弃暂时不用
     */
    @Column(name = "in_stock_amount")
    @Deprecated
    private BigDecimal inStockAmount;

    /**
     * 出库库存，废弃暂时不用
     */
    @Column(name = "lock_stock_amount")
    private BigDecimal lockStockAmount;

    /**
     * 单位成本
     */
    @Column(name = "unit_cost_price")
    @NotEmpty
    private BigDecimal unitCostPrice;

    @Transient
    private Goods goods;

    /**
     * 含税单位成本
     */
    @Column(name = "unit_cost_price_rate")
    @NotEmpty
    private BigDecimal unitCostPriceRate;

    /**
     * 总成本
     */
    @Column(name = "total_cost_price")
    @NotEmpty
    private BigDecimal totalCostPrice;

    /**
     * 含税总成本
     */
    @Column(name = "total_cost_price_rate")
    @NotEmpty
    private BigDecimal totalCostPriceRate;

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

    @Version
    private Integer version;
}