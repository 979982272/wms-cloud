package cn.czcxy.xj.basicclient.goods.entity;


import lombok.*;
import tk.mybatis.mapper.annotation.Version;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "eidp_goods_archive")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Goods implements Serializable {
    @Version
    private Integer version;
    /**
     * 产品编码
     */
    @Id
    private String id;

    /**
     * 产品类型
     */
    @Column(name = "goods_type_id")
    private String goodsType;

    /**
     * 产品类型名称
     */
    @Column(name = "goods_type_name")
    private String goodsTypeName;

    /**
     * 产品单位
     */
    @Column(name = "goods_unit_id")
    private String goodsUnit;

    /**
     * 产品单位名称
     */
    @Column(name = "goods_unit_name")
    private String goodsUnitName;

    /**
     * 品牌
     */
    @Column(name = "goods_brand_id")
    private String goodsBrand;

    /**
     * 品牌名称
     */
    @Column(name = "goods_brand_name")
    private String goodsBrandName;

    /**
     * 产品名称
     */
    @Column(name = "goods_name")
    private String goodsName;

    /**
     * 规格型号
     */
    @Column(name = "goods_model")
    private String goodsModel;

    /**
     * 条形码
     */
    @Column(name = "goods_barcode")
    private String goodsBarcode;

    /**
     * sap物料编码
     */
    @Column(name = "sap_code")
    private String sapCode;

    /**
     * 标准价格
     */
    @Column(name = "normal_price")
    private BigDecimal normalPrice;

    /**
     * 税率
     */
    private BigDecimal rate;

    /**
     * 采购参考价
     */
    @Column(name = "purchase_price")
    private BigDecimal purchasePrice;

    /**
     * 是否删除0：正常,1删除
     */
    @Column(name = "server_flag")
    private int serverFlag;

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

}