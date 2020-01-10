package cn.czcxy.xj.crmserver.stock.stockPart.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import tk.mybatis.mapper.annotation.Version;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Goods {
    private Integer version;

    /**
     * 产品编码
     */
    private String id;

    /**
     * 产品类型
     */
    private String goodsType;

    /**
     * 产品类型名称
     */
    private String goodsTypeName;

    /**
     * 产品单位
     */
    private String goodsUnit;

    /**
     * 产品单位名称
     */
    private String goodsUnitName;

    /**
     * 品牌
     */
    private String goodsBrand;

    /**
     * 品牌名称
     */
    private String goodsBrandName;

    /**
     * 产品名称
     */
    private String goodsName;

    /**
     * 规格型号
     */
    private String goodsModel;

    /**
     * 条形码
     */
    private String goodsBarcode;

    /**
     * sap物料编码
     */
    private String sapCode;

    /**
     * 标准价格
     */
    private BigDecimal normalPrice;

    /**
     * 税率
     */
    private BigDecimal rate;

    /**
     * 采购参考价
     */
    private BigDecimal purchasePrice;

    /**
     * 是否删除0：正常,1删除
     */
    private int serverFlag;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

    /**
     * 创建人
     */
    private String createEmp;

    /**
     * 创建人编号
     */
    private String createEmpId;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 修改人
     */
    private String modifyEmp;

    /**
     * 修改人编号
     */
    private String modifyEmpId;

}