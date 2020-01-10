package cn.czcxy.xj.crmclient.stock.stockBegin.entity;


import cn.czcxy.xj.core.platform.base.annotation.AuditFlag;
import cn.czcxy.xj.core.platform.base.annotation.CreateFlag;
import cn.czcxy.xj.core.platform.base.annotation.ModifyFlag;
import cn.czcxy.xj.core.platform.base.annotation.NotEmpty;
import lombok.*;
import tk.mybatis.mapper.annotation.Version;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "crm_stock_begin")
@CreateFlag
@ModifyFlag
@AuditFlag
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StockBegin {
    /**
     * 主键
     */
    @Id
    @NotEmpty
    private String id;

    @NotEmpty
    private Integer status;

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
     * 期初库存
     */
    @Column(name = "stock_amount")
    @NotEmpty
    private BigDecimal stockAmount;

    /**
     * 单位价格
     */
    @Column(name = "unit_price")
    @NotEmpty
    private BigDecimal unitPrice;

    /**
     * 含税单价
     */
    @Column(name = "unit_price_rate")
    @NotEmpty
    private BigDecimal unitPriceRate;

    /**
     * 总价
     */
    @Column(name = "total_price")
    @NotEmpty
    private BigDecimal totalPrice;

    /**
     * 含税总价
     */
    @Column(name = "total_price_rate")
    @NotEmpty
    private BigDecimal totalPriceRate;

    /**
     * 税金总额
     */
    @Column(name = "rate_price")
    @NotEmpty
    private BigDecimal ratePrice;

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
     * 审核时间
     */
    @Column(name = "audit_time")
    private Date auditTime;

    /**
     * 审核人
     */
    @Column(name = "audit_emp")
    private String auditEmp;

    /**
     * 审核人编号
     */
    @Column(name = "audit_emp_id")
    private String auditEmpId;

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

    @Version
    private Integer version;

}