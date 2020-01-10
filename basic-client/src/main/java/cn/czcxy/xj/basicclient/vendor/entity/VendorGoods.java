package cn.czcxy.xj.basicclient.vendor.entity;

import lombok.*;
import tk.mybatis.mapper.annotation.Version;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "eidp_vendor_goods")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class VendorGoods {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "UUID")
    private String id;

    /**
     * 产品编码
     */
    @Column(name = "goods_id")
    private String goodsId;

    /**
     * 产品名称
     */
    @Column(name = "goods_name")
    private String goodsName;

    /**
     * 产品类型编码
     */
    @Column(name = "goods_type_id")
    private String goodsTypeId;

    /**
     * 产品类型名称
     */
    @Column(name = "goods_type_name")
    private String goodsTypeName;

    /**
     * 产品单位编码
     */
    @Column(name = "goods_unit_id")
    private String goodsUnitId;

    /**
     * 产品单位名称
     */
    @Column(name = "goods_unit_name")
    private String goodsUnitName;

    /**
     * 产品品牌编码
     */
    @Column(name = "goods_brand_id")
    private String goodsBrandId;

    /**
     * 产品品牌名称
     */
    @Column(name = "goods_brand_name")
    private String goodsBrandName;

    /**
     * 规格型号
     */
    @Column(name = "goods_model")
    private String goodsModel;

    /**
     * 供应商编码
     */
    @Column(name = "vendor_id")
    private String vendorId;

    /**
     * 供应商名称
     */
    @Column(name = "vendor_name")
    private String vendorName;

    /**
     * 供应价格
     */
    @Column(name = "normal_price")
    private BigDecimal normalPrice;

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

    @Version
    private Integer version;
}